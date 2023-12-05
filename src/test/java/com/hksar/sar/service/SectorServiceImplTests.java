package com.hksar.sar.service;

import com.hksar.sar.domain.model.SectorCategory;
import com.hksar.sar.domain.service.SectorServiceImpl;
import com.hksar.sar.repository.SectorCategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SectorServiceImplTests {
    @InjectMocks
    private SectorServiceImpl sectorService;

    @Mock
    private SectorCategoryRepository sectorCategoryRepository;

    @Test
    void getAllSectors_returns_List_of_SectorCategory_from_repository() {
        SectorCategory category1 = new SectorCategory();
        SectorCategory category2 = new SectorCategory();
        when(sectorCategoryRepository.findAll()).thenReturn(Arrays.asList(category1, category2));

        List<SectorCategory> categories = sectorService.getAllSectors();

        assertThat(categories).hasSize(2);
    }
}
