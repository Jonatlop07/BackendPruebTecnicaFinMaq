package com.jonatlop.server.core.application.user_register;

import com.jonatlop.server.core.domain.entity.User;
import com.jonatlop.server.core.domain.exception.UserInvalidCredentialsFormatException;
import com.jonatlop.server.core.domain.exception.UserWithEmailAlreadyExistsException;
import com.jonatlop.server.core.domain.mapper.UserMapper;
import com.jonatlop.server.core.domain.persistence_dto.UserPersistenceDTO;
import com.jonatlop.server.core.domain.query_dto.UserQueryDTO;
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
        if (existsUserWithEmail(user)) {
            throw new UserWithEmailAlreadyExistsException();
        }
        final UserPersistenceDTO userWithHashedPasswordDTO = UserMapper.toPersistenceDTOWithHashedPassword(
            user,
            hashPassword(user.getPassword())
        );
        final UserPersistenceDTO createdUserDTO = persistUser(userWithHashedPasswordDTO);
        return toOutputModel(createdUserDTO);
    }
    
    private boolean userCredentialsHaveValidFormat(User user, String passwordFormatRegex) {
        return user.hasValidEmail() && user.hasValidPassword(passwordFormatRegex);
    }
    
    private boolean existsUserWithEmail(User user) {
        return this.gateway.exists(new UserQueryDTO(user.getEmail()));
    }
    
    private String hashPassword(String password) {
        final int STRENGTH = 10;
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(STRENGTH, new SecureRandom());
        return passwordEncoder.encode(password);
    }
    
    private UserPersistenceDTO persistUser(UserPersistenceDTO newUser) {
        return this.gateway.create(newUser);
    }
    
    private UserRegisterOutputModel toOutputModel(UserPersistenceDTO dto) {
        return UserRegisterOutputModel
            .builder()
            .id(dto.getId())
            .created(dto.getCreated())
            .modified(dto.getModified())
            .lastLogin(dto.getLastLogin())
            .isActive(dto.isActive())
            .build();
    }
}
