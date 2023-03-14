package com.jonatlop.server.core.domain.query_dto;

import lombok.Builder;

import java.util.Optional;

public record UserQueryDTO(String email) {
    @Builder public UserQueryDTO {}
}
