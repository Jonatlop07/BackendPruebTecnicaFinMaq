package com.jonatlop.server.core.application.user_register;

import lombok.Builder;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserRegisterOutputModel(
    @NonNull UUID id,
    @NonNull LocalDateTime created,
    @NonNull LocalDateTime modified,
    @NonNull LocalDateTime lastLogin,
    @NonNull boolean isActive
) {
    @Builder public UserRegisterOutputModel {}
}
