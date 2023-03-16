package com.jonatlop.server.framework.api.http_rest.entities;

import lombok.Value;

@Value
public class ApiResponse {
    private final Boolean success;
    private final String message;
}