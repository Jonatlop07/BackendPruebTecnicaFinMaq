package com.jonatlop.server.framework.api.http_rest.controller;

import com.jonatlop.server.framework.api.http_rest.entities.ApiResponse;
import com.jonatlop.server.framework.api.http_rest.entities.SignUpRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "/api/v1/user")
public interface UserResource {
    @PostMapping
    CompletableFuture<ResponseEntity<ApiResponse>> signUp(
        @Valid @RequestBody SignUpRequest request,
        ServletServerHttpRequest httpRequest
    );
}
