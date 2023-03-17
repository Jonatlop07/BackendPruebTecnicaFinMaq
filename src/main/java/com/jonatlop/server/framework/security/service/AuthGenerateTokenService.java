package com.jonatlop.server.framework.security.service;

import com.jonatlop.server.core.abstraction.interactor.Interactor;
import com.jonatlop.server.framework.security.entities.AuthGenerateTokenInputModel;
import com.jonatlop.server.framework.security.entities.AuthGenerateTokenOutputModel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthGenerateTokenService implements Interactor<AuthGenerateTokenInputModel, AuthGenerateTokenOutputModel> {
    @Autowired
    private JwtProvider jwtProvider;
    
    @Override
    public AuthGenerateTokenOutputModel execute(AuthGenerateTokenInputModel input) {
        return new AuthGenerateTokenOutputModel(jwtProvider.generateToken(input.getCurrentUser()));
    }
}
