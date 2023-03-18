package com.jonatlop.server.core.application.user_update_token;

import lombok.Value;

import java.util.UUID;

@Value
public class UserUpdateTokenInputModel {
   UUID userId;
   String token;
}