package com.festival.everyday.core.service;

import com.festival.everyday.core.domain.user.authority.RefreshToken;
import com.festival.everyday.core.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    // refresh token 디비 존재 여부 + 만료(Expired) + 철회(Revoked) 검증 수행
    public RefreshToken getActiveByTokenOrThrow(String refreshToken) {
        RefreshToken rt = refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Invalid refresh token"));

        if (rt.isRevoked()) {
            throw new IllegalArgumentException("Refresh token revoked");
        }
        if (rt.getExpiresAt().isBefore(Instant.now())) {
            throw new IllegalArgumentException("Refresh token expired");
        }
        return rt;
    }

    @Transactional
    public void revokeById(Long sid) {
        refreshTokenRepository.findById(sid).ifPresent(rt -> {
            if (!rt.isRevoked()) rt.setRevoked(true); // 변경감지로 UPDATE
        });
    }
}
