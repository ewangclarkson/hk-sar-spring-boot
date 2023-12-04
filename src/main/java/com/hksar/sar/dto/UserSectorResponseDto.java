package com.hksar.sar.dto;

import com.hksar.sar.constant.SectorLevels;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSectorResponseDto {
    private Long userId;
    private String name;
    private List<Sector> sectors;
    private Boolean agreeTerms;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Sector {
        private Long id;
        private String SectorName;
        private SectorLevels depthType;
    }
}
