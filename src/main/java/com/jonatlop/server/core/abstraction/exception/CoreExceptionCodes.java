package com.jonatlop.server.core.abstraction.exception;

public final class CoreExceptionCodes {
    private CoreExceptionCodes() {}
    
    public static final int REQUIRED_FIELDS_NOT_SET = 0;
    public static final int USER_ALREADY_EXISTS = 100;
    public static final int USER_INVALID_CREDENTIALS_FORMAT = 101;
}
