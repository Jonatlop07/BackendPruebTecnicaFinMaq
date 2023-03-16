package com.jonatlop.server.data.db.jpa.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Entity
@Table(name = "phones")
@Builder
@Getter
public class Phone {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    @Column(name = "number")
    private String number;
    
    @Column(name = "city_code")
    private String cityCode;
    
    @Column(name = "country_code")
    private String countryCode;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    
    protected Phone () {}
}