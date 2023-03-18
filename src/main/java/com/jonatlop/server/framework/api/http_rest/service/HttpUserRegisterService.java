package com.jonatlop.server.framework.api.http_rest.service;

import com.jonatlop.server.core.application.user_register.UserRegisterInputModel;
import com.jonatlop.server.core.application.user_register.UserRegisterInteractor;
import com.jonatlop.server.core.application.user_register.UserRegisterOutputModel;
import com.jonatlop.server.core.application.user_register.UserRegisterService;
import com.jonatlop.server.core.application.user_update_token.UserUpdateTokenInputModel;
import com.jonatlop.server.core.application.user_update_token.UserUpdateTokenInteractor;
import com.jonatlop.server.core.application.user_update_token.UserUpdateTokenOutputModel;
import com.jonatlop.server.core.domain.dto.core_dto.UserCoreDTO;
import com.jonatlop.server.framework.security.service.auth_generate_token.AuthGenerateTokenInputModel;
import com.jonatlop.server.framework.security.service.auth_generate_token.AuthGenerateTokenOutputModel;
import com.jonatlop.server.framework.security.entity.CurrentUser;
import com.jonatlop.server.framework.security.service.auth_generate_token.AuthGenerateJwtTokenService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HttpUserRegisterService implements UserRegisterInteractor {
    private final UserRegisterService userRegisterService;
    private final AuthGenerateJwtTokenService authGenerateJwtTokenService;
    private final UserUpdateTokenInteractor userUpdateTokenInteractor;
    
    @Override
    @Transactional
    public UserRegisterOutputModel execute(UserRegisterInputModel input) {
        final UserRegisterOutputModel userRegisterOutputModel = userRegisterService.execute(input);
        final UserCoreDTO createdUser = userRegisterOutputModel.getCreatedUser();
        final CurrentUser currentUser = CurrentUser.from(createdUser);
        final AuthGenerateTokenOutputModel authGenerateTokenOutputModel = authGenerateJwtTokenService.execute(
            new AuthGenerateTokenInputModel(currentUser)
        );
        final UserUpdateTokenOutputModel userUpdateTokenOutputModel = userUpdateTokenInteractor.execute(
            new UserUpdateTokenInputModel(
                createdUser.getId(),
                authGenerateTokenOutputModel.getToken()
            )
        );
        return new UserRegisterOutputModel(userUpdateTokenOutputModel.getUpdatedUser());
    }
}
