package com.jonatlop.server.data.db.jpa.repository;

import com.jonatlop.server.data.db.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);
    
    @Modifying(clearAutomatically = true)
    @Query("update User u set u.token = :token where u.id = :userId")
    int updateToken(UUID userId, String token);
}
