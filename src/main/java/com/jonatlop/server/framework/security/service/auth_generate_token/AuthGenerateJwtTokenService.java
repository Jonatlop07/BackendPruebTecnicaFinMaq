package com.jonatlop.server.framework.security.service.auth_generate_token;

import com.jonatlop.server.core.abstraction.interactor.Interactor;
import com.jonatlop.server.framework.security.entity.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthGenerateJwtTokenService implements Interactor<AuthGenerateTokenInputModel, AuthGenerateTokenOutputModel> {
    private JwtProvider jwtProvider;
    
    @Override
    public AuthGenerateTokenOutputModel execute(AuthGenerateTokenInputModel input) {
        return new AuthGenerateTokenOutputModel(jwtProvider.generateToken(input.getCurrentUser()));
    }
}
