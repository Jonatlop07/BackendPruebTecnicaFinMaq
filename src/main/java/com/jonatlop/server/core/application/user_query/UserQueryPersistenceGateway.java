package com.jonatlop.server.core.application.user_query;

import com.jonatlop.server.core.abstraction.persistence.Query;
import com.jonatlop.server.core.domain.dto.details_dto.UserDetailsDTO;
import com.jonatlop.server.core.domain.persistence.UserQueryByEmail;
import com.jonatlop.server.core.domain.persistence.UserQueryByName;

public interface UserQueryPersistenceGateway
    extends Query<UserDetailsDTO>, UserQueryByEmail, UserQueryByName {}
