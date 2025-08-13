package com.festival.everyday.core.dto.response;

import com.festival.everyday.core.domain.Festival;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class LaborApplicationListResponse {
    private final Long festivalId;
    private final String festivalName;
    private final List<LaborApplicationResponse> laborList;

    public static LaborApplicationListResponse of(Festival festival, List<LaborApplicationResponse> laborList) {
        return LaborApplicationListResponse.builder()
                .festivalId(festival.getId())
                .festivalName(festival.getName())
                .laborList(laborList)
                .build();
    }
}