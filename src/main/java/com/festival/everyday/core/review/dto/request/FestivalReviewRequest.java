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
}
