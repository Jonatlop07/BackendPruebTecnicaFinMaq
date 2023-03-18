package com.jonatlop.server.framework.security.entity;

import com.jonatlop.server.core.domain.dto.core_dto.UserCoreDTO;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Setter
@Getter
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "name", "email", "password"})
public class CurrentUser implements UserDetails {
    private UUID id;
    
    private String name;
    
    private String email;
    
    private String password;
    
    private boolean isActive;
    
    private Collection<? extends GrantedAuthority> authorities;
    
    public static CurrentUser from(UserCoreDTO user) {
        return CurrentUser
            .builder()
            .id(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .password(user.getPassword())
            .isActive(user.isActive())
            .authorities(
                Collections.singletonList(
                    new SimpleGrantedAuthority("ROLE_USER")
                )
            )
            .build();
    }
    
    public String getUsername() {
        return email;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return isActive;
    }
}