package com.festival.everyday.core.favorite.controller;

import com.festival.everyday.core.common.config.jwt.TokenAuthenticationFilter;
import com.festival.everyday.core.favorite.dto.request.FavoriteRequest;
import com.festival.everyday.core.common.dto.response.ApiResponse;
import com.festival.everyday.core.favorite.dto.response.FavoriteResponse;
import com.festival.everyday.core.favorite.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
