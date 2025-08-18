package com.festival.everyday.core.favorite.dto.response;

import com.festival.everyday.core.favorite.domain.Favorite;
import com.festival.everyday.core.favorite.dto.FavorStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FavoriteResponse {

    private Long favoriteId;
    private FavorStatus favored;

    public static FavoriteResponse of(Favorite favorite, FavorStatus favored)
    {
        return FavoriteResponse.builder()
                .favoriteId(favorite.getId())
                .favored(favored)
                .build();
    }
}
