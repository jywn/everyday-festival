package com.festival.everyday.core.controller;

import com.festival.everyday.core.config.jwt.TokenAuthenticationFilter;
import com.festival.everyday.core.domain.interaction.Favorite;
import com.festival.everyday.core.domain.interaction.Interest;
import com.festival.everyday.core.dto.request.FavoriteRequest;
import com.festival.everyday.core.dto.request.InterestRequest;
import com.festival.everyday.core.dto.response.ApiResponse;
import com.festival.everyday.core.dto.response.FavoriteResponse;
import com.festival.everyday.core.dto.response.InterestResponse;
import com.festival.everyday.core.service.FavoriteService;
import com.festival.everyday.core.service.InterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PatchMapping("/favorites")
    public ResponseEntity<ApiResponse> createFavorite(@RequestBody FavoriteRequest request, @RequestAttribute(name= TokenAuthenticationFilter.ATTR_USER_ID) Long userId, @RequestAttribute(name=TokenAuthenticationFilter.ATTR_USER_TYPE) String userType)
    {
        FavoriteResponse response=favoriteService.createFavorite(userId, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body((new ApiResponse(true,201,"찜 등록/취소에 성공했습니다.",response)));
    }
}
