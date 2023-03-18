package com.jonatlop.server.core.domain.dto.details_dto;

import com.jonatlop.server.core.abstraction.exception.RequiredFieldsNotSetException;
import com.jonatlop.server.core.domain.dto.core_dto.PhoneCoreDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@EqualsAndHashCode
public class UserDetailsDTO {
    private final UUID id;
    private final String name;
    private final String email;
    private final List<PhoneCoreDTO> phones;
    
    private UserDetailsDTO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.phones = builder.phones;
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
        Phones email(String email);
    }
    
    public interface Phones {
        Build phones(List<PhoneCoreDTO> phones);
    }
    
    public interface Build {
        UserDetailsDTO build();
    }
    
    public static class Builder implements Id, Name, Email, Phones, Build {
        private UUID id;
        private String name;
        private String email;
        private List<PhoneCoreDTO> phones;
        
        private Builder() {}
        
        @Override
        public Name id( UUID id) {
            this.id = id;
            return this;
        }
        
        @Override
        public Email name( String name) {
            this.name = name;
            return this;
        }
        
        @Override
        public Phones email( String email) {
            this.email = email;
            return this;
        }
        
        @Override
        public Build phones( List<PhoneCoreDTO> phones) {
            this.phones = phones;
            return this;
        }
        
        @Override
        public UserDetailsDTO build() {
            if (id == null || name == null || email == null || phones == null) {
                throw new RequiredFieldsNotSetException();
            }
            return new UserDetailsDTO(this);
        }
    }
}
