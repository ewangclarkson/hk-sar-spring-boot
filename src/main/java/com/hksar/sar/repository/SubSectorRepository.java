package com.hksar.sar.repository;

import com.hksar.sar.domain.model.SubSector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubSectorRepository extends JpaRepository<SubSector,Long> {
}
