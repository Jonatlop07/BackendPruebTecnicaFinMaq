package com.jonatlop.server.data.db.jpa.mapper;

import com.jonatlop.server.core.domain.dto.core_dto.UserCoreDTO;
import com.jonatlop.server.core.domain.dto.details_dto.UserDetailsDTO;
import com.jonatlop.server.data.db.jpa.entity.User;

import java.util.stream.Collectors;

public final class UserMapper {
    private UserMapper() {}
    
    public static User toEntity(UserCoreDTO dto) {
        return User
            .builder()
            .id(dto.getId())
            .name(dto.getName())
            .email(dto.getEmail())
            .password(dto.getPassword())
            .created(dto.getCreated())
            .modified(dto.getModified())
            .lastLogin(dto.getLastLogin())
            .isActive(dto.isActive())
            .token(dto.getToken())
            .phones(
                dto
                    .getPhones()
                    .stream()
                    .map(PhoneMapper::toEntity)
                    .collect(Collectors.toList())
            )
            .build();
    }
    
    public static UserCoreDTO toCoreDTO(User entity) {
        return UserCoreDTO
            .builder()
            .id(entity.getId())
            .name(entity.getName())
            .email(entity.getEmail())
            .password(entity.getPassword())
            .phones(
                entity
                    .getPhones()
                    .stream()
                    .map(PhoneMapper::toCoreDTO)
                    .collect(Collectors.toList())
            )
            .created(entity.getCreated())
            .modified(entity.getModified())
            .lastLogin(entity.getLastLogin())
            .isActive(entity.isActive())
            .token(entity.getToken())
            .build();
    }
    
    public static UserDetailsDTO toDetailsDTO(User entity) {
        return UserDetailsDTO
            .builder()
            .id(entity.getId())
            .name(entity.getName())
            .email(entity.getEmail())
            .phones(
                entity
                    .getPhones()
                    .stream()
                    .map(PhoneMapper::toCoreDTO)
                    .collect(Collectors.toList())
            )
            .build();
    }
}
