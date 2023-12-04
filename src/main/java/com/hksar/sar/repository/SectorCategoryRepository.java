package com.hksar.sar.repository;

import com.hksar.sar.domain.model.SectorCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorCategoryRepository extends JpaRepository<SectorCategory,Long> {
}
