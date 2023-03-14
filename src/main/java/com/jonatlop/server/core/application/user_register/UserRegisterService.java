package com.jonatlop.server.core.application.user_register;

import com.jonatlop.server.core.domain.entity.User;
import com.jonatlop.server.core.domain.exception.UserInvalidCredentialsFormatException;
import com.jonatlop.server.core.domain.exception.UserWithEmailAlreadyExistsException;
import com.jonatlop.server.core.domain.mapper.UserMapper;
import com.jonatlop.server.core.domain.persistence_dto.UserPersistenceDTO;
import com.jonatlop.server.core.domain.query_dto.UserQueryDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

@AllArgsConstructor
public class UserRegisterService implements UserRegisterInteractor {
    private final UserRegisterPersistenceGateway gateway;
    
    @Override
    public UserRegisterOutputModel execute(UserRegisterInputModel input) {
        final User user = UserMapper.toEntity(input);
        if (userCredentialsHaveValidFormat(user, input.passwordFormatRegex())) {
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
        return this.gateway
            .exists(
                UserQueryDTO
                    .builder()
                    .email(user.getEmail())
                    .build()
            );
    }
    
    private String hashPassword(String password) {
        final int STRENGTH = 10;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(STRENGTH, new SecureRandom());
        return passwordEncoder.encode(password);
    }
    
    private UserPersistenceDTO persistUser(UserPersistenceDTO newUser) {
        return this.gateway.create(newUser);
    }
    
    private UserRegisterOutputModel toOutputModel(UserPersistenceDTO dto) {
        return UserRegisterOutputModel
            .builder()
            .id(dto.id())
            .created(dto.created())
            .modified(dto.modified())
            .lastLogin(dto.lastLogin())
            .isActive(dto.isActive())
            .build();
    }
}
