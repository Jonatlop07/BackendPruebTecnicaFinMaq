package com.jonatlop.server.framework.api.http_rest.mapper;

import com.jonatlop.server.core.application.user_register.UserRegisterOutputModel;
import com.jonatlop.server.core.domain.dto.core_dto.UserCoreDTO;
import com.jonatlop.server.framework.api.http_rest.entities.common.ApiResponse;
import com.jonatlop.server.framework.api.http_rest.entities.sign_up.UserRegisteredResponse;
import com.jonatlop.server.framework.api.http_rest.entities.sign_up.SignUpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class UserRegisterResponseMapper {
    public static ResponseEntity<SignUpResponse> toResponse( UserRegisterOutputModel outputModel) {
        final HttpStatus httpStatus = HttpStatus.CREATED;
        final UserCoreDTO createdUser = outputModel.getCreatedUser();
        return ResponseEntity
            .status(httpStatus)
            .body(
                new SignUpResponse(
                    new ApiResponse(true, "Registro exitoso.", httpStatus, httpStatus.value()),
                    UserRegisteredResponse
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
