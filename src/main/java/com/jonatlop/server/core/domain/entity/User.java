package com.jonatlop.server.core.domain.entity;

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
    private final List<String> phones;
    
    private User( UserBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
        this.phones = builder.phones;
    }
    
    public boolean hasValidEmail() {
        return Pattern
            .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
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
        return new UserBuilder();
    }
    
    public interface Id {
        Name id();
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
        Build phones(List<String> phones);
    }
    
    public interface Build {
        User build();
    }
    
    private static class UserBuilder implements Build, Id, Name, Email, Password, Phones {
        private UUID id;
        private String name;
        private String email;
        private String password;
        private List<String> phones;
    
        @Override
        public Name id() {
            this.id = UUID.randomUUID();
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
        public Build phones(List<String> phones) {
            this.phones = phones;
            return this;
        }
    
        @Override
        public User build() {
            return new User(this);
        }
    }
}
