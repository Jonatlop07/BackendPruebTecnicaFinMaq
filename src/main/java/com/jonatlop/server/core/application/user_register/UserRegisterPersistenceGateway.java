package com.jonatlop.server.core.application.user_register;

import com.jonatlop.server.core.abstraction.persistence.Create;
import com.jonatlop.server.core.abstraction.persistence.Exists;
import com.jonatlop.server.core.domain.persistence_dto.UserPersistenceDTO;
import com.jonatlop.server.core.domain.query_dto.UserQueryDTO;

public interface UserRegisterPersistenceGateway
    extends Create<UserPersistenceDTO, UserPersistenceDTO>, Exists<UserQueryDTO> {}
