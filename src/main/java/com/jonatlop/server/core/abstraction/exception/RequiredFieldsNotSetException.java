package com.jonatlop.server.core.abstraction.exception;

public final class RequiredFieldsNotSetException extends CoreException {
    public RequiredFieldsNotSetException() {
        code = CoreExceptionCodes.REQUIRED_FIELDS_NOT_SET;
        message = "Los campos requeridos no fueron proporcionados en su totalidad.";
    }
}
