package com.jonatlop.server.framework.api.http_rest.controller;

import com.jonatlop.server.framework.api.http_rest.entity.sign_up.SignUpRequest;
import com.jonatlop.server.framework.api.http_rest.entity.sign_up.SignUpResponse;
import com.jonatlop.server.framework.api.http_rest.entity.user_query.UserQueryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "/api/v1/user")
public interface UserResource {
    
    @PostMapping
    @Operation(summary = "Register a new user", description = "Register a new user", tags = {"User"})
    @ApiResponses(value = {})
    @ResponseStatus(HttpStatus.CREATED)
    CompletableFuture<ResponseEntity<SignUpResponse>> signUp(
        @Valid @RequestBody SignUpRequest request
    );
    
    @GetMapping
    @Operation(summary = "Query users", description = "Query users by id, email or name", tags = {"User"})
    @ApiResponses(value = {})
    @ResponseStatus(HttpStatus.OK)
    CompletableFuture<ResponseEntity<UserQueryResponse>> query(
        @RequestParam(required = false) UUID id,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) String name
    );
}
