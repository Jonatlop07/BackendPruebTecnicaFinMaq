package com.jonatlop.server.framework.api.http_rest.entity.sign_up;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jonatlop.server.framework.api.http_rest.entity.common.ApiResponse;
import com.jonatlop.server.framework.api.http_rest.entity.common.CompoundResponse;
import jakarta.validation.constraints.NotNull;

public class SignUpResponse extends CompoundResponse {
    @NotNull
    @JsonProperty
    UserRegisteredResponse data;
    
    public SignUpResponse( ApiResponse apiResponse, UserRegisteredResponse data ) {
        super(apiResponse);
        this.data = data;
    }
}
