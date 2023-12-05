package com.hksar.sar.service;

import com.hksar.sar.constant.SectorLevels;
import com.hksar.sar.constant.UserRole;
import com.hksar.sar.domain.model.*;
import com.hksar.sar.domain.service.UserServiceImpl;
import com.hksar.sar.dto.SignUpDto;
import com.hksar.sar.dto.UpdateUserSectorRequestDto;
import com.hksar.sar.dto.UserSectorRequestDto;
import com.hksar.sar.dto.UserSectorResponseDto;
import com.hksar.sar.exceptions.AuthorizationException;
import com.hksar.sar.exceptions.BusinessValidationException;
import com.hksar.sar.exceptions.ResourceAlreadyExistException;
import com.hksar.sar.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
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
    void getAllUserSectorsById_throws_422_for_User_not_existing() {
        UserSector sector1 = new UserSector();
        UserSector sector2 = new UserSector();

        when(userSectorRepository.findAllByUserId(60L)).thenReturn(Arrays.asList(sector1, sector2));

        assertThrows(BusinessValidationException.class, () -> userService.getAllUserSectorsById(60L));
    }

    private static Stream<Arguments> sectorTypesSource() {
        return Stream.of(
                Arguments.of(SECTOR),
                Arguments.of(MINOR_SECTOR),
                Arguments.of(SUB_SECTOR),
                Arguments.of(SECTOR_CATEGORY)
        );
    }

    @ParameterizedTest
    @MethodSource("sectorTypesSource")
    void getAllUserSectorsById_throws_422_for_sector_not_existing(SectorLevels level) {
        UserSector sector1 = new UserSector();
        sector1.setId(60000L);
        sector1.setSectorType(level);

        User user = new User();

        when(userRepository.findById(60L)).thenReturn(Optional.of(user));
        when(userSectorRepository.findAllByUserId(60L)).thenReturn(Collections.singletonList(sector1));

        assertThrows(BusinessValidationException.class, () -> userService.getAllUserSectorsById(60L));
    }

    @ParameterizedTest
    @MethodSource("sectorTypesSource")
    void getAllUserSectorsById_returns_UserSectorResponseDto(SectorLevels level) {
        UserSector userSector1 = new UserSector();
        userSector1.setSectorType(level);
        userSector1.setId(1L);
        userSector1.setSectorId(5L);
        userSector1.setUserId(5L);

        UserSector userSector2 = new UserSector();
        userSector2.setSectorType(level);
        userSector2.setId(2L);
        userSector2.setSectorId(6L);

        User user = new User();
        user.setName("Prodigal JJ");
        user.setAgreeTerms(true);


        when(userRepository.findById(60L)).thenReturn(Optional.of(user));
        when(userSectorRepository.findAllByUserId(60L)).thenReturn(Arrays.asList(userSector1, userSector2));

        if (level.equals(SECTOR)) {
            Sector sector1 = new Sector();
            sector1.setName("HR");

            Sector sector2 = new Sector();
            sector2.setName("Insurance");

            when(sectorRepository.findById(5L)).thenReturn(Optional.of(sector1));
            when(sectorRepository.findById(6L)).thenReturn(Optional.of(sector2));
        } else if (level.equals(MINOR_SECTOR)) {
            MinorSector sector1 = new MinorSector();
            sector1.setName("HR");

            MinorSector sector2 = new MinorSector();
            sector2.setName("Insurance");

            when(minorSectorRepository.findById(5L)).thenReturn(Optional.of(sector1));
            when(minorSectorRepository.findById(6L)).thenReturn(Optional.of(sector2));
        } else if (level.equals(SUB_SECTOR)) {
            SubSector sector1 = new SubSector();
            sector1.setName("HR");

            SubSector sector2 = new SubSector();
            sector2.setName("Insurance");

            when(subSectorRepository.findById(5L)).thenReturn(Optional.of(sector1));
            when(subSectorRepository.findById(6L)).thenReturn(Optional.of(sector2));
        } else if (level.equals(SECTOR_CATEGORY)) {
            SectorCategory sector1 = new SectorCategory();
            sector1.setName("HR");

            SectorCategory sector2 = new SectorCategory();
            sector2.setName("Insurance");

            when(sectorCategoryRepository.findById(5L)).thenReturn(Optional.of(sector1));
            when(sectorCategoryRepository.findById(6L)).thenReturn(Optional.of(sector2));
        }

        UserSectorResponseDto response = userService.getAllUserSectorsById(60L);

        assertThat(response).hasNoNullFieldsOrPropertiesExcept();
        assertThat(response.getSectors()).allSatisfy(sector -> assertThat(sector).hasNoNullFieldsOrPropertiesExcept());
    }

    @Test
    void getAllAuthUserSectors_throws_422_for_User_not_existing() {
        UserSector sector1 = new UserSector();
        UserSector sector2 = new UserSector();

        User currentUser = new User();
        currentUser.setId(2L);
        UsernamePasswordAuthenticationToken usernameToken = new UsernamePasswordAuthenticationToken(currentUser, null);

        SecurityContext context = mock(SecurityContext.class);
        when(context.getAuthentication()).thenReturn(usernameToken);
        SecurityContextHolder.setContext(context);

        when(userSectorRepository.findAllByUserId(2L)).thenReturn(Arrays.asList(sector1, sector2));

        assertThrows(BusinessValidationException.class, () -> userService.getAllAuthUserSectors());
    }

    @ParameterizedTest
    @MethodSource("sectorTypesSource")
    void getAllAuthUserSectors_throws_422_for_sector_not_existing(SectorLevels level) {
        UserSector sector1 = new UserSector();
        sector1.setId(60000L);
        sector1.setSectorType(level);

        User currentUser = new User();
        currentUser.setId(60L);
        UsernamePasswordAuthenticationToken usernameToken = new UsernamePasswordAuthenticationToken(currentUser, null);

        SecurityContext context = mock(SecurityContext.class);
        when(context.getAuthentication()).thenReturn(usernameToken);
        SecurityContextHolder.setContext(context);

        User user = new User();
        when(userRepository.findById(60L)).thenReturn(Optional.of(user));
        when(userSectorRepository.findAllByUserId(60L)).thenReturn(Collections.singletonList(sector1));

        assertThrows(BusinessValidationException.class, () -> userService.getAllAuthUserSectors());
    }

    @Test
    void getAllAuthUserSectors_returns_UserSectorResponseDto() {
        UserSector userSector1 = new UserSector();
        userSector1.setSectorType(SECTOR_CATEGORY);
        userSector1.setId(1L);
        userSector1.setSectorId(5L);
        userSector1.setUserId(5L);

        UserSector userSector2 = new UserSector();
        userSector2.setSectorType(SECTOR_CATEGORY);
        userSector2.setId(2L);
        userSector2.setSectorId(6L);

        User user = new User();
        user.setName("Prodigal JJ");
        user.setAgreeTerms(true);

        SectorCategory sector1 = new SectorCategory();
        sector1.setName("HR");

        SectorCategory sector2 = new SectorCategory();
        sector2.setName("Insurance");

        User currentUser = new User();
        currentUser.setId(60L);

        UsernamePasswordAuthenticationToken usernameToken = new UsernamePasswordAuthenticationToken(currentUser, null);

        SecurityContext context = mock(SecurityContext.class);
        when(context.getAuthentication()).thenReturn(usernameToken);
        SecurityContextHolder.setContext(context);

        when(userRepository.findById(60L)).thenReturn(Optional.of(user));
        when(userSectorRepository.findAllByUserId(60L)).thenReturn(Arrays.asList(userSector1, userSector2));
        when(sectorCategoryRepository.findById(5L)).thenReturn(Optional.of(sector1));
        when(sectorCategoryRepository.findById(6L)).thenReturn(Optional.of(sector2));

        UserSectorResponseDto response = userService.getAllAuthUserSectors();

        assertThat(response).hasNoNullFieldsOrPropertiesExcept();
        assertThat(response.getSectors()).allSatisfy(sector -> assertThat(sector).hasNoNullFieldsOrPropertiesExcept());

    }

    @Test
    void createUserSector_throws_AuthorizationException_when_creating_sector_on_behalf_of_another_user() {
        User currentUser = new User();
        currentUser.setId(6L);
        UsernamePasswordAuthenticationToken usernameToken = new UsernamePasswordAuthenticationToken(currentUser, null);

        SecurityContext context = mock(SecurityContext.class);
        when(context.getAuthentication()).thenReturn(usernameToken);
        SecurityContextHolder.setContext(context);

        UserSectorRequestDto request = new UserSectorRequestDto();
        request.setUserId(5L);

        assertThrows(AuthorizationException.class, () -> userService.createUserSector(request));
    }

    @Test
    void createUserSector_throws_BusinessValidationException_when_authorized_user_not_existing() {
        User currentUser = new User();
        currentUser.setId(6L);
        UsernamePasswordAuthenticationToken usernameToken = new UsernamePasswordAuthenticationToken(currentUser, null);

        SecurityContext context = mock(SecurityContext.class);
        when(context.getAuthentication()).thenReturn(usernameToken);
        SecurityContextHolder.setContext(context);

        UserSectorRequestDto request = new UserSectorRequestDto();
        request.setUserId(6L);

        assertThrows(BusinessValidationException.class, () -> userService.createUserSector(request));
    }

    @Test
    void createUserSector_throws_BusinessValidationException_when_user_already_has_associated_sector() {
        User currentUser = new User();
        currentUser.setId(6L);
        UsernamePasswordAuthenticationToken usernameToken = new UsernamePasswordAuthenticationToken(currentUser, null);

        SecurityContext context = mock(SecurityContext.class);
        when(context.getAuthentication()).thenReturn(usernameToken);
        SecurityContextHolder.setContext(context);

        UserSectorRequestDto request = new UserSectorRequestDto();
        request.setUserId(6L);

        when(userRepository.findById(6L)).thenReturn(Optional.of(currentUser));
        when(userSectorRepository.existsByUserId(6L)).thenReturn(true);

        assertThrows(BusinessValidationException.class, () -> userService.createUserSector(request));
    }

    @Test
    void createUserSector_returns_UserSectorResponseDto() {
        User currentUser = new User();
        currentUser.setId(6L);

        UpdateUserSectorRequestDto.Sector sectorRequest1 = new UpdateUserSectorRequestDto.Sector();
        sectorRequest1.setDepthType(MINOR_SECTOR);
        sectorRequest1.setId(5L);

        UserSectorRequestDto request = new UserSectorRequestDto();
        request.setUserId(6L);
        request.setName("No names");
        request.setSectors(Collections.singleton(sectorRequest1));

        UsernamePasswordAuthenticationToken usernameToken = new UsernamePasswordAuthenticationToken(currentUser, null);

        SecurityContext context = mock(SecurityContext.class);
        when(context.getAuthentication()).thenReturn(usernameToken);
        SecurityContextHolder.setContext(context);

        when(userRepository.save(userCaptor.capture())).thenReturn(currentUser);
        when(userRepository.findById(6L)).thenReturn(Optional.of(currentUser));

        UserSectorResponseDto response = userService.createUserSector(request);

        User capturedUser = userCaptor.getValue();

        assertThat(response).hasNoNullFieldsOrPropertiesExcept();
        assertThat(capturedUser.getName()).isEqualTo("No names");
        assertTrue(capturedUser.getAgreeTerms());

        assertThat(response).hasNoNullFieldsOrPropertiesExcept();
    }

    @Test
    void loadUserByUsername_returns_user_details_from_repository() {
        User userInfo = new User();
        userInfo.setRole(UserRole.USER);
        userInfo.setPassword("nothing");
        userInfo.setEmail("Blabing@mail.com");

        UserDetails userDetails = userInfo;
        when(userRepository.findByEmail("username@mail.com")).thenReturn(userDetails);
        UserDetails user = userService.loadUserByUsername("username@mail.com");

        assertThat(user.getUsername()).isEqualTo(userInfo.getUsername());
        assertThat(user.getPassword()).isEqualTo(userInfo.getPassword());
        assertThat(user.getAuthorities()).hasSize(1);
    }


    @Test
    void updateUserSector_throws_AuthorizationException_when_updating_sector_on_behalf_of_another_user() {
        User currentUser = new User();
        currentUser.setId(6L);
        UsernamePasswordAuthenticationToken usernameToken = new UsernamePasswordAuthenticationToken(currentUser, null);

        SecurityContext context = mock(SecurityContext.class);
        when(context.getAuthentication()).thenReturn(usernameToken);
        SecurityContextHolder.setContext(context);

        UserSectorRequestDto request = new UserSectorRequestDto();
        request.setUserId(5L);

        assertThrows(AuthorizationException.class, () -> userService.updateUserSector(2L, request));
    }

    @Test
    void updateUserSector_throws_BusinessValidationException_when_authorized_user_not_existing() {
        User currentUser = new User();
        currentUser.setId(6L);
        UsernamePasswordAuthenticationToken usernameToken = new UsernamePasswordAuthenticationToken(currentUser, null);

        SecurityContext context = mock(SecurityContext.class);
        when(context.getAuthentication()).thenReturn(usernameToken);
        SecurityContextHolder.setContext(context);

        UserSectorRequestDto request = new UserSectorRequestDto();
        request.setUserId(6L);

        assertThrows(BusinessValidationException.class, () -> userService.updateUserSector(6L, request));
    }

    @Test
    void createUserSector_deletes_existing_user_sectors() {
        User currentUser = new User();
        currentUser.setId(6L);
        UsernamePasswordAuthenticationToken usernameToken = new UsernamePasswordAuthenticationToken(currentUser, null);

        SecurityContext context = mock(SecurityContext.class);
        when(context.getAuthentication()).thenReturn(usernameToken);
        SecurityContextHolder.setContext(context);

        UserSectorRequestDto request = new UserSectorRequestDto();
        request.setUserId(6L);

        when(userRepository.findById(6L)).thenReturn(Optional.of(currentUser));

        assertThrows(NullPointerException.class, () -> userService.updateUserSector(6L, request));

        verify(userSectorRepository).deleteAllByUserId(6L);
    }

    @Test
    void updateUserSector_returns_UserSectorResponseDto() {
        User currentUser = new User();
        currentUser.setId(6L);

        UpdateUserSectorRequestDto.Sector sectorRequest1 = new UpdateUserSectorRequestDto.Sector();
        sectorRequest1.setDepthType(MINOR_SECTOR);
        sectorRequest1.setId(5L);

        UserSectorRequestDto request = new UserSectorRequestDto();
        request.setUserId(6L);
        request.setName("No names");
        request.setSectors(Collections.singleton(sectorRequest1));

        UsernamePasswordAuthenticationToken usernameToken = new UsernamePasswordAuthenticationToken(currentUser, null);

        SecurityContext context = mock(SecurityContext.class);
        when(context.getAuthentication()).thenReturn(usernameToken);
        SecurityContextHolder.setContext(context);

        when(userRepository.save(userCaptor.capture())).thenReturn(currentUser);
        when(userRepository.findById(6L)).thenReturn(Optional.of(currentUser));

        UserSectorResponseDto response = userService.updateUserSector(6L, request);

        User capturedUser = userCaptor.getValue();

        assertThat(response).hasNoNullFieldsOrPropertiesExcept();
        assertThat(capturedUser.getName()).isEqualTo("No names");
        assertTrue(capturedUser.getAgreeTerms());

        assertThat(response).hasNoNullFieldsOrPropertiesExcept();
    }


    @Test
    void signUp_throws_ResourceAlreadyExist_for_User_already_existing() {
        SignUpDto request = new SignUpDto();
        request.setEmail("email@mail.com");

        when(userRepository.findByEmail(anyString())).thenReturn(new User());
        assertThrows(ResourceAlreadyExistException.class, () -> userService.signUp(request));
    }

    @Test
    void signUp_persists_user_info() {
        SignUpDto request = new SignUpDto();
        request.setEmail("email@mail.com");
        request.setPassword("plain password");

        when(userRepository.save(userCaptor.capture())).thenReturn(new User());
        UserDetails response = userService.signUp(request);

        User capturedUser = userCaptor.getValue();
        assertThat(capturedUser.getEmail()).isEqualTo(request.getEmail());
        assertThat(capturedUser.getPassword()).isNotEmpty();
        assertThat(capturedUser.getPassword()).isNotEqualTo(request.getPassword());
        assertNull(capturedUser.getAgreeTerms());
    }
}
