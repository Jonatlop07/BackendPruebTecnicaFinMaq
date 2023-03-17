package com.jonatlop.server.data.db.jpa.mapper;

import com.jonatlop.server.core.domain.dto.core_dto.PhoneCoreDTO;
import com.jonatlop.server.data.db.jpa.entity.Phone;

public final class PhoneMapper {
    private PhoneMapper() {}
    
    public static Phone toEntity( PhoneCoreDTO dto) {
        return Phone
            .builder()
            .id(dto.getId())
            .number(dto.getNumber())
            .cityCode(dto.getCityCode())
            .countryCode(dto.getCountryCode())
            .build();
    }
    
    public static PhoneCoreDTO toCoreDTO( Phone phone) {
        return PhoneCoreDTO
            .builder()
            .id(phone.getId())
            .number(phone.getNumber())
            .cityCode(phone.getCityCode())
            .countryCode(phone.getCountryCode())
            .build();
    }
}
