package com.jonatlop.server.framework.api.http_rest.entity.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jonatlop.server.core.domain.dto.core_dto.PhoneCoreDTO;
import lombok.Value;

import java.util.UUID;

@Value
public class PhoneResponse {
    @JsonProperty
    UUID id;
    
    @JsonProperty
    String number;
    
    @JsonProperty("city_code")
    String cityCode;
    
    @JsonProperty("country_code")
    String countryCode;
    
    public static PhoneCoreDTO toDTO(PhoneResponse phone) {
        return PhoneCoreDTO
            .builder()
            .number(phone.getNumber())
            .cityCode(phone.getCityCode())
            .countryCode(phone.getCountryCode())
            .build();
    }
    
    public static PhoneResponse from(PhoneCoreDTO dto) {
        return new PhoneResponse(
            dto.getId(),
            dto.getNumber(),
            dto.getNumber(),
            dto.getCountryCode()
        );
    }
}
