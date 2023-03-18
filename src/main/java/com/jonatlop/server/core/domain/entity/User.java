package com.jonatlop.server.core.domain.entity;

import com.jonatlop.server.core.abstraction.exception.RequiredFieldsNotSetException;
import lombok.Getter;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Getter
public class User {
    private final UUID id;
    private final String name;
    private final String email;
    private final String password;
    private final List<Phone> phones;
    
    private User(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
        this.phones = builder.phones;
    }
    
    public boolean hasValidEmail() {
        return Pattern
            .compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
            .matcher(this.email)
            .matches();
    }
    
    public boolean hasValidPassword(String regExp) {
        return Pattern
            .compile(regExp)
            .matcher(this.password)
            .matches();
    }
    
    public static Id builder() {
        return new Builder();
    }
    
    public interface Id {
        Name id(UUID id);
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
        Build phones(List<Phone> phones);
    }
    
    public interface Build {
        User build();
    }
    
    public static class Builder implements Build, Id, Name, Email, Password, Phones {
        private UUID id;
        private String name;
        private String email;
        private String password;
        private List<Phone> phones;
    
        @Override
        public Name id(UUID id) {
            this.id = id;
            return this;
        }
    
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
        public Build phones(List<Phone> phones) {
            this.phones = phones;
            return this;
        }
    
        @Override
        public User build() {
            if (id == null || name == null || email == null || password == null || phones == null) {
                throw new RequiredFieldsNotSetException();
            }
            return new User(this);
        }
    }
}
