package com.jonatlop.server.framework.api.http_rest.entity.sign_up;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Builder
@Data
public class UserRegisteredResponse {
    @NotNull
    @JsonProperty
    UUID id;
    
    
    @NotNull
    @JsonProperty
    Instant created;
    
    
    @NotNull
    @JsonProperty
    Instant modified;
    
    
    @NotNull
    @JsonProperty("last_login")
    Instant lastLogin;
    
    
    @NotNull
    @JsonProperty("is_active")
    boolean isActive;
    
    
    @NotNull
    @JsonProperty
    String token;
}
