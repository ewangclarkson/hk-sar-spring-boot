package com.hksar.sar.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SignUpDto {
    @NotEmpty(message = "The email field is required")
    private String email;
    @NotEmpty(message = "The password field is required")
    private String password;
}
