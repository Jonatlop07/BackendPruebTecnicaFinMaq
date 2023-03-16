package com.jonatlop.server.core.domain.mapper;

import com.jonatlop.server.core.domain.entity.Phone;
import com.jonatlop.server.core.domain.persistence_dto.PhonePersistenceDTO;

public final class PhoneMapper {
    private PhoneMapper() {}
    
    public static Phone toEntity(PhonePersistenceDTO dto) {
        return new Phone(dto.getNumber(), dto.getCityCode(), dto.getCountryCode());
    }
    
    public static PhonePersistenceDTO toPersistenceDTO(Phone phone) {
        return new PhonePersistenceDTO(phone.getNumber(), phone.getCityCode(), phone.getCountryCode());
    }
}
