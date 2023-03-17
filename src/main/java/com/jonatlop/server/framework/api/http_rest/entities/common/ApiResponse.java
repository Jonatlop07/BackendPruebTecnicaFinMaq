package com.jonatlop.server.framework.api.http_rest.entities.common;

import lombok.Value;

@Value
public class ApiResponse {
    Boolean success;
    String message;
}