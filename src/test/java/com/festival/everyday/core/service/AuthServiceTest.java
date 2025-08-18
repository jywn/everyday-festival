package com.festival.everyday.core.service;

import com.festival.everyday.core.token.dto.request.LoginRequest;
import com.festival.everyday.core.token.dto.response.LoginResponse;
import com.festival.everyday.core.token.service.AuthService;
import com.festival.everyday.core.token.service.TokenService;
import com.festival.everyday.core.user.domain.User;
import com.festival.everyday.core.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock UserRepository userRepository;
    @Mock PasswordEncoder passwordEncoder;
    @Mock
    TokenService tokenService;

    @InjectMocks
    AuthService authService;

    @Test
    @DisplayName("login: 계정/비밀번호 OK → TokenService.issueTokens 호출, LoginResponse 반환")
    void login_success() {
        // given
        LoginRequest req = new LoginRequest("geo123", "1234");

        User user = mock(User.class);
        when(user.getPassword()).thenReturn("{bcrypt}encoded"); // 아무 문자열 OK

        when(userRepository.findByAccount("geo123"))
                .thenReturn(Optional.of(user));
        when(passwordEncoder.matches("1234", "{bcrypt}encoded"))
                .thenReturn(true);

        LoginResponse issued = new LoginResponse("AT_new", "RT_new");
        when(tokenService.issueTokens(user)).thenReturn(issued);

        // when
        LoginResponse res = authService.login(req);

        // then
        assertNotNull(res);
        assertEquals("AT_new", res.accessToken());
        assertEquals("RT_new", res.refreshToken());

        verify(userRepository).findByAccount("geo123");
        verify(passwordEncoder).matches("1234", "{bcrypt}encoded");
        verify(tokenService).issueTokens(user);
        verifyNoMoreInteractions(userRepository, passwordEncoder, tokenService);
    }

    @Test
    @DisplayName("login: 계정 없음 → IllegalArgumentException('존재하지 않는 계정입니다.')")
    void login_accountNotFound() {
        // given
        LoginRequest req = new LoginRequest("nope", "any");
        when(userRepository.findByAccount("nope"))
                .thenReturn(Optional.empty());

        // when & then
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> authService.login(req)
        );
        assertEquals("존재하지 않는 계정입니다.", ex.getMessage());

        verify(userRepository).findByAccount("nope");
        verifyNoInteractions(passwordEncoder, tokenService);
    }

    @Test
    @DisplayName("login: 비밀번호 불일치 → IllegalArgumentException('비밀번호가 올바르지 않습니다.')")
    void login_badPassword() {
        // given
        LoginRequest req = new LoginRequest("geo123", "wrong");

        User user = mock(User.class);
        when(user.getPassword()).thenReturn("{bcrypt}encoded");
        when(userRepository.findByAccount("geo123"))
                .thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrong", "{bcrypt}encoded"))
                .thenReturn(false);

        // when & then
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> authService.login(req)
        );
        assertEquals("비밀번호가 올바르지 않습니다.", ex.getMessage());

        verify(userRepository).findByAccount("geo123");
        verify(passwordEncoder).matches("wrong", "{bcrypt}encoded");
        verifyNoInteractions(tokenService);
    }
}
