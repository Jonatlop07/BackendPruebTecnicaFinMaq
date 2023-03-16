package com.jonatlop.server.data.db.jpa.repository;

import com.jonatlop.server.core.domain.persistence_dto.UserPersistenceDTO;
import com.jonatlop.server.core.domain.query_dto.UserQueryDTO;
import com.jonatlop.server.core.domain.repository.UserRepository;

import com.jonatlop.server.data.db.jpa.entity.User;
import com.jonatlop.server.data.db.jpa.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
@AllArgsConstructor
public class UserRepositoryJpaAdapter implements UserRepository {
    private final JpaUserRepository repository;
    
    @Override
    public UserPersistenceDTO create(UserPersistenceDTO userDTO) {
        final UserPersistenceDTO userDTOWithTimestamps = UserPersistenceDTO
            .builder()
            .id(userDTO.getId())
            .name(userDTO.getName())
            .email(userDTO.getEmail())
            .password(userDTO.getPassword())
            .phones(userDTO.getPhones())
            .created(Instant.now())
            .modified(Instant.now())
            .lastLogin(Instant.now())
            .isActive(true)
            .build();
        final User userToSave = UserMapper.toEntity(userDTO);
        final User savedUser = repository.save(userToSave);
        return UserMapper.toPersistenceDTO(savedUser);
    }
    
    @Override
    public boolean exists(UserQueryDTO userQueryDTO) {
        return repository.existsByEmail(userQueryDTO.email());
    }
}
