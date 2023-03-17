package com.jonatlop.server.core.application.user_update_token;

import com.jonatlop.server.core.domain.core_dto.UserCoreDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserUpdateTokenOutputModel {
    private final UserCoreDTO updatedUser;
}


