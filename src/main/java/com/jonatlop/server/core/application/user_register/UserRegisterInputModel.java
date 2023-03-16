package com.jonatlop.server.core.application.user_register;

import com.jonatlop.server.core.abstraction.exception.RequiredFieldsNotSetException;
import com.jonatlop.server.core.domain.persistence_dto.PhonePersistenceDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class UserRegisterInputModel {
    
    private final String name;
    private final String email;
    private final String password;
    private final List<PhonePersistenceDTO> phones;
    private final String passwordFormatRegexp;
    
    private UserRegisterInputModel(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
        this.phones = builder.phones;
        this.passwordFormatRegexp = builder.passwordFormatRegexp;
    }
    
    public static RequiredName builder() {
        return new Builder();
    }
    
    public interface RequiredName {
        RequiredEmail name(String name);
    }
    
    public interface RequiredEmail {
        RequiredPassword email(String email);
    }
    
    public interface RequiredPassword {
        RequiredPhones password(String password);
    }
    
    public interface RequiredPhones {
        RequiredPasswordFormatRegexp phones(List<PhonePersistenceDTO> phones);
    }
    
    public interface RequiredPasswordFormatRegexp {
        Build passwordFormatRegexp(String passwordFormatRegexp);
    }
    
    public interface Build {
        UserRegisterInputModel build();
    }
    
    public static class Builder implements RequiredName, RequiredEmail, RequiredPassword, RequiredPhones, RequiredPasswordFormatRegexp, Build {
        private String name;
        private String email;
        private String password;
        private List<PhonePersistenceDTO> phones;
        private String passwordFormatRegexp;
        
        private Builder() {}
        
        @Override
        public RequiredEmail name(String name) {
            this.name = name;
            return this;
        }
        
        @Override
        public RequiredPassword email(String email) {
            this.email = email;
            return this;
        }
        
        @Override
        public RequiredPhones password(String password) {
            this.password = password;
            return this;
        }
        
        @Override
        public RequiredPasswordFormatRegexp phones(List<PhonePersistenceDTO> phones) {
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