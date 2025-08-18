package com.festival.everyday.core.token.controller;

import com.festival.everyday.core.common.config.jwt.TokenAuthenticationFilter;
import com.festival.everyday.core.token.dto.request.CreateAccessTokenRequest;
import com.festival.everyday.core.token.dto.request.LoginRequest;
import com.festival.everyday.core.token.dto.response.CreateAccessTokenResponse;
import com.festival.everyday.core.token.dto.response.LoginResponse;
import com.festival.everyday.core.token.service.AuthService;
import com.festival.everyday.core.token.service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final TokenService tokenService;

    // 1) 로그인: AT + RT
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }

    // 2) 재발급: RT -> 새 AT
    @PostMapping("/refresh")
    public ResponseEntity<CreateAccessTokenResponse> refresh(@RequestBody CreateAccessTokenRequest req) {
        String newAccess = tokenService.createNewAccessToken(req.getRefreshToken()); // ← String
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateAccessTokenResponse(newAccess));
    }

    // 3) 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestAttribute(name = TokenAuthenticationFilter.ATTR_SESSION_ID,required = false)Long token_id) {
        if (token_id == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        authService.logout(token_id);
        return ResponseEntity.status(HttpStatus.SEE_OTHER) // 303
                .header(HttpHeaders.LOCATION, "/mock-login.html") // 리다이렉트 경로
                .body("로그아웃 잘 되었습니다.");
    }

    //@PostMapping("/mock-login")

}
