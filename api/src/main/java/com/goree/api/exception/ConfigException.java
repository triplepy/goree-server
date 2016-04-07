package com.goree.api.exception;

public class ConfigException extends RuntimeException {
    private static final long serialVersionUID = -6601640176155550743L;

    public ConfigException(String message) {
        super(message);
    }
    public ConfigException(Throwable cause) {
        super(cause);
    }
    public ConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
