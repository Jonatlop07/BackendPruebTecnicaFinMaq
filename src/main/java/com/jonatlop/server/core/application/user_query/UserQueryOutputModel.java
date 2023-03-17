package com.jonatlop.server.core.application.user_query;

import com.jonatlop.server.core.domain.dto.details_dto.UserDetailsDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
@Builder
public class UserQueryOutputModel {
    private List<UserDetailsDTO> usersByName;
    private Optional<UserDetailsDTO> userById;
    private Optional<UserDetailsDTO> userByEmail;
}
