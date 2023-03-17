package com.jonatlop.server.core.application.user_query;

import com.jonatlop.server.core.abstraction.exception.QueryFieldsNotSetException;
import lombok.AllArgsConstructor;
import java.util.UUID;

@AllArgsConstructor
public class UserQueryService implements UserQueryInteractor {
    private final UserQueryPersistenceGateway gateway;
    
    @Override
    public UserQueryOutputModel execute(UserQueryInputModel input) {
        if (areQueryFieldsNotSet(input)) {
            throw new QueryFieldsNotSetException();
        }
        return UserQueryOutputModel.builder()
            .userById(gateway.queryById(input.getId()))
            .userByEmail(gateway.queryByEmail(input.getEmail()))
            .usersByName(gateway.queryByName(input.getName()))
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
