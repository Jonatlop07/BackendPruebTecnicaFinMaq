package com.jonatlop.server.framework.api.http_rest.entity.sign_up;

import com.jonatlop.server.core.application.user_register.UserRegisterInputModel;
import com.jonatlop.server.framework.api.http_rest.entity.common.PhoneResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class SignUpRequest {
    @NotBlank
    String name;
    
    @NotBlank
    String email;
    
    @NotBlank
    String password;
    
    List<PhoneResponse> phones;
    
    public static UserRegisterInputModel toInputModel(SignUpRequest signUpRequest, String passwordFormatRegexp) {
        return UserRegisterInputModel
            .builder()
            .name(signUpRequest.getName())
            .email(signUpRequest.getEmail())
            .password(signUpRequest.getPassword())
            .phones(
                signUpRequest
                    .getPhones()
                    .stream()
                    .map(PhoneResponse::toDTO)
                    .collect(Collectors.toList())
            )
            .passwordFormatRegexp(passwordFormatRegexp)
            .build();
    }
}