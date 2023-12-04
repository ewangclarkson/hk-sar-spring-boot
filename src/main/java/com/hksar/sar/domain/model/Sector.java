package com.hksar.sar.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Sector extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sector_name")
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "minor_sector_id")
    private MinorSector minorSector;

}
