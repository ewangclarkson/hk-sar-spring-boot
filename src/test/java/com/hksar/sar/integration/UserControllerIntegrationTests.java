package com.hksar.sar.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hksar.sar.config.auth.TokenProvider;
import com.hksar.sar.constant.SectorLevels;
import com.hksar.sar.constant.UserRole;
import com.hksar.sar.domain.model.Sector;
import com.hksar.sar.domain.model.User;
import com.hksar.sar.domain.model.UserSector;
import com.hksar.sar.dto.SignInDto;
import com.hksar.sar.dto.SignUpDto;
import com.hksar.sar.dto.UpdateUserSectorRequestDto;
import com.hksar.sar.dto.UserSectorRequestDto;
import com.hksar.sar.repository.SectorRepository;
import com.hksar.sar.repository.UserRepository;
import com.hksar.sar.repository.UserSectorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.stream.Stream;

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
    @Autowired
    TokenProvider tokenProvider;
    @Autowired
    PasswordEncoder passwordEncoder;

    private User user1;
    private final String passKey = "passKey";

    @BeforeEach
    void setUp() {
        user1 = new User("user@email.1", passwordEncoder.encode(passKey), UserRole.USER);
        user1 = userRepository.save(user1);
    }

    @AfterEach
    void tearDown() {
        userSectorRepository.deleteAll();
        userRepository.deleteAll();
        sectorRepository.deleteAll();
    }

    @Test
    void can_get_auth_user_sectors() throws Exception{
        Sector sector1 = new Sector();
        sector1.setName("Sector 1");
        sector1 = sectorRepository.save(sector1);

        UserSector userSector = new UserSector();
        userSector.setSectorId(sector1.getId());
        userSector.setUserId(user1.getId());
        userSector.setSectorType(SectorLevels.SECTOR);
        userSector = userSectorRepository.save(userSector);

        String token = tokenProvider.generateAccessToken(user1).getToken();

        RequestBuilder getAuthUserSectors = MockMvcRequestBuilders
                .get("/api/protected/users/sectors/me")
                .header("Authorization", "Bearer "+token)
                .accept(MediaType.APPLICATION_JSON);

        userController.perform(getAuthUserSectors).andExpect(status().isOk());
    }

    @Test
    void can_get_sectors_by_user_id() throws Exception{
        Sector sector1 = new Sector();
        sector1.setName("Sector 1");
        sector1 = sectorRepository.save(sector1);

        UserSector userSector = new UserSector();
        userSector.setSectorId(sector1.getId());
        userSector.setUserId(user1.getId());
        userSector.setSectorType(SectorLevels.SECTOR);
        userSector = userSectorRepository.save(userSector);

        String token = tokenProvider.generateAccessToken(user1).getToken();

        RequestBuilder getSectors = MockMvcRequestBuilders
                .get("/api/protected/users/{userId}/sectors", user1.getId())
                .header("Authorization", "Bearer "+token)
                .accept(MediaType.APPLICATION_JSON);

        userController.perform(getSectors).andExpect(status().isOk());
    }

    @Test
    void can_create_user_sector() throws Exception{
        Sector s = new Sector();
        s.setName("Robust");
        s= sectorRepository.save(s);

        UpdateUserSectorRequestDto.Sector sector =  new UpdateUserSectorRequestDto.Sector();
        sector.setId(s.getId());
        sector.setDepthType(SectorLevels.SECTOR);
        UserSectorRequestDto requestDto = new UserSectorRequestDto();
        requestDto.setName("Pious");
        requestDto.setUserId(user1.getId());
        requestDto.setAgreeTerms(true);
        requestDto.setSectors(Collections.singleton(sector));
        String token = tokenProvider.generateAccessToken(user1).getToken();

        RequestBuilder createSector = MockMvcRequestBuilders
                .post("/api/protected/users/sectors")
                .header("Authorization", "Bearer "+token)
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

        UpdateUserSectorRequestDto.Sector sector =  new UpdateUserSectorRequestDto.Sector();
        sector.setId(s.getId());
        sector.setDepthType(SectorLevels.SECTOR);
        UserSectorRequestDto requestDto = new UserSectorRequestDto();
        requestDto.setName("Pious");
        requestDto.setUserId(user1.getId());
        requestDto.setAgreeTerms(true);
        requestDto.setSectors(Collections.singleton(sector));
        String token = tokenProvider.generateAccessToken(user1).getToken();

        RequestBuilder updateSector = MockMvcRequestBuilders
                .put("/api/protected/users/{userId}/sectors", user1.getId())
                .header("Authorization", "Bearer "+token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto))
                .accept(MediaType.APPLICATION_JSON);

        userController.perform(updateSector).andExpect(status().isOk());
    }


    @Test
    void can_signUp_user() throws Exception{
        SignUpDto request = new SignUpDto();
        request.setPassword("password");
        request.setEmail("usr@mail.1");

        RequestBuilder signUp = MockMvcRequestBuilders
                .post("/api/public/register")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request));

        userController.perform(signUp).andExpect(status().isCreated());
    }

    private static Stream<Arguments> inValidCredentials() {
        return Stream.of(
                Arguments.of("", ""),
                Arguments.of("one set", ""),
                Arguments.of("", "one wrongly set"),
                Arguments.of("", "user@users.com"),
                Arguments.of(null, null),
                Arguments.of("", null),
                Arguments.of(null, "null@valid.com")
        );
    }

    @ParameterizedTest
    @MethodSource("inValidCredentials")
    void signUp_throws_400_for_invalid_credentials(String password, String email) throws Exception{
        SignUpDto request = new SignUpDto();
        request.setPassword(password);
        request.setEmail(email);

        RequestBuilder signUp = MockMvcRequestBuilders
                .post("/api/public/register")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request));

        userController.perform(signUp).andExpect(status().isBadRequest());
    }

    @Test
    void can_signIn_user() throws Exception{
        SignInDto request = new SignInDto();
        request.setPassword(passKey);
        request.setEmail(user1.getEmail());

        RequestBuilder signIn = MockMvcRequestBuilders
                .post("/api/public/login")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request));

        userController.perform(signIn).andExpect(status().isOk());
    }

    @ParameterizedTest
    @MethodSource("inValidCredentials")
    void signIn_throws_400_for_invalid_fields_user(String password, String email) throws Exception{
        SignInDto request = new SignInDto();
        request.setPassword(password);
        request.setEmail(email);

        RequestBuilder signIn = MockMvcRequestBuilders
                .post("/api/public/login")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request));

        userController.perform(signIn).andExpect(status().isBadRequest());
    }
}
