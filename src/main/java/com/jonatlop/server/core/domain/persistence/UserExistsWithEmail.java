package com.jonatlop.server.core.domain.persistence;

public interface UserExistsWithEmail {
    boolean existsWithEmail(String email);
}
