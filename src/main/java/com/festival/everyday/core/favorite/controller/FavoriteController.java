package com.festival.everyday.core.favorite.controller;

import com.festival.everyday.core.common.config.jwt.TokenAuthenticationFilter;
import com.festival.everyday.core.favorite.dto.request.FavoriteRequest;
import com.festival.everyday.core.common.dto.response.ApiResponse;
import com.festival.everyday.core.favorite.service.FavoriteCommandService;
import com.festival.everyday.core.favorite.service.FavoriteQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favorites")
public class FavoriteController {

    private final FavoriteQueryService favoriteQueryService;
    private final FavoriteCommandService favoriteCommandService;

    @PutMapping
    public ResponseEntity<ApiResponse<Long>> createFavorite (
            @RequestBody FavoriteRequest request,
            @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID) Long userId,
            @RequestAttribute(name=TokenAuthenticationFilter.ATTR_USER_TYPE) String userType)
    {
        Long id = favoriteCommandService.favor(userId, request);

        return ResponseEntity.ok((ApiResponse.success("찜 등록에 성공했습니다.", id)));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteFavorite (
            @RequestBody FavoriteRequest request,
            @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID) Long userId,
            @RequestAttribute(name=TokenAuthenticationFilter.ATTR_USER_TYPE) String userType)
    {
        favoriteCommandService.unFavor(userId, request);

        return ResponseEntity.noContent().build();
    }
}
