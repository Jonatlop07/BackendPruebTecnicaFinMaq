package com.jonatlop.server.core.application.user_update_token;

import com.jonatlop.server.core.domain.dto.core_dto.UserCoreDTO;

import java.util.UUID;

public interface UserUpdateTokenPersistenceGateway {
    UserCoreDTO updateToken(UUID userId, String token);
}
