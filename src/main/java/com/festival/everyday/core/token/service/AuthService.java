package com.festival.everyday.core.token.service;

import com.festival.everyday.core.token.dto.request.LoginRequest;
import com.festival.everyday.core.token.dto.response.LoginResponse;
import com.festival.everyday.core.token.exception.InvalidPasswordException;
import com.festival.everyday.core.user.domain.User;
import com.festival.everyday.core.user.exception.UserNotFoundException;
import com.festival.everyday.core.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final RefreshTokenService refreshTokenService;
    @Transactional
    public LoginResponse login(LoginRequest req) {
        User user = userRepository.findByAccount(req.account())    //account로 찾기
                .orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(req.password(), user.getPassword())) {     //user의 password와 받은 password 매치
            throw new InvalidPasswordException();
        }

        return tokenService.issueTokens(user);
    }

    @Transactional
    public void logout(Long sid) {
        if (sid == null) return; // idempotent
        refreshTokenService.revokeById(sid);
    }

}
