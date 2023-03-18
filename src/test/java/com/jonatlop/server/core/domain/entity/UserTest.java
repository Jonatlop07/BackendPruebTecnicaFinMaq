package com.jonatlop.server.core.domain.entity;

import com.jonatlop.server.core.util.uuid.GenerateUuidService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.UUID;

public class UserTest {
    /*
        This password regular expression requires:
        - At least 8 characters
        - One lowercase letter
        - One uppercase letter
        - One number
        - One special character (! @ # $ & * ~ _)
     */
    private final String testPasswordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$&*~_]).{8,}$";
    private final UUID userId = new GenerateUuidService().execute(null);
    private final String userName = "TestName";
    private final String userEmail = "test_email1@test.com";
    private final String userPassword = "Abc123_tr";
    private final List<Phone> userPhones = List.of();
    
    @Test
    public void givenWrongEmail_whenValidate_thenReturnFalse() {
        final List<String> wrongEmailList = List.of(
            "test_email.com.co",
            "test_email@",
            "@com"
        );
        wrongEmailList
            .stream()
            .map(
                (email) ->
                    User
                        .builder()
                        .id(userId)
                        .name(userName)
                        .email(email)
                        .password(userPassword)
                        .phones(userPhones)
                        .build()
            )
            .forEach((user) -> {
                Assertions.assertFalse(user.hasValidEmail());
            });
    }
    
    @Test
    public void givenWrongPassword_whenValidate_thenReturnFalse() {
        final List<String> wrongPasswordList = List.of(
            "Pass_1", /* Less than 8 characters */
            "password1_", /* No uppercase letters */
            "PASSWORD1_", /* No lowercase letters */
            "Password_" /* No numbers */,
            "Password1" /* No special characters */
        );
        wrongPasswordList
            .stream()
            .map(
                (password) ->
                    User
                        .builder()
                        .id(userId)
                        .name(userName)
                        .email(userEmail)
                        .password(password)
                        .phones(userPhones)
                        .build()
            )
            .forEach((user) -> {
                Assertions.assertFalse(user.hasValidPassword(testPasswordRegex));
            });
    }
    
    @Test
    public void givenValidEmail_whenValidate_thenReturnTrue() {
        final List<String> validEmailList = List.of(
            "test_email@com.co"
        );
        validEmailList
            .stream()
            .map(
                (email) ->
                    User
                        .builder()
                        .id(userId)
                        .name(userName)
                        .email(email)
                        .password(userPassword)
                        .phones(userPhones)
                        .build()
            )
            .forEach((user) -> {
                Assertions.assertTrue(user.hasValidEmail());
            });
    }
    
    @Test
    public void givenValidPassword_whenValidate_thenReturnTrue() {
        final List<String> validPasswordList = List.of(
            "Abc123_tr"
        );
        validPasswordList
            .stream()
            .map(
                (password) ->
                    User
                        .builder()
                        .id(userId)
                        .name(userName)
                        .email(userEmail)
                        .password(password)
                        .phones(userPhones)
                        .build()
            )
            .forEach((user) -> {
                Assertions.assertTrue(user.hasValidPassword(testPasswordRegex));
            });
    }
}
