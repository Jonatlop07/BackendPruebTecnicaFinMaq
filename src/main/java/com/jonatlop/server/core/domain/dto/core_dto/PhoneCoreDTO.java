package com.jonatlop.server.core.domain.dto.core_dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
@Builder
public class PhoneCoreDTO {
    private final UUID id;
    
    @NonNull
    private final String number;
    
    @NonNull
    private final String cityCode;
    
    @NonNull
    private final String countryCode;
}
