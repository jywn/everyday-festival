package com.festival.everyday.core.dto.request;

import com.festival.everyday.core.domain.Festival;
import com.festival.everyday.core.domain.interaction.Favorite;
import com.festival.everyday.core.domain.interaction.Interest;
import com.festival.everyday.core.domain.interaction.ReceiverType;
import com.festival.everyday.core.domain.user.Company;
import com.festival.everyday.core.domain.user.User;
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
