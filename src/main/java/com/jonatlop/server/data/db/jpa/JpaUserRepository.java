package com.jonatlop.server.data.db.jpa;

import com.jonatlop.server.data.db.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<User, UUID> {
    @Override
    User save(User user);
}
