package com.jonatlop.server.core.application.user_register;

import com.jonatlop.server.core.abstraction.persistence.Create;
import com.jonatlop.server.core.domain.dto.core_dto.UserCoreDTO;
import com.jonatlop.server.core.domain.persistence.UserExistsWithEmail;

public interface UserRegisterPersistenceGateway
    extends Create<UserCoreDTO, UserCoreDTO>, UserExistsWithEmail {}
