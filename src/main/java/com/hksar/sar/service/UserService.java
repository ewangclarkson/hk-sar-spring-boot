package com.hksar.sar.service;


import com.hksar.sar.dto.CreateUserSectorRequestDto;
import com.hksar.sar.dto.UserSectorResponseDto;



public interface UserService {
    UserSectorResponseDto createUserSector(CreateUserSectorRequestDto userSectorRequestDto);

    UserSectorResponseDto updateUserSector(Long userId, CreateUserSectorRequestDto userSectorRequestDto);

}
