package com.jonatlop.server.core.domain.persistence_dto;

import lombok.Builder;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record UserPersistenceDTO(
    @NonNull UUID id,
    @NonNull String name,
    @NonNull String email,
    @NonNull String password,
    @NonNull List<String> phones,
    LocalDateTime created,
    LocalDateTime modified,
    LocalDateTime lastLogin,
    Boolean isActive
) {
    @Builder public UserPersistenceDTO {}
}
