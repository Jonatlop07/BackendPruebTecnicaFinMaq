package com.jonatlop.server.data.db.jpa;

import com.jonatlop.server.core.domain.persistence_dto.UserPersistenceDTO;
import com.jonatlop.server.core.domain.query_dto.UserQueryDTO;
import com.jonatlop.server.core.domain.repository.UserRepository;

import com.jonatlop.server.data.db.jpa.entity.User;
import com.jonatlop.server.data.db.jpa.mapper.UserMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class UserRepositoryJpaAdapter implements UserRepository {
    private final JpaUserRepository repository;
    
    @Override
    public UserPersistenceDTO create(UserPersistenceDTO userDTO) {
        final User userToSave = UserMapper.toEntity(userDTO);
        final User savedUser = repository.save(userToSave);
        return UserMapper.toPersistenceDTO(savedUser);
    }
    
    @Override
    public boolean exists(UserQueryDTO userQueryDTO) {
        return false;
    }
}
