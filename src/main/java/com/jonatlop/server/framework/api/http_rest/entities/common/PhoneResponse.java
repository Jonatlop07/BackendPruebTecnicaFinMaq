package com.jonatlop.server.framework.api.http_rest.entities.common;

import com.jonatlop.server.core.domain.dto.core_dto.PhoneCoreDTO;
import lombok.Value;

import java.util.UUID;

@Value
public class PhoneResponse {
    UUID id;
    String number;
    String cityCode;
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
