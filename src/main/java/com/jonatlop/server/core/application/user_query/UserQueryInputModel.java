package com.jonatlop.server.core.application.user_query;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class UserQueryInputModel {
    UUID id;
    String email;
    String name;
}
