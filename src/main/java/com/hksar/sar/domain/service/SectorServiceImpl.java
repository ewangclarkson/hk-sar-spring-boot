package com.hksar.sar.domain.service;

import com.hksar.sar.domain.model.SectorCategory;
import com.hksar.sar.repository.SectorCategoryRepository;
import com.hksar.sar.service.SectorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class SectorServiceImpl implements SectorService {

    private final SectorCategoryRepository sectorCategoryRepository;

    @Override
    public List<SectorCategory> getAllSectors() {
        return sectorCategoryRepository.findAll();
    }
}
