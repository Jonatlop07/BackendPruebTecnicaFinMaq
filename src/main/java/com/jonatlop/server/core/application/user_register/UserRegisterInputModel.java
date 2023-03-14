package com.jonatlop.server.core.application.user_register;

import lombok.Builder;
import lombok.NonNull;

import java.util.List;

public record UserRegisterInputModel(
    @NonNull String name,
    @NonNull String email,
    @NonNull String password,
    @NonNull List<String> phones,
    @NonNull String passwordFormatRegex
) {
    @Builder public UserRegisterInputModel {}
}
