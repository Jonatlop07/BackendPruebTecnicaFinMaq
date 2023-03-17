package com.jonatlop.server.core.application.user_register;

import com.jonatlop.server.core.domain.entity.User;
import com.jonatlop.server.core.domain.exception.UserInvalidCredentialsFormatException;
import com.jonatlop.server.core.domain.exception.UserWithEmailAlreadyExistsException;
import com.jonatlop.server.core.domain.mapper.UserMapper;
import com.jonatlop.server.core.domain.dto.core_dto.UserCoreDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class UserRegisterService implements UserRegisterInteractor {
    private final UserRegisterPersistenceGateway gateway;
    
    @Override
    public UserRegisterOutputModel execute(UserRegisterInputModel input) {
        final User user = UserMapper.toEntity(input);
        if (userCredentialsHaveValidFormat(user, input.getPasswordFormatRegexp())) {
            throw new UserInvalidCredentialsFormatException();
        }
        if (gateway.existsWithEmail(user.getEmail())) {
            throw new UserWithEmailAlreadyExistsException();
        }
        final UserCoreDTO userWithHashedPasswordDTO = UserMapper.toPersistenceDTOWithHashedPassword(
            user,
            hashPassword(user.getPassword())
        );
        final UserCoreDTO createdUserDTO = gateway.create(userWithHashedPasswordDTO);
        return new UserRegisterOutputModel(createdUserDTO);
    }
    
    private boolean userCredentialsHaveValidFormat(User user, String passwordFormatRegex) {
        return user.hasValidEmail() && user.hasValidPassword(passwordFormatRegex);
    }
    
    private String hashPassword(String password) {
        final int STRENGTH = 10;
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(STRENGTH, new SecureRandom());
        return passwordEncoder.encode(password);
    }
}
