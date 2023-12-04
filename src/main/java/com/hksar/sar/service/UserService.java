package com.hksar.sar.service;

import com.hksar.sar.domain.model.User;
import com.hksar.sar.dto.SignUpDto;
import com.hksar.sar.dto.UpdateUserSectorRequestDto;
import com.hksar.sar.dto.UserSectorRequestDto;
import com.hksar.sar.dto.UserSectorResponseDto;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserService {
    UserSectorResponseDto getAllUserSectorsById(Long userId);

    UserSectorResponseDto getAllAuthUserSectors();

    UserSectorResponseDto createUserSector(UserSectorRequestDto userSectorRequestDto);

    UserSectorResponseDto updateUserSector(Long userId, UpdateUserSectorRequestDto userSectorRequestDto);

    UserDetails signUp(SignUpDto registerUserDto);
    User getCurrentAuthUser();
}
