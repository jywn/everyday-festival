package com.festival.everyday.core.interest.dto.request;

import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.interest.domain.Interest;
import com.festival.everyday.core.company.domain.Company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InterestRequest {
    private Long festivalId;
    public Interest toEntity(Company company, Festival festival)
    {
        return Interest.create(company, festival);
    }
}
