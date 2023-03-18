package com.jonatlop.server.data.db.jpa.repository;

import com.jonatlop.server.core.domain.dto.details_dto.UserDetailsDTO;
import com.jonatlop.server.core.util.moment.GetCurrentInstantInteractor;
import com.jonatlop.server.core.util.uuid.GenerateUuidService;
import com.jonatlop.server.core.domain.dto.core_dto.PhoneCoreDTO;
import com.jonatlop.server.core.domain.dto.core_dto.UserCoreDTO;
import com.jonatlop.server.data.db.jpa.entity.User;
import com.jonatlop.server.data.db.jpa.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserRepositoryJpaAdapterTest {
    private final UUID userId = new GenerateUuidService().execute(null);
    private final String userName = "UserName";
    private final String userEmail = "test_email1@test.co";
    private final String userPassword = "Abc123_tr";
    private final List<PhoneCoreDTO> userPhones = List.of();
    private final Instant dummyInstant = Instant.ofEpochMilli(0);
    private final String dummyToken = "I am a dummy token!";
    
    private final GetCurrentInstantInteractor getCurrentInstantInteractor = Mockito.mock(GetCurrentInstantInteractor.class);
    private final JpaUserRepository repository = Mockito.mock(JpaUserRepository.class);
    
    @BeforeEach
    public void setUpGetCurrentInstantInteractor() {
        Mockito.when(getCurrentInstantInteractor.execute(null)).thenReturn(dummyInstant);
    }
    
    @Test
    public void givenUserData_whenCreate_ThenReturnsUserCreated() {
        final UserCoreDTO userDTO =
            UserCoreDTO
                .builder()
                .id(userId)
                .name(userName)
                .email(userEmail)
                .password(userPassword)
                .phones(userPhones)
                .build();
        final UserCoreDTO expectedUser =
            UserCoreDTO
                .builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .phones(userDTO.getPhones())
                .created(dummyInstant)
                .modified(dummyInstant)
                .lastLogin(dummyInstant)
                .isActive(true)
                .build();
        final User expectedUserJpaModel = UserMapper.toEntity(expectedUser);
        Mockito.when(repository.save(expectedUserJpaModel)).thenReturn(expectedUserJpaModel);
        final UserRepositoryJpaAdapter userRepositoryJpaAdapter = new UserRepositoryJpaAdapter(repository, getCurrentInstantInteractor);
        final UserCoreDTO actualUser = userRepositoryJpaAdapter.create(expectedUser);
        Assertions.assertEquals(expectedUser, actualUser);
    }
    
    @Test
    public void givenUserEmail_whenExistsWithEmail_thenReturnsTrue() {
        Mockito.when(repository.existsByEmail(userEmail)).thenReturn(true);
        final UserRepositoryJpaAdapter userRepositoryJpaAdapter = new UserRepositoryJpaAdapter(repository, getCurrentInstantInteractor);
        Assertions.assertTrue(userRepositoryJpaAdapter.existsWithEmail(userEmail));
    }
    
    @Test
    public void givenUserIdAndToken_whenUpdateToken_thenReturnsUser() {
        final UserCoreDTO expectedUser =
            UserCoreDTO
                .builder()
                .id(userId)
                .name(userName)
                .email(userEmail)
                .password(userPassword)
                .phones(userPhones)
                .token(dummyToken)
                .build();
        final User user = UserMapper.toEntity(expectedUser);
        Mockito.when(repository.findById(userId)).thenReturn(Optional.of(user));
        final UserRepositoryJpaAdapter userRepositoryJpaAdapter = new UserRepositoryJpaAdapter(repository, getCurrentInstantInteractor);
        Assertions.assertEquals(expectedUser, userRepositoryJpaAdapter.updateToken(userId, dummyToken));
    }
    
    @Test
    public void givenExistingUserId_whenQueryById_thenReturnsUserDetails() {
        final UserCoreDTO userDTO =
            UserCoreDTO
                .builder()
                .id(userId)
                .name(userName)
                .email(userEmail)
                .password(userPassword)
                .phones(userPhones)
                .token(dummyToken)
                .build();
        
        final User user = UserMapper.toEntity(userDTO);
        final Optional<UserDetailsDTO> expectedUserDetails = Optional.of(UserMapper.toDetailsDTO(user));
        Mockito.when(repository.findById(userId)).thenReturn(Optional.of(user));
        final UserRepositoryJpaAdapter userRepositoryJpaAdapter = new UserRepositoryJpaAdapter(repository, getCurrentInstantInteractor);
        Assertions.assertEquals(expectedUserDetails, userRepositoryJpaAdapter.queryById(userId));
    }
    
    @Test
    public void givenExistingUserEmail_whenQueryByEmail_thenReturnsUserDetails() {
        final UserCoreDTO userDTO =
            UserCoreDTO
                .builder()
                .id(userId)
                .name(userName)
                .email(userEmail)
                .password(userPassword)
                .phones(userPhones)
                .token(dummyToken)
                .build();
        final User user = UserMapper.toEntity(userDTO);
        final Optional<UserDetailsDTO> expectedUserDetails = Optional.of(UserMapper.toDetailsDTO(user));
        Mockito.when(repository.findByEmail(userEmail)).thenReturn(Optional.of(user));
        final UserRepositoryJpaAdapter userRepositoryJpaAdapter = new UserRepositoryJpaAdapter(repository, getCurrentInstantInteractor);
        Assertions.assertEquals(expectedUserDetails, userRepositoryJpaAdapter.queryByEmail(userEmail));
    }
    
    @Test
    public void givenExistingUserName_whenQueryByName_thenReturnsUsersDetails() {
        final List<UserCoreDTO> userDTOs = List.of(
            UserCoreDTO
                .builder()
                .id(userId)
                .name(userName)
                .email(userEmail)
                .password(userPassword)
                .phones(userPhones)
                .token(dummyToken)
                .build()
        );
        final List<User> users =
            userDTOs
                .stream()
                .map(UserMapper::toEntity)
                .collect(Collectors.toList());
        final List<UserDetailsDTO> expectedUsersDetails =
            users
                .stream()
                .map(UserMapper::toDetailsDTO)
                .collect( Collectors.toList() );
        Mockito.when(repository.findAllByName(userName)).thenReturn(users);
        final UserRepositoryJpaAdapter userRepositoryJpaAdapter = new UserRepositoryJpaAdapter(repository, getCurrentInstantInteractor);
        Assertions.assertEquals(expectedUsersDetails, userRepositoryJpaAdapter.queryByName(userName));
    }
}
