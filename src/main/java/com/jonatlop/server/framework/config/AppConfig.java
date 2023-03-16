package com.jonatlop.server.framework.config;

import com.jonatlop.server.core.application.user_register.UserRegisterInteractor;
import com.jonatlop.server.core.application.user_register.UserRegisterPersistenceGateway;
import com.jonatlop.server.core.application.user_register.UserRegisterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public UserRegisterInteractor userRegisterInteractor(UserRegisterPersistenceGateway gateway) {
        return new UserRegisterService(gateway);
    }
}
