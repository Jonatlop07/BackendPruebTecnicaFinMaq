package com.jonatlop.server.core.abstraction.exception;

public class QueryFieldsNotSetException extends CoreException {
    public QueryFieldsNotSetException() {
        code = CoreExceptionCodes.QUERY_FIELDS_NOT_SET;
        message = "Ningún parámetro de consulta fue proporcionado.";
    }
}
