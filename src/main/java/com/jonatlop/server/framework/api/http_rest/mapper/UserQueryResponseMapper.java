package com.jonatlop.server.framework.api.http_rest.mapper;

import com.jonatlop.server.core.application.user_query.UserQueryOutputModel;
import com.jonatlop.server.framework.api.http_rest.entity.common.ApiResponse;
import com.jonatlop.server.framework.api.http_rest.entity.common.UserDetailsResponse;
import com.jonatlop.server.framework.api.http_rest.entity.user_query.UserQueryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.stream.Collectors;

public final class UserQueryResponseMapper {
    public static ResponseEntity<UserQueryResponse> toResponse(UserQueryOutputModel outputModel) {
        final HttpStatus httpStatus = HttpStatus.OK;
        return ResponseEntity
            .status(httpStatus)
            .body(
                new UserQueryResponse(
                    new ApiResponse(true, "Consulta exitosa.", httpStatus, httpStatus.value()),
                    outputModel
                        .getUserById()
                        .map(UserDetailsResponse::from)
                        .orElse(null),
                    outputModel
                        .getUserByEmail()
                        .map(UserDetailsResponse::from)
                        .orElse(null),
                    outputModel
                        .getUsersByName()
                        .stream()
                        .map(UserDetailsResponse::from)
                        .collect(Collectors.toList())
                )
            );
    }
}
