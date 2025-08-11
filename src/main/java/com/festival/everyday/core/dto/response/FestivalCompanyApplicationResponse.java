package com.festival.everyday.core.dto.response;

import com.festival.everyday.core.domain.Festival;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class FestivalCompanyApplicationResponse {
    private final Long festivalId;
    private final String festivalName;
    private final List<CompanyApplicationResponse> companyList;

    public static FestivalCompanyApplicationResponse of(Festival festival, List<CompanyApplicationResponse> companyList) {
        return FestivalCompanyApplicationResponse.builder()
                .festivalId(festival.getId())
                .festivalName(festival.getName())
                .companyList(companyList)
                .build();
    }

}
