package com.festival.everyday.core.service;

import com.festival.everyday.core.config.jwt.TokenProvider;
import com.festival.everyday.core.domain.user.User;
import com.festival.everyday.core.domain.user.authority.RefreshToken;
import com.festival.everyday.core.dto.response.CreateAccessTokenResponse;
import com.festival.everyday.core.dto.response.LoginResponse;
import com.festival.everyday.core.repository.RefreshTokenRepository;
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
    private final UserService userService;

    // access token은 15분 refresh token은 14일
    private static final Duration ACCESS_EXPIRES  = Duration.ofMinutes(15);
    private static final Duration REFRESH_EXPIRES = Duration.ofDays(14);

    // 사용자당 RT 하나
    private static final boolean SINGLE_RT_PER_USER = true;

    //로그인 시 AT,RT 제공
    @Transactional
    public LoginResponse issueTokens(User user) {
        String access  = tokenProvider.generateToken(user, ACCESS_EXPIRES);
        String refresh = tokenProvider.generateToken(user, REFRESH_EXPIRES);

        Instant now = Instant.now();

        if (SINGLE_RT_PER_USER) {
            refreshTokenRepository.findByUserId(user.getId())
                    .ifPresent(RefreshToken::revoke); // 이전 RT 무효화(또는 delete)
        }

        RefreshToken entity = new RefreshToken(
                user.getId(),
                refresh,
                now,
                now.plus(REFRESH_EXPIRES)
        );
        refreshTokenRepository.save(entity);

        return new LoginResponse(access, refresh);
    }

    // 재발급 메서드(유효 RT로 새 AT 발급)
    public String createNewAccessToken(String refreshToken) {
        RefreshToken rt = refreshTokenService.getActiveByTokenOrThrow(refreshToken);
        User user = userService.findById(rt.getUserId());
        return tokenProvider.generateToken(user, ACCESS_EXPIRES);
    }

    // ---------------------------------------------------------
    // (선택) 3) 재발급 + RT 회전: 재사용 공격 대응이 필요한 경우
    // ---------------------------------------------------------
    @Transactional
    public LoginResponse reissueTokensWithRotation(String refreshToken) {
        RefreshToken old = refreshTokenService.getActiveByTokenOrThrow(refreshToken);
        User user = userService.findById(old.getUserId());

        String access  = tokenProvider.generateToken(user, ACCESS_EXPIRES);
        String refresh = tokenProvider.generateToken(user, REFRESH_EXPIRES);

        // 이전 RT 폐기 후 새 RT 저장(회전)
        old.revoke();
        Instant now = Instant.now();
        refreshTokenRepository.save(new RefreshToken(
                user.getId(),
                refresh,
                now,
                now.plus(REFRESH_EXPIRES)
        ));

        return new LoginResponse(access, refresh);
    }
}
