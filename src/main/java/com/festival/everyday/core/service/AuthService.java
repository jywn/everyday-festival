package com.festival.everyday.core.service;

import com.festival.everyday.core.dto.request.LoginRequest;
import com.festival.everyday.core.dto.response.LoginResponse;
import com.festival.everyday.core.domain.user.User;
import com.festival.everyday.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Transactional
    public LoginResponse login(LoginRequest req) {
        User user = userRepository.findByAccount(req.account())    //account로 찾기
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));

        if (!passwordEncoder.matches(req.password(), user.getPassword())) {     //user의 password와 받은 password 매치
            throw new IllegalArgumentException("비밀번호가 올바르지 않습니다.");
        }

        return tokenService.issueTokens(user);
    }
}
