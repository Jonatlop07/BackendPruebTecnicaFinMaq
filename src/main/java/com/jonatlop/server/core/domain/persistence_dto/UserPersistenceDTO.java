package com.jonatlop.server.core.domain.persistence_dto;

import com.jonatlop.server.core.abstraction.exception.RequiredFieldsNotSetException;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
public class UserPersistenceDTO {
    private final UUID id;
    private final String name;
    private final String email;
    private final String password;
    private final List<PhonePersistenceDTO> phones;
    private final Instant created;
    private final Instant modified;
    private final Instant lastLogin;
    private final boolean isActive;
    
    private UserPersistenceDTO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
        this.phones = builder.phones;
        this.created = builder.created;
        this.modified = builder.modified;
        this.lastLogin = builder.lastLogin;
        this.isActive = builder.isActive;
    }
    
    public static Builder builder() {
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
        Build phones(List<PhonePersistenceDTO> phones);
    }
    
    public interface Build {
        Build created(Instant created);
        Build modified(Instant modified);
        Build lastLogin(Instant lastLogin);
        Build isActive(boolean isActive);
        UserPersistenceDTO build();
    }
    
    public static class Builder implements Id, Name, Email, Password, Phones, Build {
        private UUID id;
        private String name;
        private String email;
        private String password;
        private List<PhonePersistenceDTO> phones;
        private Instant created;
        private Instant modified;
        private Instant lastLogin;
        private boolean isActive;
        
        private Builder() {}
        
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
        public Build phones(List<PhonePersistenceDTO> phones) {
            this.phones = phones;
            return this;
        }
        
        public Build created(Instant created) {
            this.created = created;
            return this;
        }
        
        public Build modified(Instant modified) {
            this.modified = modified;
            return this;
        }
        
        public Build lastLogin(Instant lastLogin) {
            this.lastLogin = lastLogin;
            return this;
        }
        
        public Build isActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }
        
        @Override
        public UserPersistenceDTO build() {
            if (id == null || name == null || email == null || password == null || phones == null) {
                throw new RequiredFieldsNotSetException();
            }
            return new UserPersistenceDTO(this);
        }
    }
}