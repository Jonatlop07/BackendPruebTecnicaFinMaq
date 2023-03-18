package com.jonatlop.server.framework.config;

import com.jonatlop.server.core.util.moment.GetCurrentInstantInteractor;
import com.jonatlop.server.core.util.moment.GetCurrentInstantService;
import com.jonatlop.server.core.util.uuid.GenerateUuidService;
import com.jonatlop.server.core.application.user_query.UserQueryInteractor;
import com.jonatlop.server.core.application.user_query.UserQueryPersistenceGateway;
import com.jonatlop.server.core.application.user_query.UserQueryService;
import com.jonatlop.server.core.application.user_register.UserRegisterInteractor;
import com.jonatlop.server.core.application.user_register.UserRegisterPersistenceGateway;
import com.jonatlop.server.core.application.user_register.UserRegisterService;
import com.jonatlop.server.core.application.user_update_token.UserUpdateTokenInteractor;
import com.jonatlop.server.core.application.user_update_token.UserUpdateTokenPersistenceGateway;
import com.jonatlop.server.core.application.user_update_token.UserUpdateTokenService;
import com.jonatlop.server.framework.api.http_rest.service.HttpUserRegisterService;
import com.jonatlop.server.framework.security.service.auth_generate_token.AuthGenerateJwtTokenService;
import com.jonatlop.server.framework.security.service.hash_password.HashPasswordInteractor;
import com.jonatlop.server.framework.security.service.hash_password.BCryptHashPasswordService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    @Bean
    public String getJwtSecret(@Value("${security.jwtSecret}") String jwtSecret) {
        return jwtSecret;
    }
    
    @Bean
    public int getJwtExpirationInMs(@Value("${security.jwtExpirationInMs}") int jwtExpirationInMs) {
        return jwtExpirationInMs;
    }
    
    @Bean
    public UserRegisterInteractor userRegisterInteractor(
        UserRegisterService userRegisterService,
        AuthGenerateJwtTokenService authGenerateJwtTokenService,
        UserUpdateTokenInteractor userUpdateTokenInteractor
    ) {
        return new HttpUserRegisterService(
            userRegisterService,
            authGenerateJwtTokenService,
            userUpdateTokenInteractor
        );
    }
    
    @Bean
    public UserRegisterService userRegisterService(
        UserRegisterPersistenceGateway gateway,
        HashPasswordInteractor hashPasswordInteractor,
        GenerateUuidService uuidService
    ) {
        return new UserRegisterService(gateway, hashPasswordInteractor, uuidService);
    }
    
    @Bean
    public UserUpdateTokenInteractor userUpdateTokenInteractor(UserUpdateTokenPersistenceGateway gateway) {
        return new UserUpdateTokenService(gateway);
    }
    
    @Bean
    UserQueryInteractor userQueryInteractor(UserQueryPersistenceGateway gateway) {
        return new UserQueryService(gateway);
    }
    
    @Bean
    HashPasswordInteractor hashPasswordInteractor() {
        return new BCryptHashPasswordService();
    }
    
    @Bean
    GenerateUuidService uuidService() {
        return new GenerateUuidService();
    }
    
    @Bean
    GetCurrentInstantInteractor currentInstantInteractor() {
        return new GetCurrentInstantService();
    }
}
