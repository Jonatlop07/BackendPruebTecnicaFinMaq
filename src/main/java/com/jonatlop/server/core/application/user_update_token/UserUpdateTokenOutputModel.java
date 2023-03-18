package com.jonatlop.server.core.application.user_update_token;

import com.jonatlop.server.core.domain.dto.core_dto.UserCoreDTO;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class UserUpdateTokenOutputModel {
    private final UserCoreDTO updatedUser;
}


