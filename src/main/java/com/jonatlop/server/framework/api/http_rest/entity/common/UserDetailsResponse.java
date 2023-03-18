package com.jonatlop.server.framework.api.http_rest.entity.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jonatlop.server.core.domain.dto.details_dto.UserDetailsDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Value
public class UserDetailsResponse {
    @NotBlank
    @JsonProperty
    UUID id;
    
    @NotBlank
    @JsonProperty
    String name;
    
    @NotBlank
    @JsonProperty
    String email;
    
    @NotNull
    @JsonProperty
    List<PhoneResponse> phones;
    
    public static UserDetailsResponse from(UserDetailsDTO dto) {
        return new UserDetailsResponse(
          dto.getId(),
            dto.getName(),
            dto.getEmail(),
            dto.getPhones()
                .stream()
                .map(PhoneResponse::from)
                .collect(Collectors.toList())
        );
    }
}
