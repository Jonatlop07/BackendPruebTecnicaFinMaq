package com.jonatlop.server.core.abstraction.exception;

public abstract class CoreException extends RuntimeException {
    protected int code;
    protected String message;
}
