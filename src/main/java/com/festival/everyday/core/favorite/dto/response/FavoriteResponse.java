package com.festival.everyday.core.favorite.dto.response;

import com.festival.everyday.core.favorite.domain.Favorite;
import com.festival.everyday.core.favorite.dto.FavorStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FavoriteResponse {

    private Long favoriteId;

    public static FavoriteResponse from(Long id)
    {
        return new FavoriteResponse(id);
    }
}
