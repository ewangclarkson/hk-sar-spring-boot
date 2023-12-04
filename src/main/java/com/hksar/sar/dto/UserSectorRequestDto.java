package com.hksar.sar.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class UserSectorRequestDto extends UpdateUserSectorRequestDto {
    @NotNull(message = "The user id is required")
    private Long userId;
}
