package com.jonatlop.server.framework.api.http_rest.controller;

import com.jonatlop.server.core.abstraction.interactor.InteractorExecutor;
import com.jonatlop.server.core.application.user_register.UserRegisterInteractor;
import com.jonatlop.server.framework.api.http_rest.entities.SignUpRequest;
import com.jonatlop.server.framework.api.http_rest.entities.UserRegisteredResponse;
import com.jonatlop.server.framework.api.http_rest.mapper.UserRegisterResponseMapper;
import com.jonatlop.server.framework.config.PasswordProperties;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.concurrent.CompletableFuture;

@Component
@AllArgsConstructor
public class UserController implements UserResource {
    private final InteractorExecutor interactorExecutor;
    
    private UserRegisterInteractor userRegisterInteractor;
    
    private PasswordProperties passwordProperties;
    
    @Override
    public CompletableFuture<ResponseEntity<UserRegisteredResponse>> signUp(
        @Valid @RequestBody SignUpRequest request
    ) {
        return interactorExecutor.execute(
            userRegisterInteractor,
            SignUpRequest.toInputModel(request, passwordProperties.getFormat()),
            outputModel -> UserRegisterResponseMapper.toResponse(outputModel)
        );
    }
}
