package com.jonatlop.server.core.application.user_update_token;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class UserUpdateTokenService implements UserUpdateTokenInteractor {
    private final UserUpdateTokenPersistenceGateway gateway;
    
    @Override
    public UserUpdateTokenOutputModel execute(UserUpdateTokenInputModel input) {
        return new UserUpdateTokenOutputModel(gateway.updateToken(input.getUserId(), input.getToken()));
    }
}
