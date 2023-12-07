package com.hksar.sar.repository;

import com.hksar.sar.domain.model.UserSector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSectorRepository extends JpaRepository<UserSector,Long> {
    void deleteAllByUserId(Long userId);
    Boolean existsByUserId(Long userId);
}
