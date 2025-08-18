package com.festival.everyday.core.review.dto.request;

import com.festival.everyday.core.common.dto.ReceiverType;
import com.festival.everyday.core.review.domain.Review;
import com.festival.everyday.core.common.dto.SenderType;
import com.festival.everyday.core.user.domain.User;
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
