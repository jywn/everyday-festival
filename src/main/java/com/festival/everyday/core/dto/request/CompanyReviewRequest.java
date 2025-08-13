package com.festival.everyday.core.dto.request;

import com.festival.everyday.core.domain.interaction.ReceiverType;
import com.festival.everyday.core.domain.interaction.Review;
import com.festival.everyday.core.domain.interaction.SenderType;
import com.festival.everyday.core.domain.user.User;
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
