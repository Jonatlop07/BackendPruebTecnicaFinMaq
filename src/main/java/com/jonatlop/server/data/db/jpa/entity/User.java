package com.jonatlop.server.data.db.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    
    @Id
    private UUID id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @Column
    private Instant created;
    
    @Column
    private Instant modified;
    
    @Column(name = "last_login")
    private Instant lastLogin;
    
    @Column(name = "is_active")
    private boolean isActive;
    
    @Column
    private String token;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private List<Phone> phones;
}
