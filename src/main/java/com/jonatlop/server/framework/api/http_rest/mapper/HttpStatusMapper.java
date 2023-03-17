package com.jonatlop.server.framework.api.http_rest.mapper;

import com.jonatlop.server.core.abstraction.exception.CoreExceptionCodes;
import org.springframework.http.HttpStatus;

import java.util.Set;

public final class HttpStatusMapper {
    private static final Set<Integer> conflictCodes = Set.of(
        CoreExceptionCodes.USER_ALREADY_EXISTS
    );
    
    private static final Set<Integer> forbiddenCodes = Set.of(
        CoreExceptionCodes.USER_INVALID_CREDENTIALS_FORMAT
    );
    
    private static final Set<Integer> badRequestCodes = Set.of(
        CoreExceptionCodes.REQUIRED_FIELDS_NOT_SET,
        CoreExceptionCodes.QUERY_FIELDS_NOT_SET
    );
    
    
    public static HttpStatus from(int code) {
        if (conflictCodes.contains(code)) {
            return HttpStatus.CONFLICT;
        }
        if (badRequestCodes.contains(code)) {
            return HttpStatus.BAD_REQUEST;
        }
        if (forbiddenCodes.contains(code)) {
            return HttpStatus.FORBIDDEN;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
