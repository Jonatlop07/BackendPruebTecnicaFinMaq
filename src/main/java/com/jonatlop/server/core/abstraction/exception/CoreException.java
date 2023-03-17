package com.jonatlop.server.core.abstraction.exception;

import lombok.Getter;

@Getter
public abstract class CoreException extends RuntimeException {
    protected int code;
    protected String message;
}
