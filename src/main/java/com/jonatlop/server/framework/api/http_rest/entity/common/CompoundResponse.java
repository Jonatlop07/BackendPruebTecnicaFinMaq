package com.jonatlop.server.framework.api.http_rest.entity.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CompoundResponse {
    @NotNull
    @JsonProperty("api_response")
    protected final ApiResponse apiResponse;
}
