package com.hksar.sar.controller;

import com.hksar.sar.dto.*;
import com.hksar.sar.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
class UserControllerTests {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;


    @Test
    void createUserSectors_returns_OK_with_created_sector() {
        CreateUserSectorRequestDto requestDto = new CreateUserSectorRequestDto();
        requestDto.setName("HR");
        UserSectorResponseDto createdSector = UserSectorResponseDto.builder().userId(3L).build();
        when(userService.createUserSector(requestDto)).thenReturn(createdSector);

        ResponseEntity<UserSectorResponseDto> response = userController.createUserSectors(requestDto);

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).isEqualTo(createdSector);
    }

    @Test
    void updateUserSectors_returns_OK_with_created_sector() {
        CreateUserSectorRequestDto requestDto = new CreateUserSectorRequestDto();
        requestDto.setName("HR");
        UserSectorResponseDto updatedSector = UserSectorResponseDto.builder().userId(3L).build();
        when(userService.updateUserSector(2L, requestDto)).thenReturn(updatedSector);

        ResponseEntity<UserSectorResponseDto> response = userController.updateUserSectors(2L, requestDto);

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).isEqualTo(updatedSector);
    }
}
