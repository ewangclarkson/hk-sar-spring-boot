package com.hksar.sar.controller;

import com.hksar.sar.dto.*;
import com.hksar.sar.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;;


@RestController
@RequestMapping("/api/public")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/sectors")
    public ResponseEntity<UserSectorResponseDto> createUserSectors(@Valid @RequestBody CreateUserSectorRequestDto userSectorRequestDto) {
        return ResponseEntity.ok(userService.createUserSector(userSectorRequestDto));
    }

    @CrossOrigin
    @PutMapping("/users/{userId}/sectors")
    public ResponseEntity<UserSectorResponseDto> updateUserSectors(@PathVariable Long userId, @Valid @RequestBody CreateUserSectorRequestDto userSectorRequestDto) {
        return ResponseEntity.ok(userService.updateUserSector(userId, userSectorRequestDto));
    }

}
