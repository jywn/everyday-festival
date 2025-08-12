package com.festival.everyday.core.dto.response;

public record LoginResponse(
        String accessToken,
        String refreshToken
) {}