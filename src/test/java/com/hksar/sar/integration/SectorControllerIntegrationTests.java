package com.hksar.sar.integration;

import com.hksar.sar.domain.model.SectorCategory;
import com.hksar.sar.repository.SectorCategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SectorControllerIntegrationTests {
    @Autowired
     MockMvc sectorController;

    @Autowired
    SectorCategoryRepository sectorCategoryRepository;

    @BeforeEach
    void setUp() {
        SectorCategory cat1 = new SectorCategory();
        cat1.setName("Cateogry 1");

        sectorCategoryRepository.save(cat1);
    }

    @AfterEach
    void tearDown() {
        sectorCategoryRepository.deleteAll();
    }

    @Test
    void can_get_all_sectors() throws Exception {
        RequestBuilder getSectors = MockMvcRequestBuilders
                .get("/api/public/sectors")
                .accept(MediaType.APPLICATION_JSON);

        sectorController.perform(getSectors).andExpect(status().isOk());
    }
}
