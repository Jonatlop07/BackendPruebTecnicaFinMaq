package com.jonatlop.server.core.domain.exception;

import com.jonatlop.server.core.abstraction.exception.CoreExceptionCodes;

public class UserWithEmailAlreadyExistsException extends UserException {
    public UserWithEmailAlreadyExistsException() {
        code = CoreExceptionCodes.USER_ALREADY_EXISTS;
        message = "El correo ya está registrado";
    }
}
