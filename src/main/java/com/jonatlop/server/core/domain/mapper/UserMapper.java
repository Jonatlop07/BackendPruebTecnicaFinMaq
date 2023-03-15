package com.jonatlop.server.core.domain.mapper;

import com.jonatlop.server.core.domain.persistence_dto.UserPersistenceDTO;
import com.jonatlop.server.core.application.user_register.UserRegisterInputModel;
import com.jonatlop.server.core.domain.entity.User;

public final class UserMapper {
    private UserMapper() {}
    
    public static User toEntity( UserRegisterInputModel input) {
        return User
            .builder()
            .id()
            .name(input.getName())
            .email(input.getEmail())
            .password(input.getPassword())
            .phones(input.getPhones())
            .build();
    }
    
    public static UserPersistenceDTO toPersistenceDTOWithHashedPassword( User user, String hashedPassword) {
        return UserPersistenceDTO
            .builder()
            .id(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .password(hashedPassword)
            .phones(user.getPhones())
            .build();
    }
}
