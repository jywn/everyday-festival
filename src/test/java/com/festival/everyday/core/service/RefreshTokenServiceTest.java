package com.festival.everyday.core.service;

import com.festival.everyday.core.token.domain.RefreshToken;
import com.festival.everyday.core.token.repository.RefreshTokenRepository;
import com.festival.everyday.core.token.service.RefreshTokenService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RefreshTokenServiceTest {

    @Mock
    RefreshTokenRepository refreshTokenRepository;

    @InjectMocks
    RefreshTokenService refreshTokenService;

    @Nested
    @DisplayName("getActiveByTokenOrThrow")
    class GetActiveByTokenOrThrow {

        @Test
        @DisplayName("성공: 유효한 RT이면 엔티티를 반환한다")
        void success_validToken() {
            // given
            String rt = "RT_valid";
            RefreshToken entity = new RefreshToken(
                    1L, rt, Instant.now(), Instant.now().plusSeconds(3600)
            );  //RefreshToken(Long userId, String refresh_token, Instant issuedAt, Instant expiresAt)

            when(refreshTokenRepository.findByRefreshToken(rt))
                    .thenReturn(Optional.of(entity));

            // when
            RefreshToken result = refreshTokenService.getActiveByTokenOrThrow(rt);

            // then
            assertNotNull(result);
            assertEquals(rt, result.getRefreshToken());
            assertFalse(result.isRevoked());
            assertTrue(result.getExpiresAt().isAfter(Instant.now().minusSeconds(1)));
        }

        @Test
        @DisplayName("실패: DB에 존재하지 않는 RT이면 IllegalArgumentException(Invalid refresh token)")
        void fail_notFound() {
            // given
            String rt = "RT_whereareyou?";
            when(refreshTokenRepository.findByRefreshToken(rt))
                    .thenReturn(Optional.empty());

            // when & then
            IllegalArgumentException ex = assertThrows(
                    IllegalArgumentException.class,
                    () -> refreshTokenService.getActiveByTokenOrThrow(rt)
            );
            assertEquals("Invalid refresh token", ex.getMessage());
        }

        @Test
        @DisplayName("실패: 철회된 RT이면 IllegalArgumentException(Refresh token revoked)")
        void fail_revoked() {
            // given
            String rt = "RT_revoked";
            RefreshToken entity = new RefreshToken(
                    2L, rt, Instant.now(), Instant.now().plusSeconds(3600)
            );
            entity.revoke(); // revoked = true
            when(refreshTokenRepository.findByRefreshToken(rt))
                    .thenReturn(Optional.of(entity));

            // when & then
            IllegalArgumentException ex = assertThrows(
                    IllegalArgumentException.class,
                    () -> refreshTokenService.getActiveByTokenOrThrow(rt)
            );
            assertEquals("Refresh token revoked", ex.getMessage());
        }

        @Test
        @DisplayName("실패: 만료된 RT이면 IllegalArgumentException(Refresh token expired)")
        void fail_expired() {
            // given
            String rt = "RT_expired";
            RefreshToken entity = new RefreshToken(
                    3L, rt, Instant.now().minusSeconds(7200), // issued 2h ago
                    Instant.now().minusSeconds(10)            // expired 10s ago
            );
            when(refreshTokenRepository.findByRefreshToken(rt))
                    .thenReturn(Optional.of(entity));

            // when & then
            IllegalArgumentException ex = assertThrows(
                    IllegalArgumentException.class,
                    () -> refreshTokenService.getActiveByTokenOrThrow(rt)
            );
            assertEquals("Refresh token expired", ex.getMessage());
        }
    }
}
