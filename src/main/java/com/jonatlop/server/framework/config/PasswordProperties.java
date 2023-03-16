package com.jonatlop.server.framework.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("password")
@Data
public class PasswordProperties {
    private String format;
}