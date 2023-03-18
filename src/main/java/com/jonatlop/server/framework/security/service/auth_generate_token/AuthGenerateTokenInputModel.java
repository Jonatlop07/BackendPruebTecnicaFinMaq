package com.jonatlop.server.framework.security.service.auth_generate_token;

import com.jonatlop.server.framework.security.entity.CurrentUser;
import lombok.Data;

@Data
public class AuthGenerateTokenInputModel {
    private final CurrentUser currentUser;
}
