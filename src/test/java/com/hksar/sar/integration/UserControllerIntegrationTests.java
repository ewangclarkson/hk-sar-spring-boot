package com.hksar.sar.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hksar.sar.constant.SectorLevels;
import com.hksar.sar.domain.model.Sector;
import com.hksar.sar.domain.model.User;
import com.hksar.sar.domain.model.UserSector;
import com.hksar.sar.dto.CreateUserSectorRequestDto;
import com.hksar.sar.repository.SectorRepository;
import com.hksar.sar.repository.UserRepository;
import com.hksar.sar.repository.UserSectorRepository;
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

import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTests {
    @Autowired
    MockMvc userController;

    @Autowired
    UserRepository userRepository;
    @Autowired
    SectorRepository sectorRepository;
    @Autowired
    UserSectorRepository userSectorRepository;

    private User user1;
    private final String passKey = "passKey";

    @BeforeEach
    void setUp() {
        user1 = new User();
        user1.setName("hk-sar");
        user1.setAgreeTerms(true);
        user1 = userRepository.save(user1);
    }

    @AfterEach
    void tearDown() {
        userSectorRepository.deleteAll();
        userRepository.deleteAll();
        sectorRepository.deleteAll();
    }


    @Test
    void can_create_user_sector() throws Exception{
        Sector s = new Sector();
        s.setName("Robust");
        s= sectorRepository.save(s);

        CreateUserSectorRequestDto.Sector sector =  new CreateUserSectorRequestDto.Sector();
        sector.setId(s.getId());
        sector.setDepthType(SectorLevels.SECTOR);
        CreateUserSectorRequestDto requestDto = new CreateUserSectorRequestDto();
        requestDto.setName("Pious");
        requestDto.setAgreeTerms(true);
        requestDto.setSectors(Collections.singleton(sector));
        RequestBuilder createSector = MockMvcRequestBuilders
                .post("/api/public/users/sectors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto))
                .accept(MediaType.APPLICATION_JSON);

        userController.perform(createSector).andExpect(status().isOk());
    }

    @Test
    void can_update_user_sector() throws Exception{
        Sector s = new Sector();
        s.setName("Robust");
        s= sectorRepository.save(s);

        UserSector userSector = new UserSector();
        userSector.setSectorType(SectorLevels.SECTOR);
        userSector.setSectorId(s.getId());
        userSector.setUserId(user1.getId());
        userSector = userSectorRepository.save(userSector);

        CreateUserSectorRequestDto.Sector sector =  new CreateUserSectorRequestDto.Sector();
        sector.setId(s.getId());
        sector.setDepthType(SectorLevels.SECTOR);
        CreateUserSectorRequestDto requestDto = new CreateUserSectorRequestDto();
        requestDto.setName("Pious");
        requestDto.setAgreeTerms(true);
        requestDto.setSectors(Collections.singleton(sector));

        RequestBuilder updateSector = MockMvcRequestBuilders
                .put("/api/public/users/{userId}/sectors", user1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto))
                .accept(MediaType.APPLICATION_JSON);

        userController.perform(updateSector).andExpect(status().isOk());
    }
}
