package com.jonatlop.server.framework.api.http_rest.controller;

import com.jonatlop.server.framework.api.http_rest.entities.SignUpRequest;
import com.jonatlop.server.framework.api.http_rest.entities.UserRegisteredResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "/api/v1/user")
public interface UserResource {
    
    @PostMapping
    @Operation(summary = "Register a new user", description = "Register a new user", tags = {"Post"})
    @ApiResponses(value = {})
    @ResponseStatus(HttpStatus.CREATED)
    CompletableFuture<ResponseEntity<UserRegisteredResponse>> signUp(
        @Valid @RequestBody SignUpRequest request
    );
}
