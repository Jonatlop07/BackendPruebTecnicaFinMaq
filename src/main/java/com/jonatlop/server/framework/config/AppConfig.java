package com.jonatlop.server.framework.config;

import com.jonatlop.server.core.application.user_register.UserRegisterInteractor;
import com.jonatlop.server.core.application.user_register.UserRegisterPersistenceGateway;
import com.jonatlop.server.core.application.user_register.UserRegisterService;
import com.jonatlop.server.core.application.user_update_token.UserUpdateTokenInteractor;
import com.jonatlop.server.core.application.user_update_token.UserUpdateTokenPersistenceGateway;
import com.jonatlop.server.core.application.user_update_token.UserUpdateTokenService;
import com.jonatlop.server.framework.api.http_rest.service.HttpUserRegisterService;
import com.jonatlop.server.framework.security.service.AuthGenerateTokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public UserRegisterInteractor userRegisterInteractor(
        UserRegisterService userRegisterService,
        AuthGenerateTokenService authGenerateTokenService,
        UserUpdateTokenInteractor userUpdateTokenInteractor
    ) {
        return new HttpUserRegisterService(
            userRegisterService,
            authGenerateTokenService,
            userUpdateTokenInteractor
        );
    }
    
    @Bean
    public UserRegisterService userRegisterService(UserRegisterPersistenceGateway gateway) {
        return new UserRegisterService(gateway);
    }
    
    @Bean
    public UserUpdateTokenInteractor userUpdateTokenInteractor(UserUpdateTokenPersistenceGateway gateway) {
        return new UserUpdateTokenService(gateway);
    }
}
