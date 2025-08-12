package com.festival.everyday.core.service;

import com.festival.everyday.core.config.jwt.TokenProvider;
import com.festival.everyday.core.domain.user.User;
import com.festival.everyday.core.domain.user.authority.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@RequiredArgsConstructor
@Service
public class TokenService {
    /* refresh token을 받아 새 access token을 발급받는 용도
     */
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken) {
        /* refresh token이 유효한지 체크하고, refreshToken의 entity에서
           userId를 가져와 uesr 객체를 가져온다. 그 이후, 해당 유저에게 accessToken을 15분 발급
        * */
       RefreshToken rt = refreshTokenService.getActiveByTokenOrThrow(refreshToken);

       User user = userService.findById(rt.getUserId());

       return tokenProvider.generateToken(user, Duration.ofMinutes(15));
    }
}