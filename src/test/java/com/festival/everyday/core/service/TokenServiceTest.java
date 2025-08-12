package com.festival.everyday.core.service;

import com.festival.everyday.core.config.jwt.TokenProvider;
import com.festival.everyday.core.domain.user.User;
import com.festival.everyday.core.domain.user.authority.RefreshToken;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TokenServiceTest {

    @Mock TokenProvider tokenProvider;
    @Mock RefreshTokenService refreshTokenService;
    @Mock UserService userService;

    @InjectMocks TokenService tokenService;

    @Test
    @DisplayName("성공: 유효한 RT로 15분짜리 AT 발급")
    void createNewAccessToken_success() {
        // given
        String rtValue = "RT_valid";
        long userId = 100L;
        //rt 발급
        RefreshToken rtEntity =
                new RefreshToken(userId, rtValue, Instant.now(), Instant.now().plusSeconds(3600));
        User user = mock(User.class);

        when(refreshTokenService.getActiveByTokenOrThrow(rtValue)).thenReturn(rtEntity);
        when(userService.findById(userId)).thenReturn(user);

        ArgumentCaptor<Duration> durationCaptor = ArgumentCaptor.forClass(Duration.class);
        when(tokenProvider.generateToken(eq(user), durationCaptor.capture()))
                .thenReturn("AT_new");

        // when
        String result = tokenService.createNewAccessToken(rtValue);

        // then
        assertEquals("AT_new", result);
        assertEquals(Duration.ofMinutes(15), durationCaptor.getValue());

        verify(refreshTokenService).getActiveByTokenOrThrow(rtValue);
        verify(userService).findById(userId);
        verify(tokenProvider).generateToken(eq(user), any(Duration.class));
        verifyNoMoreInteractions(refreshTokenService, userService, tokenProvider);
    }

    @Test
    @DisplayName("실패: RT 미유효 → IllegalArgumentException 전파")
    void createNewAccessToken_invalidRefreshToken() {
        // given
        String bad = "RT_bad";
        when(refreshTokenService.getActiveByTokenOrThrow(bad))
                .thenThrow(new IllegalArgumentException("Invalid refresh token"));

        // when & then
        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class,
                        () -> tokenService.createNewAccessToken(bad));
        assertEquals("Invalid refresh token", ex.getMessage());

        verify(refreshTokenService).getActiveByTokenOrThrow(bad);
        verifyNoInteractions(userService, tokenProvider);
    }

    @Test
    @DisplayName("실패: 사용자 없음 → EntityNotFoundException 전파")
    void createNewAccessToken_userNotFound() {
        // given
        String rt = "RT_ok";
        long userId = 1L;
        RefreshToken rtEntity =
                new RefreshToken(userId, rt, Instant.now(), Instant.now().plusSeconds(3600));

        when(refreshTokenService.getActiveByTokenOrThrow(rt)).thenReturn(rtEntity);
        when(userService.findById(userId)).thenThrow(new EntityNotFoundException("User not found"));

        // when & then
        assertThrows(EntityNotFoundException.class,
                () -> tokenService.createNewAccessToken(rt));

        verify(refreshTokenService).getActiveByTokenOrThrow(rt);
        verify(userService).findById(userId);
        verifyNoInteractions(tokenProvider);
    }
}
