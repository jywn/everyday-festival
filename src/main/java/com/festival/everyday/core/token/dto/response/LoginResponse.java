package com.festival.everyday.core.token.dto.response;

public record LoginResponse(
        String accessToken,
        String refreshToken
) {}