package com.jonatlop.server.framework.api.http_rest.controller;

import com.jonatlop.server.core.abstraction.interactor.InteractorExecutor;
import com.jonatlop.server.core.application.user_query.UserQueryInputModel;
import com.jonatlop.server.core.application.user_query.UserQueryInteractor;
import com.jonatlop.server.core.application.user_register.UserRegisterInteractor;
import com.jonatlop.server.framework.api.http_rest.entity.sign_up.SignUpRequest;
import com.jonatlop.server.framework.api.http_rest.entity.sign_up.SignUpResponse;
import com.jonatlop.server.framework.api.http_rest.entity.user_query.UserQueryResponse;
import com.jonatlop.server.framework.api.http_rest.mapper.UserQueryResponseMapper;
import com.jonatlop.server.framework.api.http_rest.mapper.UserRegisterResponseMapper;
import com.jonatlop.server.framework.config.PasswordProperties;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
@AllArgsConstructor
public class UserController implements UserResource {
    private final InteractorExecutor interactorExecutor;
    
    private final UserRegisterInteractor userRegisterInteractor;
    private final UserQueryInteractor userQueryInteractor;
    
    private final PasswordProperties passwordProperties;
    
    @Override
    public CompletableFuture<ResponseEntity<SignUpResponse>> signUp(SignUpRequest request) {
        return interactorExecutor.execute(
            userRegisterInteractor,
            SignUpRequest.toInputModel(request, passwordProperties.getFormat()),
            UserRegisterResponseMapper::toResponse
        );
    }
    
    @Override
    public CompletableFuture<ResponseEntity<UserQueryResponse>> query(
        UUID id,
        String email,
        String name
    ) {
        return interactorExecutor.execute(
            userQueryInteractor,
            UserQueryInputModel
                .builder()
                .id(id)
                .email(email)
                .name(name)
                .build(),
            UserQueryResponseMapper::toResponse
        );
    }
}
