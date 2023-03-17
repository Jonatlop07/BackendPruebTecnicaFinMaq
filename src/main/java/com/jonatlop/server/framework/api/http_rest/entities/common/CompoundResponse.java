package com.jonatlop.server.framework.api.http_rest.entities.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class CompoundResponse {
    @NotNull
    @JsonProperty("api_response")
    protected final ApiResponse apiResponse;
}
