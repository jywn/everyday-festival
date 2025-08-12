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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TokenServiceTest {

    @Mock TokenProvider tokenProvider;
    @Mock RefreshTokenService refreshTokenService;
    @Mock UserService userService;

    @InjectMocks TokenService tokenService;

    @Test
    @DisplayName("유효한 RT → 새 AT 문자열 반환(만료 15분)")
    void createNewAccessToken_success() {
        String rtValue = "RT_valid";
        long userId = 100L;
        RefreshToken rt = new RefreshToken(userId, rtValue, Instant.now(), Instant.now().plusSeconds(3600));
        User user = mock(User.class);

        when(refreshTokenService.getActiveByTokenOrThrow(rtValue)).thenReturn(rt);
        when(userService.findById(userId)).thenReturn(user);

        ArgumentCaptor<Duration> dur = ArgumentCaptor.forClass(Duration.class);
        when(tokenProvider.generateToken(eq(user), dur.capture())).thenReturn("AT_new");

        // ← 여기!
        String result = tokenService.createNewAccessToken(rtValue);

        assertEquals("AT_new", result);
        assertEquals(Duration.ofMinutes(15), dur.getValue());
        verifyNoMoreInteractions(refreshTokenService, userService, tokenProvider);
    }

    @Test
    @DisplayName("RT 무효 → IllegalArgumentException 전파")
    void createNewAccessToken_invalidRT() {
        String bad = "RT_bad";
        when(refreshTokenService.getActiveByTokenOrThrow(bad))
                .thenThrow(new IllegalArgumentException("Invalid refresh token"));

        assertThrows(IllegalArgumentException.class, () -> tokenService.createNewAccessToken(bad));
        verifyNoInteractions(userService, tokenProvider);
    }

    @Test
    @DisplayName("유저 없음 → EntityNotFoundException 전파")
    void createNewAccessToken_userNotFound() {
        String rtVal = "RT_ok";
        long uid = 1L;
        RefreshToken rt = new RefreshToken(uid, rtVal, Instant.now(), Instant.now().plusSeconds(3600));

        when(refreshTokenService.getActiveByTokenOrThrow(rtVal)).thenReturn(rt);
        when(userService.findById(uid)).thenThrow(new EntityNotFoundException("Unexpected User"));

        assertThrows(EntityNotFoundException.class, () -> tokenService.createNewAccessToken(rtVal));
        verifyNoInteractions(tokenProvider);
    }
}
