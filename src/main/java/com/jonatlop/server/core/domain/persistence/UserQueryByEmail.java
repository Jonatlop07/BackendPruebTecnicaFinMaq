package com.jonatlop.server.core.domain.persistence;

import com.jonatlop.server.core.domain.dto.details_dto.UserDetailsDTO;

import java.util.Optional;

public interface UserQueryByEmail {
    Optional<UserDetailsDTO> queryByEmail(String email);
}
