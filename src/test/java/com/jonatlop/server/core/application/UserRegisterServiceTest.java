package com.jonatlop.server.core.application;

import com.jonatlop.server.core.util.uuid.GenerateUuidInteractor;
import com.jonatlop.server.core.util.uuid.GenerateUuidService;
import com.jonatlop.server.core.application.user_register.*;
import com.jonatlop.server.core.domain.dto.core_dto.PhoneCoreDTO;
import com.jonatlop.server.core.domain.dto.core_dto.UserCoreDTO;
import com.jonatlop.server.core.domain.entity.User;
import com.jonatlop.server.core.domain.exception.UserInvalidCredentialsFormatException;
import com.jonatlop.server.core.domain.exception.UserWithEmailAlreadyExistsException;
import com.jonatlop.server.core.domain.mapper.PhoneMapper;
import com.jonatlop.server.core.domain.mapper.UserMapper;
import com.jonatlop.server.framework.security.service.hash_password.HashPasswordInteractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserRegisterServiceTest {
    private final String testPasswordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$&*~_]).{8,}$";
    private final UUID userId = new GenerateUuidService().execute(null);
    private final String userName = "UserName";
    private final String validUserEmail = "test_email1@test.co";
    private final String invalidUserEmail = "test_email1@";
    private final String userPassword = "Abc123_tr";
    private final List<PhoneCoreDTO> userPhones = List.of();
    private final String dummyHashedPassword = "HashedPassword";
    
    private final GenerateUuidInteractor generateUuidInteractorMock = Mockito.mock(GenerateUuidInteractor.class);
    private final HashPasswordInteractor hashPasswordInteractorMock = Mockito.mock(HashPasswordInteractor.class);
    private final UserRegisterPersistenceGateway gatewayMock = Mockito.mock(UserRegisterPersistenceGateway.class);
    
    @BeforeEach
    public void setUpGenerateUuidInteractorMock() {
        Mockito.when(generateUuidInteractorMock.execute(null)).thenReturn(userId);
    }
    
    @Test
    public void givenValidCredentials_whenExecute_thenReturnCreatedUser() {
        final UserRegisterInputModel userRegisterInputModel = UserRegisterInputModel
            .builder()
            .name(userName)
            .email(validUserEmail)
            .password(userPassword)
            .phones(userPhones)
            .passwordFormatRegexp(testPasswordRegex)
            .build();
        final UserRegisterOutputModel expectedOutputModel = new UserRegisterOutputModel(
            UserCoreDTO
                .builder()
                .id(userId)
                .name(userName)
                .email(validUserEmail)
                .password(dummyHashedPassword)
                .phones(userPhones)
                .build()
        );
        Mockito.when(hashPasswordInteractorMock.execute(userRegisterInputModel.getPassword())).thenReturn(dummyHashedPassword);
        Mockito.when(gatewayMock.existsWithEmail(userRegisterInputModel.getEmail())).thenReturn(false);
        Mockito.when(gatewayMock.create(
            UserMapper.toCoreDTOWithHashedPassword(
                User
                .builder()
                .id(userId)
                .name(userName)
                .email(validUserEmail)
                .password(userPassword)
                .phones(
                    userPhones
                        .stream()
                        .map(PhoneMapper::toEntity)
                        .collect(Collectors.toList())
                )
                .build(),
                dummyHashedPassword
            )
        )).thenReturn(expectedOutputModel.getCreatedUser());
        final UserRegisterInteractor userRegisterService = new UserRegisterService(
          gatewayMock,
          hashPasswordInteractorMock,
          generateUuidInteractorMock  
        );
        final UserRegisterOutputModel actualOutputModel = userRegisterService.execute(userRegisterInputModel);
        Assertions.assertEquals(expectedOutputModel, actualOutputModel);
    }
    
    @Test
    public void givenCredentialsWithInvalidFormat_whenExecute_thenThrowsInvalidCredentialsException() {
        final UserRegisterInputModel userRegisterInputModel = UserRegisterInputModel
            .builder()
            .name(userName)
            .email(invalidUserEmail)
            .password(userPassword)
            .phones(userPhones)
            .passwordFormatRegexp(testPasswordRegex)
            .build();
        Mockito.when(generateUuidInteractorMock.execute(null)).thenReturn(userId);
        final UserRegisterInteractor userRegisterService = new UserRegisterService(
            gatewayMock,
            hashPasswordInteractorMock,
            generateUuidInteractorMock
        );
        Assertions.assertThrows(UserInvalidCredentialsFormatException.class, () -> userRegisterService.execute(userRegisterInputModel));
    }
    
    @Test
    public void givenAlreadyTakenEmail_whenExecute_thenThrowUserAlreadyExistsException() {
        final UserRegisterInputModel userRegisterInputModel = UserRegisterInputModel
            .builder()
            .name(userName)
            .email(validUserEmail)
            .password(userPassword)
            .phones(userPhones)
            .passwordFormatRegexp(testPasswordRegex)
            .build();
        Mockito.when(generateUuidInteractorMock.execute(null)).thenReturn(userId);
        Mockito.when(gatewayMock.existsWithEmail(userRegisterInputModel.getEmail())).thenReturn(true);
        final UserRegisterInteractor userRegisterService = new UserRegisterService(
            gatewayMock,
            hashPasswordInteractorMock,
            generateUuidInteractorMock
        );
        Assertions.assertThrows(UserWithEmailAlreadyExistsException.class, () -> userRegisterService.execute(userRegisterInputModel));
    }
}