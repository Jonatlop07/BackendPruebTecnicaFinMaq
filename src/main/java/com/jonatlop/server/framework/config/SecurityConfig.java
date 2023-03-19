package com.jonatlop.server.framework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, @Value("${origin.url}") String originUrl) throws Exception {
        return http
            .csrf().disable()
            .cors().configurationSource(request -> {
                CorsConfiguration corsConfiguration = new CorsConfiguration();
                corsConfiguration.setAllowedOrigins(List.of(originUrl));
                corsConfiguration.setAllowedMethods(List.of("GET", "POST"));
                corsConfiguration.setAllowedHeaders(List.of("*"));
                return corsConfiguration;
            })
            .and()
            .build();
    }
}
