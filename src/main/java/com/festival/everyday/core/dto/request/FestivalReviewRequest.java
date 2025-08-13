package com.festival.everyday.core.dto.request;

import com.festival.everyday.core.domain.interaction.ReceiverType;
import com.festival.everyday.core.domain.interaction.Review;
import com.festival.everyday.core.domain.interaction.SenderType;
import com.festival.everyday.core.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FestivalReviewRequest {

    private String content;

    public Review toEntity(User sender, SenderType senderType, Long festivalId, ReceiverType receiverType) {
        return Review.create(sender.getId(), senderType, festivalId, receiverType, this.content);
    }
}
