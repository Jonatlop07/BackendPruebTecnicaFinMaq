package com.jonatlop.server.core.application;

import com.jonatlop.server.core.abstraction.exception.QueryFieldsNotSetException;
import com.jonatlop.server.core.util.uuid.GenerateUuidService;
import com.jonatlop.server.core.application.user_query.*;
import com.jonatlop.server.core.domain.dto.core_dto.PhoneCoreDTO;
import com.jonatlop.server.core.domain.dto.details_dto.UserDetailsDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserQueryServiceTest {
    private final UUID userId = new GenerateUuidService().execute(null);
    private final String userName = "UserName";
    private final String userEmail = "test_email1@test.co";
    private final String userPassword = "Abc123_tr";
    private final List<PhoneCoreDTO> userPhones = List.of();
    
    private final UserQueryPersistenceGateway gateway = Mockito.mock(UserQueryPersistenceGateway.class);
    
    @Test
    public void givenExistingUserId_whenExecute_thenReturnsUserDetails() {
        final UserQueryInputModel userQueryInputModel = UserQueryInputModel
            .builder()
            .id(userId)
            .build();
        final Optional<UserDetailsDTO> expectedUserDetails = Optional.of(
            UserDetailsDTO
                .builder()
                .id(userId)
                .name(userName)
                .email(userEmail)
                .phones(userPhones)
                .build()
        );
        Mockito.when(gateway.queryById(userId)).thenReturn(expectedUserDetails);
        final UserQueryInteractor userQueryInteractor = new UserQueryService(gateway);
        final UserQueryOutputModel userQueryOutputModel = userQueryInteractor.execute(userQueryInputModel);
        Assertions.assertEquals(expectedUserDetails, userQueryOutputModel.getUserById());
        Assertions.assertEquals(Optional.<UserDetailsDTO>empty(), userQueryOutputModel.getUserByEmail());
        Assertions.assertEquals(List.<UserDetailsDTO>of(), userQueryOutputModel.getUsersByName());
    }
    
    @Test
    public void givenExistingUserEmail_whenExecute_thenReturnsUserDetails() {
        final UserQueryInputModel userQueryInputModel = UserQueryInputModel
            .builder()
            .email(userEmail)
            .build();
        final Optional<UserDetailsDTO> expectedUserDetails = Optional.of(
            UserDetailsDTO
                .builder()
                .id(userId)
                .name(userName)
                .email(userEmail)
                .phones(userPhones)
                .build()
        );
        Mockito.when(gateway.queryByEmail(userEmail)).thenReturn(expectedUserDetails);
        final UserQueryInteractor userQueryInteractor = new UserQueryService(gateway);
        final UserQueryOutputModel userQueryOutputModel = userQueryInteractor.execute(userQueryInputModel);
        Assertions.assertEquals(Optional.<UserDetailsDTO>empty(), userQueryOutputModel.getUserById());
        Assertions.assertEquals(expectedUserDetails, userQueryOutputModel.getUserByEmail());
        Assertions.assertEquals(List.<UserDetailsDTO>of(), userQueryOutputModel.getUsersByName());
    }
    
    @Test
    public void givenExistingUserName_whenExecute_thenReturnsUserDetailsInList() {
        final UserQueryInputModel userQueryInputModel = UserQueryInputModel
            .builder()
            .name(userName)
            .build();
        final List<UserDetailsDTO> expectedUsersDetails = List.of(
            UserDetailsDTO
                .builder()
                .id(userId)
                .name(userName)
                .email(userEmail)
                .phones(userPhones)
                .build()
        );
        Mockito.when(gateway.queryByName(userName)).thenReturn(expectedUsersDetails);
        final UserQueryInteractor userQueryInteractor = new UserQueryService(gateway);
        final UserQueryOutputModel userQueryOutputModel = userQueryInteractor.execute(userQueryInputModel);
        Assertions.assertEquals(Optional.<UserDetailsDTO>empty(), userQueryOutputModel.getUserById());
        Assertions.assertEquals(Optional.<UserDetailsDTO>empty(), userQueryOutputModel.getUserByEmail());
        Assertions.assertEquals(expectedUsersDetails, userQueryOutputModel.getUsersByName());
    }
    
    @Test
    public void givenNoQueryFields_whenExecute_thenThrowsQueryFieldsNotSetException() {
        final UserQueryInputModel userQueryInputModel = UserQueryInputModel
            .builder()
            .build();
        final UserQueryInteractor userQueryInteractor = new UserQueryService(gateway);
        Assertions.assertThrows(QueryFieldsNotSetException.class, () -> userQueryInteractor.execute(userQueryInputModel));
    }
}
