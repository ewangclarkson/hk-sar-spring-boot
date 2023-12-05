package com.hksar.sar.controller;

import com.hksar.sar.domain.model.SectorCategory;
import com.hksar.sar.service.SectorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SectorControllerTests {
    @InjectMocks
    private SectorController sectorController;

    @Mock
    private SectorService sectorService;

    @Test
    void getAllSectors_callsSectorService_Once() {
        sectorController.getAllSectors();
        verify(sectorService, times(1)).getAllSectors();
    }

    @Test
    void getAllSectors_returns_OK_response_with_List_of_Sector_Categories() {
        SectorCategory sector1 = new SectorCategory();
        SectorCategory sector2 = new SectorCategory();

        when(sectorService.getAllSectors()).thenReturn(Arrays.asList(sector1, sector2));
        ResponseEntity<List<SectorCategory>> response = sectorController.getAllSectors();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(2);
    }
}
