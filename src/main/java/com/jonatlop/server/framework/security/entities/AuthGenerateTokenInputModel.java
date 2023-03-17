package com.jonatlop.server.framework.security.entities;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Data
public class AuthGenerateTokenInputModel {
    private final CurrentUser currentUser;
}
