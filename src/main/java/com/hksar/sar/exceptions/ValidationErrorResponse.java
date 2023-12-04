package com.hksar.sar.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationErrorResponse extends ErrorResponse {

    private List<ErrorItem> errors = new ArrayList<>();

    void addError(ErrorItem errorItem){
        this.errors.add(errorItem);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ErrorItem {
        private String field;
        private String message;
    }
}
