package com.jonatlop.server.framework.api.http_rest.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class UserRegisteredResponse {
    
    @NotNull
    @JsonProperty("api_response")
    ApiResponse apiResponse;
    
    @NotNull
    @JsonProperty
    UserDataResponse data;
}
