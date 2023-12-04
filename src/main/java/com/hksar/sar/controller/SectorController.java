package com.hksar.sar.controller;


import com.hksar.sar.domain.model.SectorCategory;
import com.hksar.sar.service.SectorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public")
@Slf4j
@RequiredArgsConstructor
public class SectorController {

    private final SectorService sectorService;

    @GetMapping("/sectors")
    public ResponseEntity<List<SectorCategory>> getAllSectors() {
        return ResponseEntity.ok(sectorService.getAllSectors());
    }
}
