package com.hksar.sar.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class SubSector extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "subSector")
    private List<MinorSector> minorSectors;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sector_category_id")
    private SectorCategory sectorCategory;
}
