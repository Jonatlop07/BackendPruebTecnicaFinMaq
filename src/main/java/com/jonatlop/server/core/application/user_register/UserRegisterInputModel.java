package com.jonatlop.server.core.application.user_register;

import com.jonatlop.server.core.abstraction.exception.RequiredFieldsNotSetException;
import com.jonatlop.server.core.domain.dto.core_dto.PhoneCoreDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class UserRegisterInputModel {
    
    private final String name;
    private final String email;
    private final String password;
    private final List<PhoneCoreDTO> phones;
    private final String passwordFormatRegexp;
    
    private UserRegisterInputModel(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
        this.phones = builder.phones;
        this.passwordFormatRegexp = builder.passwordFormatRegexp;
    }
    
    public static Name builder() {
        return new Builder();
    }
    
    public interface Name {
        Email name(String name);
    }
    
    public interface Email {
        Password email(String email);
    }
    
    public interface Password {
        Phones password(String password);
    }
    
    public interface Phones {
        PasswordFormatRegexp phones(List<PhoneCoreDTO> phones);
    }
    
    public interface PasswordFormatRegexp {
        Build passwordFormatRegexp(String passwordFormatRegexp);
    }
    
    public interface Build {
        UserRegisterInputModel build();
    }
    
    public static class Builder implements Name, Email, Password, Phones, PasswordFormatRegexp, Build {
        private String name;
        private String email;
        private String password;
        private List<PhoneCoreDTO> phones;
        private String passwordFormatRegexp;
        
        private Builder() {}
        
        @Override
        public Email name(String name) {
            this.name = name;
            return this;
        }
        
        @Override
        public Password email(String email) {
            this.email = email;
            return this;
        }
        
        @Override
        public Phones password(String password) {
            this.password = password;
            return this;
        }
        
        @Override
        public PasswordFormatRegexp phones(List<PhoneCoreDTO> phones) {
            this.phones = phones;
            return this;
        }
        
        @Override
        public Build passwordFormatRegexp(String passwordFormatRegexp) {
            this.passwordFormatRegexp = passwordFormatRegexp;
            return this;
        }
        
        @Override
        public UserRegisterInputModel build() {
            if (name == null || email == null || password == null || phones == null || passwordFormatRegexp == null) {
                throw new RequiredFieldsNotSetException();
            }
            return new UserRegisterInputModel(this);
        }
    }
    
}