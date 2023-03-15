package com.jonatlop.server.core.application.user_register;

import com.jonatlop.server.core.abstraction.exception.RequiredFieldsNotSetException;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class UserRegisterOutputModel {
    private final UUID id;
    private final Instant created;
    private final Instant modified;
    private final Instant lastLogin;
    private final boolean isActive;
    
    private UserRegisterOutputModel(Builder builder) {
        this.id = builder.id;
        this.created = builder.created;
        this.modified = builder.modified;
        this.lastLogin = builder.lastLogin;
        this.isActive = builder.isActive;
    }
    
    public static Id builder() {
        return new Builder();
    }
    
    public interface Id {
        Created id(UUID id);
    }
    
    public interface Created {
        Modified created(Instant created);
    }
    
    public interface Modified {
        LastLogin modified(Instant modified);
    }
    
    public interface LastLogin {
        IsActive lastLogin(Instant lastLogin);
    }
    
    public interface IsActive {
        Build isActive(boolean isActive);
    }
    
    public interface Build {
        UserRegisterOutputModel build();
    }
    
    public static class Builder implements Id, Created, Modified, LastLogin, IsActive, Build {
        private UUID id;
        private Instant created;
        private Instant modified;
        private Instant lastLogin;
        private boolean isActive;
        
        private Builder() {}
        
        @Override
        public Created id(UUID id) {
            this.id = id;
            return this;
        }
        
        @Override
        public Modified created(Instant created) {
            this.created = created;
            return this;
        }
        
        @Override
        public LastLogin modified(Instant modified) {
            this.modified = modified;
            return this;
        }
        
        @Override
        public IsActive lastLogin(Instant lastLogin) {
            this.lastLogin = lastLogin;
            return this;
        }
        
        @Override
        public Build isActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }
        
        @Override
        public UserRegisterOutputModel build() {
            if (id == null || created == null || modified == null || lastLogin == null) {
                throw new RequiredFieldsNotSetException();
            }
            return new UserRegisterOutputModel(this);
        }
    }
}


