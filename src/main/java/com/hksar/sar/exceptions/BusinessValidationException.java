package com.hksar.sar.exceptions;

import lombok.Data;

@Data
public class BusinessValidationException extends RuntimeException {
    public BusinessValidationException(String message) {
        super(message);
    }
}
