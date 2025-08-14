package com.festival.everyday.core.dto.response;

import com.festival.everyday.core.domain.interaction.Favorite;
import com.festival.everyday.core.domain.interaction.Interest;
import com.festival.everyday.core.dto.FavorStatus;
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
