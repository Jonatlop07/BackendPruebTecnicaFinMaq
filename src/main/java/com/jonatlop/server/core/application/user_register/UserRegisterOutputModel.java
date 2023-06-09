package com.jonatlop.server.core.application.user_register;

import com.jonatlop.server.core.domain.dto.core_dto.UserCoreDTO;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class UserRegisterOutputModel {
    private final UserCoreDTO createdUser;
}


