package com.hksar.sar.dto;

import com.hksar.sar.constant.SectorLevels;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class UpdateUserSectorRequestDto {
    @NotEmpty(message = "The username is required")
    private String name;
    @NotEmpty(message = "Sector cannot be empty")
    private Set<@Valid Sector> sectors;

    @AssertTrue(message = "Must agree to terms of conditions")
    private boolean agreeTerms;

    @Data
    public static class Sector {
        @NotNull(message = "The sector id is required")
        private Long id;
        @NotNull(message = "The sector type is required")
        private SectorLevels depthType;
    }
}
