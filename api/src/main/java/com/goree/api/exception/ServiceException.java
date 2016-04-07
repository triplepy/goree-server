package com.goree.api.exception;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -1113680954617055214L;

    public ServiceException(String message) {
        super(message);
    }
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
