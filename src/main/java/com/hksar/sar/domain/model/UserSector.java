package com.hksar.sar.domain.model;

import com.hksar.sar.constant.SectorLevels;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSector extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sector_id")
    private Long sectorId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SectorLevels sectorType;
    @Column(name = "user_id", nullable = false)
    private Long userId;
}
