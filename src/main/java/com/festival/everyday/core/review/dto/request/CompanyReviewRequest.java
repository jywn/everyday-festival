package com.festival.everyday.core.review.dto.request;

import com.festival.everyday.core.common.dto.ReceiverType;
import com.festival.everyday.core.review.domain.Review;
import com.festival.everyday.core.common.dto.SenderType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CompanyReviewRequest {

    private String content;
    private Long festivalId;

    public Review toEntity(SenderType senderType, Long companyId, ReceiverType receiverType) {
        return Review.create(this.festivalId, senderType, companyId, receiverType, this.content);
    }
}
