package com.jonatlop.server.core.domain.persistence_dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class PhonePersistenceDTO {
    private final UUID id;
    private final String number;
    private final String cityCode;
    private final String countryCode;
}
