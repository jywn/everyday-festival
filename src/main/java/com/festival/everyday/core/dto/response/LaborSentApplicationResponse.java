package com.festival.everyday.core.dto.response;

import com.festival.everyday.core.domain.Festival;
import com.festival.everyday.core.domain.application.Application;
import com.festival.everyday.core.domain.application.SELECTED;
import com.festival.everyday.core.domain.user.Holder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class LaborSentApplicationResponse {

    private final Long festivalId;
    private final String festivalName;
    private final String holderName;
    private final LocalDateTime holdBegin;
    private final LocalDateTime holdEnd;
    private final LocalDateTime createdAt;
    private final SELECTED selected;

    public static LaborSentApplicationResponse of(Application application) {
        Festival festival=application.getFestival();
        Holder holder=festival.getHolder();

        return LaborSentApplicationResponse.builder()
                .festivalId(festival.getId())
                .festivalName(festival.getName())
                .holderName(holder.getName())
                .holdBegin(festival.getPeriod().getBegin())
                .holdEnd(festival.getPeriod().getEnd())
                .selected(application.getSelected())
                .build();
    }
}
