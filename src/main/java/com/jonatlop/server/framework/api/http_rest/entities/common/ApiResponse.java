package com.jonatlop.server.framework.api.http_rest.entities.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import org.springframework.http.HttpStatus;

@Value
public class ApiResponse {
    @JsonProperty
    Boolean success;
    
    @JsonProperty
    String message;
    
    @JsonProperty
    HttpStatus httpStatus;
    
    @JsonProperty
    int httpStatusCode;
}