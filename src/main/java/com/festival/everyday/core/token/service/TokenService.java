package com.festival.everyday.core.token.service;

import com.festival.everyday.core.common.config.jwt.TokenProvider;
import com.festival.everyday.core.user.domain.User;
import com.festival.everyday.core.token.domain.RefreshToken;
import com.festival.everyday.core.token.dto.response.LoginResponse;
import com.festival.everyday.core.token.repository.RefreshTokenRepository;
import com.festival.everyday.core.user.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenService refreshTokenService;
    private final UserQueryService userQueryService;

    // access token 은 15분 refresh token 은 14일
    private static final Duration ACCESS_EXPIRES  = Duration.ofMinutes(15);
    private static final Duration REFRESH_EXPIRES = Duration.ofDays(14);

    // [MOD] 로그인 시 AT,RT 제공 (sid 사용 시)
    @Transactional
    public LoginResponse issueTokens(User user) {
        String refresh = tokenProvider.generateRefreshToken(user, REFRESH_EXPIRES);   // RT 생성
        Instant now = Instant.now();

        RefreshToken saved = refreshTokenRepository.save(
                new RefreshToken(user.getId(), refresh, now, now.plus(REFRESH_EXPIRES))
        );

        String access  = tokenProvider.generateAccessToken(user, ACCESS_EXPIRES, saved.getTokenId()); // AT 생성
        return new LoginResponse(access, refresh);
    }


    // 재발급 메서드(유효 RT로 새 AT 발급)
    public String createNewAccessToken(String refreshToken) {
        RefreshToken rt = refreshTokenService.getActiveByTokenOrThrow(refreshToken);    //RT 유효한지 체크
        User user = userQueryService.findById(rt.getUserId());                               //RT로 User를 찾기
        // 기존 세션 유지: AT에 sid 로 rt.getTokenId() 포함
        return tokenProvider.generateAccessToken(user, ACCESS_EXPIRES, rt.getTokenId()); //Access token 지급
    }
}
