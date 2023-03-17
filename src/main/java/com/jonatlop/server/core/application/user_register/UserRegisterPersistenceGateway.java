package com.jonatlop.server.core.application.user_register;

import com.jonatlop.server.core.abstraction.persistence.Create;
import com.jonatlop.server.core.abstraction.persistence.Exists;
import com.jonatlop.server.core.domain.core_dto.UserCoreDTO;
import com.jonatlop.server.core.domain.query_dto.UserQueryDTO;

public interface UserRegisterPersistenceGateway
    extends Create<UserCoreDTO, UserCoreDTO>, Exists<UserQueryDTO> {}
