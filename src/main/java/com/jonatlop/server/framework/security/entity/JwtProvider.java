package com.jonatlop.server.framework.security.entity;

import com.jonatlop.server.core.util.moment.GetCurrentInstantInteractor;
import com.jonatlop.server.framework.security.entity.CurrentUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.Instant;

@Component
@Slf4j
@AllArgsConstructor
public class JwtProvider {
    private final String jwtSecret;
    
    private final int jwtExpirationInMs;
    
    private final GetCurrentInstantInteractor currentInstantInteractor;
    
    public String generateToken(CurrentUser user) {
        final Instant now = currentInstantInteractor.execute(null);
        final Instant expiresAt = now.plusMillis(jwtExpirationInMs);
        return Jwts.builder()
            .setSubject(String.format("%s,%s", user.getId(), user.getEmail()))
            .setIssuedAt(Date.from(now))
            .setExpiration(Date.from(expiresAt))
            .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
            .compact();
    }
}
