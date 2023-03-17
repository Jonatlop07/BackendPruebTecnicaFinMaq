package com.jonatlop.server.core.domain.repository;

import com.jonatlop.server.core.application.user_register.UserRegisterPersistenceGateway;
import com.jonatlop.server.core.application.user_update_token.UserUpdateTokenPersistenceGateway;

public interface UserRepository extends UserRegisterPersistenceGateway, UserUpdateTokenPersistenceGateway {
}
