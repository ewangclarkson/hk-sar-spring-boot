package com.hksar.sar.controller;

import com.hksar.sar.config.auth.TokenProvider;
import com.hksar.sar.constant.UserRole;
import com.hksar.sar.domain.model.User;
import com.hksar.sar.dto.*;
import com.hksar.sar.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
class UserControllerTests {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private TokenProvider tokenProvider;

    @Captor
    private ArgumentCaptor<UsernamePasswordAuthenticationToken> passwordTokenCaptor;

    @Test
    void getAuthUserSectors_returns_OK_with_sectors_from_service() {
        UserSectorResponseDto sector = UserSectorResponseDto.builder().userId(3L).build();
        when(userService.getAllAuthUserSectors()).thenReturn(sector);

        ResponseEntity<UserSectorResponseDto> response = userController.getAuthUserSectors();

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).isEqualTo(sector);
    }

    @Test
    void getUserSectors_returns_OK_with_sectors_from_service() {
        UserSectorResponseDto sector = UserSectorResponseDto.builder().userId(3L).build();
        when(userService.getAllUserSectorsById(4L)).thenReturn(sector);

        ResponseEntity<UserSectorResponseDto> response = userController.getUserSectors(4L);

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).isEqualTo(sector);
    }

    @Test
    void createUserSectors_returns_OK_with_created_sector() {
        UserSectorRequestDto requestDto = new UserSectorRequestDto();
        requestDto.setName("HR");
        requestDto.setUserId(4L);
        UserSectorResponseDto createdSector = UserSectorResponseDto.builder().userId(3L).build();
        when(userService.createUserSector(requestDto)).thenReturn(createdSector);

        ResponseEntity<UserSectorResponseDto> response = userController.createUserSectors(requestDto);

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).isEqualTo(createdSector);
    }

    @Test
    void updateUserSectors_returns_OK_with_created_sector() {
        UpdateUserSectorRequestDto requestDto = new UserSectorRequestDto();
        requestDto.setName("HR");
        UserSectorResponseDto updatedSector = UserSectorResponseDto.builder().userId(3L).build();
        when(userService.updateUserSector(2L, requestDto)).thenReturn(updatedSector);

        ResponseEntity<UserSectorResponseDto> response = userController.updateUserSectors(2L, requestDto);

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).isEqualTo(updatedSector);
    }

    @Test
    void signUp_calls_service() {
        SignUpDto request = new SignUpDto();
        userController.signUp(request);
        verify(userService).signUp(request);
    }

    @Test
    void signUp_returns_status_CREATED() {
        SignUpDto request = new SignUpDto();
        ResponseEntity<?> response = userController.signUp(request);

        assertThat(response.getStatusCode()).isEqualTo(CREATED);
    }

    @Test
    void signIn_returns_OK_with_JwtDto() {
        SignInDto request = new SignInDto();
        User user = new User();
        user.setId(4L);
        user.setName("Hr User");
        user.setEmail("mail@user.com");
        user.setRole(UserRole.USER);
        user.setAgreeTerms(false);
        Authentication auth = mock(Authentication.class);
        when(auth.getPrincipal()).thenReturn(user);

        TokenDto tokenDTO = TokenDto.builder()
                .token("token").expiresIn("50").build();

        when(authenticationManager.authenticate(passwordTokenCaptor.capture())).thenReturn(auth);
        when(tokenProvider.generateAccessToken(user)).thenReturn(tokenDTO);

        ResponseEntity<JwtDto> response = userController.signIn(request);
        assertThat(response.getStatusCode()).isEqualTo(OK);

        JwtDto token = response.getBody();

        assertThat(token.getUserDetails().getId()).isEqualTo(4);
        assertThat(token.getUserDetails().getName()).isEqualTo("Hr User");
        assertThat(token.getUserDetails().getEmail()).isEqualTo("mail@user.com");
        assertThat(token.getUserDetails().getRole()).isEqualTo(UserRole.USER);
        assertFalse(token.getUserDetails().getAgreeTerms());
        assertThat(token).hasNoNullFieldsOrPropertiesExcept();
    }
}
