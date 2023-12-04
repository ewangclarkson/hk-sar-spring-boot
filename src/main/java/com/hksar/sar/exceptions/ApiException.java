package com.hksar.sar.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiException extends RuntimeException {
    private HttpStatus httpStatus;
    private String errorCode;
    public ApiException(String message, String errorCode, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "ApiException{" +
                "message=" + getMessage() +
                "httpStatus=" + httpStatus +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }
}
