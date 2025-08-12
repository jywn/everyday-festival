package com.festival.everyday.core.controller;

import com.festival.everyday.core.dto.request.CreateAccessTokenRequest;
import com.festival.everyday.core.dto.request.LoginRequest;
import com.festival.everyday.core.dto.response.CreateAccessTokenResponse;
import com.festival.everyday.core.dto.response.LoginResponse;
import com.festival.everyday.core.service.AuthService;
import com.festival.everyday.core.service.TokenService;
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
}
