package com.festival.everyday.core.favorite.dto.request;

import com.festival.everyday.core.common.domain.ReceiverType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteRequest {

    private Long receiverId;
    private ReceiverType receiverType;
}
