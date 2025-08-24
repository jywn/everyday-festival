package com.festival.everyday.core.company.dto.response;

import com.festival.everyday.core.common.CategoryMapper;
import com.festival.everyday.core.common.dto.command.AddressDto;
import com.festival.everyday.core.company.dto.command.RecommendCompanyDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecommendCompanyResponse {

    private Long id;
    private String name;

    private AddressDto address;

    private String category;

    private String imageUrl;

    public static RecommendCompanyResponse from(RecommendCompanyDto dto) {
        return new RecommendCompanyResponse(
                dto.getId(), dto.getName(),
                AddressDto.of(dto.getCity(), dto.getDistrict(), dto.getDetail()),
                CategoryMapper.enumToStr(dto.getCategory()),
                dto.getImageUrl()
        );
    }
}
