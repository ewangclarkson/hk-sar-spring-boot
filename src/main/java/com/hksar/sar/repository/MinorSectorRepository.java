package com.hksar.sar.repository;

import com.hksar.sar.domain.model.MinorSector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MinorSectorRepository extends JpaRepository<MinorSector,Long> {
}
