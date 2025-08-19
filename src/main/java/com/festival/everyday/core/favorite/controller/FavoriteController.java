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
@RequestMapping("/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PutMapping
    public ResponseEntity<ApiResponse<Long>> createFavorite (
            @RequestBody FavoriteRequest request,
            @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID) Long userId,
            @RequestAttribute(name=TokenAuthenticationFilter.ATTR_USER_TYPE) String userType)
    {
        Long id = favoriteService.favor(userId, request);

        return ResponseEntity.ok((ApiResponse.success("찜 등록에 성공했습니다.", id)));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteFavorite (
            @RequestBody FavoriteRequest request,
            @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID) Long userId,
            @RequestAttribute(name=TokenAuthenticationFilter.ATTR_USER_TYPE) String userType)
    {
        favoriteService.unFavor(userId, request);

        return ResponseEntity.noContent().build();
    }
}
