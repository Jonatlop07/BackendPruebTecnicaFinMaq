package com.jonatlop.server.core.domain.mapper;

import com.jonatlop.server.core.domain.entity.Phone;
import com.jonatlop.server.core.domain.persistence_dto.PhonePersistenceDTO;

public final class PhoneMapper {
    private PhoneMapper() {}
    
    public static Phone toEntity(PhonePersistenceDTO dto) {
        return Phone
            .builder()
            .id(dto.getId())
            .number(dto.getNumber())
            .cityCode(dto.getCityCode())
            .countryCode(dto.getCountryCode())
            .build();
    }
    
    public static PhonePersistenceDTO toPersistenceDTO(Phone phone) {
        return PhonePersistenceDTO
            .builder()
            .id(phone.getId())
            .number(phone.getNumber())
            .cityCode(phone.getCityCode())
            .countryCode(phone.getCountryCode())
            .build();
    }
}
