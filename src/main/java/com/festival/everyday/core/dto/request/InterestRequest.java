package com.festival.everyday.core.dto.request;

import com.festival.everyday.core.domain.Festival;
import com.festival.everyday.core.domain.Interest;
import com.festival.everyday.core.domain.user.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InterestRequest {

    private Long festivalId;

    public Interest toEntity(Festival festival, Company company)
    {
        return Interest.builder()
                .festival(festival)
                .company(company)
                .build();
    }
}
