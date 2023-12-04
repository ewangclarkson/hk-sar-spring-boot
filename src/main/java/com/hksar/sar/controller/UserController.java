package com.hksar.sar.controller;

import com.hksar.sar.config.auth.TokenProvider;
import com.hksar.sar.domain.model.User;
import com.hksar.sar.dto.*;
import com.hksar.sar.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenService;

    @GetMapping("/protected/users/sectors/me")
    public ResponseEntity<UserSectorResponseDto> getAuthUserSectors() {
        return ResponseEntity.ok(userService.getAllAuthUserSectors());
    }

    @GetMapping("/protected/users/{userId}/sectors")
    public ResponseEntity<UserSectorResponseDto> getUserSectors(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getAllUserSectorsById(userId));
    }

    @PostMapping("/protected/users/sectors")
    public ResponseEntity<UserSectorResponseDto> createUserSectors(@Valid @RequestBody UserSectorRequestDto userSectorRequestDto) {
        return ResponseEntity.ok(userService.createUserSector(userSectorRequestDto));
    }


    @PutMapping("/protected/users/{userId}/sectors")
    public ResponseEntity<UserSectorResponseDto> updateUserSectors(@PathVariable Long userId, @Valid @RequestBody UpdateUserSectorRequestDto userSectorRequestDto) {
        return ResponseEntity.ok(userService.updateUserSector(userId, userSectorRequestDto));
    }


    @PostMapping("/public/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpDto signUpDto) {
        userService.signUp(signUpDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/public/signin")
    public ResponseEntity<JwtDto> signIn(@RequestBody @Valid SignInDto signInDto) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(signInDto.getEmail(), signInDto.getPassword());
        Authentication authUser = authenticationManager.authenticate(usernamePassword);
        TokenDto token = tokenService.generateAccessToken((User) authUser.getPrincipal());
        User user = (User) authUser.getPrincipal();
        UserDto userDto = UserDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole())
                .agreeTerms(user.getAgreeTerms())
                .id(user.getId())
                .build();

        return ResponseEntity.ok(new JwtDto(token.getToken(), userDto, token.getExpiresIn()));
    }
}
