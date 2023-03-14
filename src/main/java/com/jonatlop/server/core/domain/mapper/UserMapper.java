package com.jonatlop.server.core.domain.mapper;

import com.jonatlop.server.core.domain.persistence_dto.UserPersistenceDTO;
import com.jonatlop.server.core.application.user_register.UserRegisterInputModel;
import com.jonatlop.server.core.domain.entity.User;

public class UserMapper {
    public static User toEntity( UserRegisterInputModel input) {
        return User
            .builder()
            .id()
            .name(input.name())
            .email(input.email())
            .password(input.password())
            .phones(input.phones())
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
