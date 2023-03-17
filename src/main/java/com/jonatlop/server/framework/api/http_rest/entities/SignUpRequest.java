package com.jonatlop.server.framework.api.http_rest.entities;

import com.jonatlop.server.core.application.user_register.UserRegisterInputModel;
import com.jonatlop.server.core.domain.core_dto.PhoneCoreDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Value
public class SignUpRequest {
    @NotBlank
    private final String name;
    
    @NotBlank
    private final String email;
    
    @NotBlank
    private final String password;
    
    private final List<Phone> phones;
    
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
                    .map(Phone::toDTO)
                    .collect(Collectors.toList())
            )
            .passwordFormatRegexp(passwordFormatRegexp)
            .build();
    }
    
    @Value
    public static class Phone {
        private final UUID id;
        private final String number;
        private final String cityCode;
        private final String countryCode;
    
        public static PhoneCoreDTO toDTO( Phone phone) {
            return PhoneCoreDTO
                .builder()
                .number(phone.getNumber())
                .cityCode(phone.getCityCode())
                .countryCode(phone.getCountryCode())
                .build();
        }
    }
}