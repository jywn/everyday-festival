package com.festival.everyday.core.service;

import com.festival.everyday.core.config.jwt.TokenProvider;
import com.festival.everyday.core.domain.user.User;
import com.festival.everyday.core.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;


//로그인 하는 과정
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService; // 선택: RT 쓰면

    public AuthResult login(String account, String rawPassword) {
        User user = userRepository.findByAccount(account)
                .orElseThrow(() -> new IllegalArgumentException("account가 없습니다."));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new IllegalArgumentException("password가 일치하지 않습니다.");
        }

        // 인증 성공 access token은 15분
        String accessToken = tokenProvider.generateToken(user, Duration.ofMinutes(15));

        // 리프레시 토큰은 14일
        String refreshToken = refreshTokenService.issue(user.getId(), Duration.ofDays(14));

        return new AuthResult(accessToken, refreshToken); // refreshToken은 안 쓰면 null
    }

    @Getter
    @AllArgsConstructor
    public static class AuthResult {
        private final String accessToken;
        private final String refreshToken;
    }
}
