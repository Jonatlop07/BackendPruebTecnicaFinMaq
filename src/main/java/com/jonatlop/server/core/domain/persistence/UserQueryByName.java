package com.jonatlop.server.core.domain.persistence;

import com.jonatlop.server.core.domain.dto.details_dto.UserDetailsDTO;

import java.util.List;

public interface UserQueryByName {
    List<UserDetailsDTO> queryByName(String name);
}
