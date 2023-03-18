package com.jonatlop.server.core.application.user_query;

import com.jonatlop.server.core.abstraction.exception.QueryFieldsNotSetException;
import com.jonatlop.server.core.domain.dto.details_dto.UserDetailsDTO;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class UserQueryService implements UserQueryInteractor {
    private final UserQueryPersistenceGateway gateway;
    
    @Override
    public UserQueryOutputModel execute(UserQueryInputModel input) {
        if (areQueryFieldsNotSet(input)) {
            throw new QueryFieldsNotSetException();
        }
        Optional<UserDetailsDTO> userById = Optional.empty();
        Optional<UserDetailsDTO> userByEmail = Optional.empty();
        List<UserDetailsDTO> usersByName = new ArrayList<>();
        if (isIdSet(input.getId())) {
            userById = gateway.queryById(input.getId());
        }
        if (isEmailSet(input.getEmail())) {
            userByEmail = gateway.queryByEmail(input.getEmail());
        }
        if (isNameSet(input.getName())) {
            usersByName = gateway.queryByName(input.getName());
        }
        return UserQueryOutputModel.builder()
            .userById(userById)
            .userByEmail(userByEmail)
            .usersByName(usersByName)
            .build();
    }
    
    private boolean areQueryFieldsNotSet(UserQueryInputModel input) {
        return !isIdSet(input.getId())
            && !isEmailSet(input.getEmail())
            && !isNameSet(input.getName());
    }
    
    private boolean isIdSet(UUID id) {
        return id != null;
    }
    
    private boolean isEmailSet(String email) {
        return email != null && !email.isBlank();
    }
    
    private boolean isNameSet(String name) {
        return name != null && !name.isBlank();
    }
}
