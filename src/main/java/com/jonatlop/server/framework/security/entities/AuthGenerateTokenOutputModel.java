package com.jonatlop.server.framework.security.entities;

import lombok.Value;

@Value
public class AuthGenerateTokenOutputModel {
    private final String token;
}
