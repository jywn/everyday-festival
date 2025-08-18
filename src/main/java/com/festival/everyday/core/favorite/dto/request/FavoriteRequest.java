package com.festival.everyday.core.favorite.dto.request;

import com.festival.everyday.core.favorite.domain.Favorite;
import com.festival.everyday.core.common.dto.ReceiverType;
import com.festival.everyday.core.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteRequest {

    private Long receiverId;
    private ReceiverType receiverType;

    public Favorite toEntity(User sender, ReceiverType receiverType, Long receiverId)
    {
        return Favorite.create(sender, receiverType, receiverId);
    }
}
