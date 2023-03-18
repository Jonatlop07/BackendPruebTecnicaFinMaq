package com.jonatlop.server.framework.security.service.hash_password;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

public class BCryptHashPasswordService implements HashPasswordInteractor {
    @Override
    public String execute(String password) {
        final int STRENGTH = 10;
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(STRENGTH, new SecureRandom());
        return passwordEncoder.encode(password);
    }
}
