package com.hksar.sar.domain.service;

import com.hksar.sar.domain.model.*;
import com.hksar.sar.dto.CreateUserSectorRequestDto;
import com.hksar.sar.dto.UserSectorResponseDto;
import com.hksar.sar.exceptions.AuthorizationException;
import com.hksar.sar.exceptions.BusinessValidationException;
import com.hksar.sar.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Collections;
import java.util.Optional;
import java.util.stream.Stream;

import static com.hksar.sar.constant.SectorLevels.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTests {
    @InjectMocks
    UserServiceImpl userService;
    @Mock
    private UserSectorRepository userSectorRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private MinorSectorRepository minorSectorRepository;
    @Mock
    private SectorRepository sectorRepository;
    @Mock
    private SectorCategoryRepository sectorCategoryRepository;
    @Mock
    private SubSectorRepository subSectorRepository;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Test
    void createUserSector_returns_UserSectorResponseDto() {
        User currentUser = new User();
        currentUser.setId(6L);

        CreateUserSectorRequestDto.Sector sectorRequest1 = new CreateUserSectorRequestDto.Sector();
        sectorRequest1.setDepthType(MINOR_SECTOR);
        sectorRequest1.setId(5L);

        CreateUserSectorRequestDto request = new CreateUserSectorRequestDto();
        request.setName("No names");
        request.setSectors(Collections.singleton(sectorRequest1));
        request.setAgreeTerms(true);

        when(userRepository.save(userCaptor.capture())).thenReturn(currentUser);
        when(userRepository.findUserByNameIgnoreCase("No names")).thenReturn(Optional.of(currentUser));

        UserSectorResponseDto response = userService.createUserSector(request);
        User capturedUser = userCaptor.getValue();

        assertThat(response).hasNoNullFieldsOrPropertiesExcept();
        assertThat(capturedUser.getName()).isEqualTo("No names");
        assertTrue(capturedUser.getAgreeTerms());

        assertThat(response).hasNoNullFieldsOrPropertiesExcept();
    }

    @Test
    void updateUserSector_throws_BusinessValidationException_when_authorized_user_not_existing() {
        User currentUser = new User();
        currentUser.setId(6L);
        CreateUserSectorRequestDto request = new CreateUserSectorRequestDto();

        assertThrows(BusinessValidationException.class, () -> userService.updateUserSector(6L, request));
    }

    @Test
    void updateUserSector_returns_UserSectorResponseDto() {
        User currentUser = new User();
        currentUser.setId(6L);

        CreateUserSectorRequestDto.Sector sectorRequest1 = new CreateUserSectorRequestDto.Sector();
        sectorRequest1.setDepthType(MINOR_SECTOR);
        sectorRequest1.setId(5L);

        CreateUserSectorRequestDto request = new CreateUserSectorRequestDto();
        request.setName("No names");
        request.setSectors(Collections.singleton(sectorRequest1));

        when(userRepository.save(userCaptor.capture())).thenReturn(currentUser);
        when(userRepository.findById(6L)).thenReturn(Optional.of(currentUser));

        UserSectorResponseDto response = userService.updateUserSector(6L, request);

        User capturedUser = userCaptor.getValue();

        assertThat(response).hasNoNullFieldsOrPropertiesExcept();
        assertThat(capturedUser.getName()).isEqualTo("No names");
        assertTrue(capturedUser.getAgreeTerms());

        assertThat(response).hasNoNullFieldsOrPropertiesExcept();
    }
}
