package com.hksar.sar.domain.service;

import com.hksar.sar.constant.UserRole;
import com.hksar.sar.domain.model.*;
import com.hksar.sar.dto.SignUpDto;
import com.hksar.sar.dto.UpdateUserSectorRequestDto;
import com.hksar.sar.dto.UserSectorRequestDto;
import com.hksar.sar.dto.UserSectorResponseDto;
import com.hksar.sar.exceptions.AuthorizationException;
import com.hksar.sar.exceptions.BusinessValidationException;
import com.hksar.sar.exceptions.ResourceAlreadyExistException;
import com.hksar.sar.repository.*;
import com.hksar.sar.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.hksar.sar.exceptions.ErrorCodes.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserSectorRepository userSectorRepository;
    private final UserRepository userRepository;
    private final MinorSectorRepository minorSectorRepository;
    private final SectorRepository sectorRepository;
    private final SectorCategoryRepository sectorCategoryRepository;
    private final SubSectorRepository subSectorRepository;

    @Override
    public UserSectorResponseDto getAllUserSectorsById(Long userId) {
        List<UserSector> userSectors = userSectorRepository.findAllByUserId(userId);
        return mapToSectorResponse(userSectors, userId);
    }

    @Override
    public UserSectorResponseDto getAllAuthUserSectors() {
        User  user = getCurrentAuthUser();
        List<UserSector> userSectors = userSectorRepository.findAllByUserId(user.getId());
        return mapToSectorResponse(userSectors, user.getId());
    }

    @Override
    public UserSectorResponseDto createUserSector(UserSectorRequestDto userSectorRequestDto) {
        verifyCurrentAuthUser(userSectorRequestDto.getUserId());
        User user = userRepository.findById(userSectorRequestDto.getUserId()).orElseThrow(() -> new BusinessValidationException(BUSINESS_VALIDATION_FAILURE.getMessage()));
        if (userSectorRepository.existsByUserId(user.getId())) {
            throw new BusinessValidationException(BUSINESS_VALIDATION_FAILURE.getMessage());
        }
        return getUserSectorResponseDto(userSectorRequestDto, user);

    }

    @Override
    @Transactional
    public UserSectorResponseDto updateUserSector(Long userId, UpdateUserSectorRequestDto userSectorRequestDto) {
        verifyCurrentAuthUser(userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new BusinessValidationException(BUSINESS_VALIDATION_FAILURE.getMessage()));
        userSectorRepository.deleteAllByUserId(userId);
        return getUserSectorResponseDto(userSectorRequestDto, user);
    }

    private UserSectorResponseDto getUserSectorResponseDto(UpdateUserSectorRequestDto userSectorRequestDto, User user) {
        User finalUser = user;
        List<UserSector> userSectors = userSectorRequestDto.getSectors()
                .stream()
                .map(sector -> UserSector.builder()
                        .sectorId(sector.getId())
                        .userId(finalUser.getId())
                        .sectorType(sector.getDepthType())
                        .build()
                ).collect(Collectors.toList());
        user.setName(userSectorRequestDto.getName());
        user.setAgreeTerms(true);
        user = userRepository.save(user);
        return mapToSectorResponse(userSectorRepository.saveAll(userSectors), user.getId());
    }

    private UserSectorResponseDto mapToSectorResponse(List<UserSector> userSectors, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new BusinessValidationException(BUSINESS_VALIDATION_FAILURE.getMessage()));
        UserSectorResponseDto sectorsResponse = new UserSectorResponseDto();
        sectorsResponse.setUserId(userId);
        sectorsResponse.setName(user.getName());
        sectorsResponse.setAgreeTerms(user.getAgreeTerms());

        // TODO remove nth +1 loop problem
        sectorsResponse.setSectors(userSectors.stream()
                .map(userSector -> {
                    UserSectorResponseDto.Sector sectorResp = new UserSectorResponseDto.Sector();
                    switch (userSector.getSectorType()) {
                        case SECTOR:
                            Sector sector = sectorRepository.findById(userSector.getSectorId()).orElseThrow(() -> new BusinessValidationException(BUSINESS_VALIDATION_FAILURE.getMessage()));
                            sectorResp = UserSectorResponseDto.Sector
                                    .builder()
                                    .SectorName(sector.getName())
                                    .depthType(userSector.getSectorType())
                                    .id(userSector.getId())
                                    .build();
                            break;
                        case MINOR_SECTOR:
                            MinorSector minorSector = minorSectorRepository.findById(userSector.getSectorId()).orElseThrow(() -> new BusinessValidationException(BUSINESS_VALIDATION_FAILURE.getMessage()));
                            sectorResp = UserSectorResponseDto.Sector
                                    .builder()
                                    .SectorName(minorSector.getName())
                                    .depthType(userSector.getSectorType())
                                    .id(userSector.getId())
                                    .build();
                            break;
                        case SUB_SECTOR:
                            SubSector subSector = subSectorRepository.findById(userSector.getSectorId()).orElseThrow(() -> new BusinessValidationException(BUSINESS_VALIDATION_FAILURE.getMessage()));
                            sectorResp = UserSectorResponseDto.Sector
                                    .builder()
                                    .SectorName(subSector.getName())
                                    .depthType(userSector.getSectorType())
                                    .id(userSector.getId())
                                    .build();
                            break;
                        case SECTOR_CATEGORY:
                            SectorCategory sectorCategory = sectorCategoryRepository.findById(userSector.getSectorId()).orElseThrow(() -> new BusinessValidationException(BUSINESS_VALIDATION_FAILURE.getMessage()));
                            sectorResp = UserSectorResponseDto.Sector
                                    .builder()
                                    .SectorName(sectorCategory.getName())
                                    .depthType(userSector.getSectorType())
                                    .id(userSector.getId())
                                    .build();
                            break;

                    }
                    return sectorResp;
                }).collect(Collectors.toList())
        );
        return sectorsResponse;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByEmail(username);
    }

    public UserDetails signUp(SignUpDto registerUserDto) throws BusinessValidationException {
        if (userRepository.findByEmail(registerUserDto.getEmail()) != null) {
            throw new ResourceAlreadyExistException(RESOURCE_ALREADY_EXIST.getMessage());
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerUserDto.getPassword());
        User newUser = new User(registerUserDto.getEmail(), encryptedPassword, UserRole.USER);
        return userRepository.save(newUser);
    }

    @Override
    public User getCurrentAuthUser() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = (User) principal;
            return user;
        }catch (Exception e){
            throw new AuthorizationException(ACCESS_DENIED.getMessage());
        }
    }

    private void verifyCurrentAuthUser(Long userId){
        User authUser = getCurrentAuthUser();
        if(!authUser.getId().equals(userId)){
            throw new AuthorizationException(MUST_BE_AUTH_USER_ID.getMessage());
        }

    }
}
