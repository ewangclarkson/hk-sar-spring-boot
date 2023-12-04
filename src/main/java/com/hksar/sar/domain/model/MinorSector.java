package com.hksar.sar.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class MinorSector extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "minorSector")
    private List<Sector> sectors;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sub_sector_id")
    private SubSector subSector;
}
