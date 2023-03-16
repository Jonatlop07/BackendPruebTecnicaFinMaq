package com.jonatlop.server.data.db.jpa.mapper;

import com.jonatlop.server.core.domain.persistence_dto.UserPersistenceDTO;
import com.jonatlop.server.data.db.jpa.entity.User;

import java.util.stream.Collectors;

public final class UserMapper {
    private UserMapper() {}
    
    public static User toEntity(UserPersistenceDTO dto) {
        return User
            .builder()
            .id(dto.getId())
            .name(dto.getName())
            .email(dto.getEmail())
            .password(dto.getPassword())
            .created(dto.getCreated())
            .modified(dto.getModified())
            .lastLogin(dto.getLastLogin())
            .phones(
                dto
                    .getPhones()
                    .stream()
                    .map(PhoneMapper::toEntity)
                    .collect(Collectors.toList())
            )
            .build();
    }
    
    public static UserPersistenceDTO toPersistenceDTO(User entity) {
        return UserPersistenceDTO
            .builder()
            .id(entity.getId())
            .name(entity.getName())
            .email(entity.getEmail())
            .password(entity.getPassword())
            .phones(
                entity
                    .getPhones()
                    .stream()
                    .map(PhoneMapper::toPersistenceDTO)
                    .collect(Collectors.toList())
            )
            .created(entity.getCreated())
            .modified(entity.getModified())
            .lastLogin(entity.getLastLogin())
            .build();
    }
}
