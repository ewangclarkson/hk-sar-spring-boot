package com.hksar.sar.exceptions;


public enum ErrorCodes {
    RESOURCE_NOT_FOUND("Resource Not Found"),
    ACCESS_DENIED("Access is denied"),
    ACCESS_FORBIDDEN("Access is forbidden"),
    VALIDATION_ERROR("Validation Error"),
    INVALID_FORMAT("Invalid Format"),
    RESOURCE_ALREADY_EXIST("Resource already exist."),
    BUSINESS_VALIDATION_FAILURE("A business validation failed"),
    MUST_BE_AUTH_USER_ID("Action must be performed only on the current auth user");
    private String message;

    ErrorCodes(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
