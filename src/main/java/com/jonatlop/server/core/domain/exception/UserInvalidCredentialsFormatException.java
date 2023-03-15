package com.jonatlop.server.core.domain.exception;

import com.jonatlop.server.core.abstraction.exception.CoreExceptionCodes;

public final class UserInvalidCredentialsFormatException extends UserException {
    public UserInvalidCredentialsFormatException() {
        code = CoreExceptionCodes.USER_INVALID_CREDENTIALS_FORMAT;
        message = "El formato de las credenciales no es válido.";
    }
}
