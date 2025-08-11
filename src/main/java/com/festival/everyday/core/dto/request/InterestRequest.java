package com.festival.everyday.core.dto.request;

import com.festival.everyday.core.domain.Festival;
import com.festival.everyday.core.domain.interaction.Interest;
import com.festival.everyday.core.domain.user.Company;

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
