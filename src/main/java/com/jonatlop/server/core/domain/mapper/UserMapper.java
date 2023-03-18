package com.jonatlop.server.core.domain.mapper;

import com.jonatlop.server.core.domain.dto.core_dto.UserCoreDTO;
import com.jonatlop.server.core.application.user_register.UserRegisterInputModel;
import com.jonatlop.server.core.domain.entity.User;

import java.util.UUID;
import java.util.stream.Collectors;

public final class UserMapper {
    private UserMapper() {}
    
    public static User toEntity(UserRegisterInputModel input, UUID id) {
        return User
            .builder()
            .id(id)
            .name(input.getName())
            .email(input.getEmail())
            .password(input.getPassword())
            .phones(
                input.getPhones()
                    .stream()
                    .map(PhoneMapper::toEntity)
                    .collect(Collectors.toList())
            )
            .build();
    }
    
    public static UserCoreDTO toCoreDTOWithHashedPassword(User user, String hashedPassword) {
        return UserCoreDTO
            .builder()
            .id(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .password(hashedPassword)
            .phones(
                user.getPhones()
                    .stream()
                    .map(PhoneMapper::toPersistenceDTO)
                    .collect(Collectors.toList())
            )
            .build();
    }
}
