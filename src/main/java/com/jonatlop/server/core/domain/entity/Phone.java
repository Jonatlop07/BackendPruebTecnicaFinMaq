package com.jonatlop.server.core.domain.entity;

import lombok.Getter;

@Getter
public class Phone {
    private final String number;
    private final String cityCode;
    private final String countryCode;
    
    public Phone(String number, String cityCode, String countryCode) {
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
    }
}
