package com.jonatlop.server.core.application.user_register;

import com.jonatlop.server.core.util.uuid.GenerateUuidInteractor;
import com.jonatlop.server.framework.security.service.hash_password.HashPasswordInteractor;
import com.jonatlop.server.core.domain.entity.User;
import com.jonatlop.server.core.domain.exception.UserInvalidCredentialsFormatException;
import com.jonatlop.server.core.domain.exception.UserWithEmailAlreadyExistsException;
import com.jonatlop.server.core.domain.mapper.UserMapper;
import com.jonatlop.server.core.domain.dto.core_dto.UserCoreDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class UserRegisterService implements UserRegisterInteractor {
    private final UserRegisterPersistenceGateway gateway;
    private final HashPasswordInteractor hashPasswordInteractor;
    private final GenerateUuidInteractor generateUuidInteractor;
    
    @Override
    public UserRegisterOutputModel execute(UserRegisterInputModel input) {
        final User user = UserMapper.toEntity(input, generateUuidInteractor.execute(null));
        if (!userCredentialsHaveValidFormat(user, input.getPasswordFormatRegexp())) {
            throw new UserInvalidCredentialsFormatException();
        }
        if (gateway.existsWithEmail(user.getEmail())) {
            throw new UserWithEmailAlreadyExistsException();
        }
        final UserCoreDTO userWithHashedPasswordDTO = UserMapper.toCoreDTOWithHashedPassword(
            user,
            hashPasswordInteractor.execute(user.getPassword())
        );
        final UserCoreDTO createdUserDTO = gateway.create(userWithHashedPasswordDTO);
        return new UserRegisterOutputModel(createdUserDTO);
    }
    
    private boolean userCredentialsHaveValidFormat(User user, String passwordFormatRegex) {
        System.out.println(user.getEmail());
        return user.hasValidEmail() && user.hasValidPassword(passwordFormatRegex);
    }
}
