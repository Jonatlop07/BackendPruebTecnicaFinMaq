package com.jonatlop.server.framework.api.http_rest.entities.user_query;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jonatlop.server.framework.api.http_rest.entities.common.ApiResponse;
import com.jonatlop.server.framework.api.http_rest.entities.common.CompoundResponse;
import com.jonatlop.server.framework.api.http_rest.entities.common.UserDetailsResponse;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.List;

@Value
public class UserQueryResponse extends CompoundResponse {
    @JsonProperty("user_by_id")
    UserDetailsResponse userById;
    
    @JsonProperty("user_by_email")
    UserDetailsResponse userByEmail;
    
    @NotNull
    @JsonProperty("users_by_name")
    List<UserDetailsResponse> usersByName;
    
    public UserQueryResponse(
        ApiResponse apiResponse,
        UserDetailsResponse userById,
        UserDetailsResponse userByEmail,
        List<UserDetailsResponse> usersByName
    ) {
        super(apiResponse);
        this.userById = userById;
        this.userByEmail = userByEmail;
        this.usersByName = usersByName;
    }
}
