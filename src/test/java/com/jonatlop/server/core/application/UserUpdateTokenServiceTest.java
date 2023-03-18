package com.jonatlop.server.core.application;

import com.jonatlop.server.core.util.uuid.GenerateUuidService;
import com.jonatlop.server.core.application.user_update_token.*;
import com.jonatlop.server.core.domain.dto.core_dto.PhoneCoreDTO;
import com.jonatlop.server.core.domain.dto.core_dto.UserCoreDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

public class UserUpdateTokenServiceTest {
    private final UUID userId = new GenerateUuidService().execute(null);
    private final String dummyToken = "I am a dummy token!";
    private final String userName = "UserName";
    private final String userEmail = "test_email1@test.co";
    private final String dummyHashedPassword = "HashedPassword";
    private final List<PhoneCoreDTO> userPhones = List.of();
    
    @Test
    public void givenUserAndToken_whenExecute_thenReturnUpdatedUserWithToken() {
        final UserUpdateTokenInputModel userUpdateTokenInputModel = new UserUpdateTokenInputModel(userId, dummyToken);
        final UserUpdateTokenOutputModel expectedOutputModel = new UserUpdateTokenOutputModel(
            UserCoreDTO
                .builder()
                .id(userId)
                .name(userName)
                .email(userEmail)
                .password(dummyHashedPassword)
                .phones(userPhones)
                .token(dummyToken)
                .build()
        );
        final UserUpdateTokenPersistenceGateway gateway = Mockito.mock(UserUpdateTokenPersistenceGateway.class);
        Mockito.when(gateway.updateToken(userId, dummyToken)).thenReturn(expectedOutputModel.getUpdatedUser());
        final UserUpdateTokenInteractor userUpdateTokenInteractor = new UserUpdateTokenService(gateway);
        final UserUpdateTokenOutputModel actualOutputModel = userUpdateTokenInteractor.execute(userUpdateTokenInputModel);
        Assertions.assertEquals(expectedOutputModel, actualOutputModel);
    }
}
