package com.festival.everyday.core.dto.response;

import com.festival.everyday.core.domain.application.Application;
import com.festival.everyday.core.domain.application.SELECTED;
import com.festival.everyday.core.domain.user.Category;
import com.festival.everyday.core.domain.user.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class CompanyApplicationResponse {
    private final Long applicationId;
    private final LocalDateTime createdAt;
    private final SELECTED selected;

    private final Long id;
    private final String name;
    private final Category category;

    private final String city;
    private final String district;
    private final String detail;

    public static CompanyApplicationResponse from(Application application){
        Company company = (Company) application.getUser();
            return CompanyApplicationResponse.builder()
                    .applicationId(application.getId())
                    .createdAt(application.getCreatedAt())
                    .selected(application.getSelected())
                    .id(company.getId())
                    .name(company.getName())
                    .category(company.getCategory())
                    .city(company.getAddress().getCity())
                    .district(company.getAddress().getDetail())
                    .build();
        }
    }
