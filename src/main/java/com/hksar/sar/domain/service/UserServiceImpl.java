package com.hksar.sar.domain.service;

import com.hksar.sar.domain.model.*;
import com.hksar.sar.dto.CreateUserSectorRequestDto;
import com.hksar.sar.dto.UserSectorResponseDto;
import com.hksar.sar.exceptions.BusinessValidationException;
import com.hksar.sar.repository.*;
import com.hksar.sar.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.hksar.sar.exceptions.ErrorCodes.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserSectorRepository userSectorRepository;
    private final UserRepository userRepository;
    private final MinorSectorRepository minorSectorRepository;
    private final SectorRepository sectorRepository;
    private final SectorCategoryRepository sectorCategoryRepository;
    private final SubSectorRepository subSectorRepository;

    @Override
    public UserSectorResponseDto createUserSector(CreateUserSectorRequestDto userSectorRequestDto) {
        Optional<User> userOptional = userRepository.findUserByNameIgnoreCase(userSectorRequestDto.getName());
        User user = getUpdatedUser(userOptional.orElseGet(User::new), userSectorRequestDto);
        return getUserSectorResponseDto(userSectorRequestDto, user);
    }

    public User getUpdatedUser(User user, CreateUserSectorRequestDto userSectorRequestDto) {
        user.setName(userSectorRequestDto.getName());
        user.setAgreeTerms(true);
        user = userRepository.save(user);
        if (userSectorRepository.existsByUserId(user.getId())) userSectorRepository.deleteAllByUserId(user.getId());
        return user;
    }

    @Override
    @Transactional
    public UserSectorResponseDto updateUserSector(Long userId, CreateUserSectorRequestDto userSectorRequestDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new BusinessValidationException(BUSINESS_VALIDATION_FAILURE.getMessage()));
        return getUserSectorResponseDto(userSectorRequestDto, getUpdatedUser(user, userSectorRequestDto));
    }

    private UserSectorResponseDto getUserSectorResponseDto(CreateUserSectorRequestDto userSectorRequestDto, User user) {
        List<UserSector> userSectors = userSectorRequestDto.getSectors()
                .stream()
                .map(sector -> UserSector.builder()
                        .sectorId(sector.getId())
                        .userId(user.getId())
                        .sectorType(sector.getDepthType())
                        .build()
                ).collect(Collectors.toList());
        return mapToSectorResponse(userSectorRepository.saveAll(userSectors), user);
    }

    private UserSectorResponseDto mapToSectorResponse(List<UserSector> userSectors, User user) {
        UserSectorResponseDto sectorsResponse = new UserSectorResponseDto();
        sectorsResponse.setUserId(user.getId());
        sectorsResponse.setName(user.getName());
        sectorsResponse.setAgreeTerms(user.getAgreeTerms());

        sectorsResponse.setSectors(userSectors.stream()
                .map(userSector -> {
                    UserSectorResponseDto.Sector sectorResp = new UserSectorResponseDto.Sector();
                    switch (userSector.getSectorType()) {
                        case SECTOR:
                            Sector sector = sectorRepository.findById(userSector.getSectorId()).orElseThrow(() -> new BusinessValidationException(BUSINESS_VALIDATION_FAILURE.getMessage()));
                            sectorResp = UserSectorResponseDto.Sector
                                    .builder()
                                    .sectorName(sector.getName())
                                    .depthType(userSector.getSectorType())
                                    .id(userSector.getSectorId())
                                    .build();
                            break;
                        case MINOR_SECTOR:
                            MinorSector minorSector = minorSectorRepository.findById(userSector.getSectorId()).orElseThrow(() -> new BusinessValidationException(BUSINESS_VALIDATION_FAILURE.getMessage()));
                            sectorResp = UserSectorResponseDto.Sector
                                    .builder()
                                    .sectorName(minorSector.getName())
                                    .depthType(userSector.getSectorType())
                                    .id(userSector.getSectorId())
                                    .build();
                            break;
                        case SUB_SECTOR:
                            SubSector subSector = subSectorRepository.findById(userSector.getSectorId()).orElseThrow(() -> new BusinessValidationException(BUSINESS_VALIDATION_FAILURE.getMessage()));
                            sectorResp = UserSectorResponseDto.Sector
                                    .builder()
                                    .sectorName(subSector.getName())
                                    .depthType(userSector.getSectorType())
                                    .id(userSector.getSectorId())
                                    .build();
                            break;
                        case SECTOR_CATEGORY:
                            SectorCategory sectorCategory = sectorCategoryRepository.findById(userSector.getSectorId()).orElseThrow(() -> new BusinessValidationException(BUSINESS_VALIDATION_FAILURE.getMessage()));
                            sectorResp = UserSectorResponseDto.Sector
                                    .builder()
                                    .sectorName(sectorCategory.getName())
                                    .depthType(userSector.getSectorType())
                                    .id(userSector.getSectorId())
                                    .build();
                            break;

                    }
                    return sectorResp;
                }).collect(Collectors.toList())
        );
        return sectorsResponse;
    }
}
