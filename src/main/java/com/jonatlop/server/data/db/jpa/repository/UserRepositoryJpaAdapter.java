package com.jonatlop.server.data.db.jpa.repository;

import com.jonatlop.server.core.domain.dto.core_dto.UserCoreDTO;
import com.jonatlop.server.core.domain.dto.details_dto.UserDetailsDTO;
import com.jonatlop.server.core.domain.repository.UserRepository;

import com.jonatlop.server.core.util.moment.GetCurrentInstantInteractor;
import com.jonatlop.server.data.db.jpa.entity.User;
import com.jonatlop.server.data.db.jpa.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class UserRepositoryJpaAdapter implements UserRepository {
    private final JpaUserRepository repository;
    private final GetCurrentInstantInteractor getCurrentInstantInteractor;
    
    @Override
    public UserCoreDTO create(UserCoreDTO userDTO) {
        final Instant now = getCurrentInstantInteractor.execute(null);
        final UserCoreDTO userDTOWithTimestamps = UserCoreDTO
            .builder()
            .id(userDTO.getId())
            .name(userDTO.getName())
            .email(userDTO.getEmail())
            .password(userDTO.getPassword())
            .phones(userDTO.getPhones())
            .created(now)
            .modified(now)
            .lastLogin(now)
            .isActive(true)
            .build();
        final User userToSave = UserMapper.toEntity(userDTOWithTimestamps);
        final User savedUser = repository.save(userToSave);
        return UserMapper.toCoreDTO(savedUser);
    }
    
    @Override
    public boolean existsWithEmail(String email) {
        return repository.existsByEmail(email);
    }
    
    @Override
    public UserCoreDTO updateToken(UUID userId, String token) {
        repository.updateToken(userId, token);
        return repository
            .findById(userId)
            .map(UserMapper::toCoreDTO)
            .orElse( null);
    }
    
    @Override
    public Optional<UserDetailsDTO> queryById(UUID id) {
        return repository
            .findById(id)
            .map(UserMapper::toDetailsDTO);
    }
    
    @Override
    public Optional<UserDetailsDTO> queryByEmail(String email) {
        return repository   
            .findByEmail(email)
            .map(UserMapper::toDetailsDTO);
    }
    
    @Override
    public List<UserDetailsDTO> queryByName(String name) {
        return repository
            .findAllByName(name)
            .stream()
            .map(UserMapper::toDetailsDTO)
            .collect(Collectors.toList());
    }
}
