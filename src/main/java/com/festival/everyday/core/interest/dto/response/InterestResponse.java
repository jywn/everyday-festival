package com.festival.everyday.core.interest.dto.response;

import com.festival.everyday.core.interest.domain.Interest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class InterestResponse {

    private Long interestId;

    public static InterestResponse of(Interest interest)
    {
        return InterestResponse.builder()
                .interestId(interest.getId())
                .build();
    }

}
