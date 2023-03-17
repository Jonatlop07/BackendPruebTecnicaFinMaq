package com.jonatlop.server.data.db.jpa.repository;

import com.jonatlop.server.core.domain.core_dto.UserCoreDTO;
import com.jonatlop.server.core.domain.query_dto.UserQueryDTO;
import com.jonatlop.server.core.domain.repository.UserRepository;

import com.jonatlop.server.data.db.jpa.entity.User;
import com.jonatlop.server.data.db.jpa.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class UserRepositoryJpaAdapter implements UserRepository {
    private final JpaUserRepository repository;
    
    @Override
    public UserCoreDTO create(UserCoreDTO userDTO) {
        final UserCoreDTO userDTOWithTimestamps = UserCoreDTO
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
        final User userToSave = UserMapper.toEntity(userDTOWithTimestamps);
        final User savedUser = repository.save(userToSave);
        return UserMapper.toPersistenceDTO(savedUser);
    }
    
    @Override
    public boolean exists(UserQueryDTO userQueryDTO) {
        return repository.existsByEmail(userQueryDTO.email());
    }
    
    @Override
    public UserCoreDTO updateToken(UUID userId, String token) {
        repository.updateToken(userId, token);
        final User updatedUser = repository.findById(userId).get();
        updatedUser.getToken();
        return UserMapper.toPersistenceDTO(updatedUser);
    }
}
