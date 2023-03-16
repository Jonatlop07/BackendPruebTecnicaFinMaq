package com.jonatlop.server.framework.api.http_rest.mapper;

import com.jonatlop.server.core.application.user_register.UserRegisterOutputModel;
import com.jonatlop.server.framework.api.http_rest.entities.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServletServerHttpRequest;

public final class UserRegisterResponseMapper {
    public static ResponseEntity<ApiResponse> toResponse( UserRegisterOutputModel outputModel, ServletServerHttpRequest httpRequest) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                new ApiResponse(true, "Registro exitoso.")
            );
    }
}
