package com.jonatlop.server.framework.security.service;

import com.jonatlop.server.framework.security.entities.CurrentUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.Instant;

@Component
@Slf4j
public class JwtProvider {
    @Value("${security.jwtSecret}")
    private String jwtSecret;
    
    @Value("${security.jwtExpirationInMs}")
    private int jwtExpirationInMs;
    
    public String generateToken(CurrentUser user) {
        final Instant now = Instant.now();
        final Instant expiresAt = now.plusMillis(jwtExpirationInMs);
        return Jwts.builder()
            .setSubject(String.format("%s,%s", user.getId(), user.getEmail()))
            .setIssuedAt(Date.from(now))
            .setExpiration(Date.from(expiresAt))
            .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
            .compact();
    }
}
