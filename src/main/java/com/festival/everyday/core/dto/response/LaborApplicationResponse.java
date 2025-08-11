package com.festival.everyday.core.dto.response;

import com.festival.everyday.core.domain.application.Application;
import com.festival.everyday.core.domain.application.SELECTED;
import com.festival.everyday.core.domain.user.Company;
import com.festival.everyday.core.domain.user.Labor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class LaborApplicationResponse {
    private final Long applicationId;
    private final LocalDateTime createdAt;
    private final SELECTED selected;

    private final Long id;
    private final String name;

    private final String city;
    private final String district;
    private final String detail;

    public static LaborApplicationResponse from(Application application){
        Labor labor = (Labor) application.getUser();
        return LaborApplicationResponse.builder()
                .applicationId(application.getId())
                .createdAt(application.getCreatedAt())
                .selected(application.getSelected())
                .id(labor.getId())
                .name(labor.getName())
                .city(labor.getAddress().getCity())
                .district(labor.getAddress().getDetail())
                .build();
    }
}
