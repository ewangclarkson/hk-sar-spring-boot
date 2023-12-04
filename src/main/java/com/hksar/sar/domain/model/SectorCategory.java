package com.hksar.sar.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
public class SectorCategory extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "sectorCategory")
    private List<SubSector> subSectors;
}
