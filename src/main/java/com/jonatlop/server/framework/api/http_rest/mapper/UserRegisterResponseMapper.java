package com.jonatlop.server.framework.api.http_rest.mapper;

import com.jonatlop.server.core.application.user_register.UserRegisterOutputModel;
import com.jonatlop.server.core.domain.core_dto.UserCoreDTO;
import com.jonatlop.server.framework.api.http_rest.entities.ApiResponse;
import com.jonatlop.server.framework.api.http_rest.entities.UserDataResponse;
import com.jonatlop.server.framework.api.http_rest.entities.UserRegisteredResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class UserRegisterResponseMapper {
    public static ResponseEntity<UserRegisteredResponse> toResponse(UserRegisterOutputModel outputModel) {
        final UserCoreDTO createdUser = outputModel.getCreatedUser();
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                new UserRegisteredResponse(
                    new ApiResponse(true, "Registro exitoso."),
                    UserDataResponse
                        .builder()
                        .id(createdUser.getId())
                        .created(createdUser.getCreated())
                        .modified(createdUser.getModified())
                        .lastLogin(createdUser.getLastLogin())
                        .isActive(createdUser.isActive())
                        .token(createdUser.getToken())
                        .build()
                )
            );
    }
}
