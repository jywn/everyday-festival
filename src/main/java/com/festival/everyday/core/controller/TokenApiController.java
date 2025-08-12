package com.festival.everyday.core.controller;

import com.festival.everyday.core.dto.request.CreateAccessTokenRequest;
import com.festival.everyday.core.dto.response.CreateAccessTokenResponse;
import com.festival.everyday.core.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenApiController {
    private final TokenService tokenService;

    @PostMapping("/api/token")
    public ResponseEntity<CreateAccessTokenResponse> createAccessToken(
            @RequestBody CreateAccessTokenRequest req) {

        String newAccess = tokenService.createNewAccessToken(req.getRefreshToken());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateAccessTokenResponse(newAccess));
    }
}
