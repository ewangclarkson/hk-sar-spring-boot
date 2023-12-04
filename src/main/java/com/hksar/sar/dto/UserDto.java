package com.hksar.sar.dto;

import com.hksar.sar.constant.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private Boolean agreeTerms;
    private UserRole role;
}
