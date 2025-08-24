package com.festival.everyday.core.application.dto.response;

import com.festival.everyday.core.application.domain.SELECTED;
import com.festival.everyday.core.application.dto.command.CompanyApplicationSimpleDto;
import com.festival.everyday.core.common.Mapper.CategoryMapper;
import com.festival.everyday.core.common.Mapper.ImageMapper;
import com.festival.everyday.core.common.dto.command.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CompanyApplicationSimpleResponse {

    //application
    private Long id;
    private SELECTED selected;

    // company
    SimpleCompanyDto simpleCompany;

    // image
    private String imageUrl;

    public static CompanyApplicationSimpleResponse from(CompanyApplicationSimpleDto companyApplicationSimpleDto) {
        return new CompanyApplicationSimpleResponse(companyApplicationSimpleDto.getId(), companyApplicationSimpleDto.getSelected(),
                SimpleCompanyDto.of(companyApplicationSimpleDto), ImageMapper.serverUrlToDomain(companyApplicationSimpleDto.getImageUrl()));
    }

    @Data
    @AllArgsConstructor
    static class SimpleCompanyDto {

        private Long id;
        private String name;
        private String category;
        private AddressDto address;

        public static SimpleCompanyDto of(CompanyApplicationSimpleDto companyApplicationSimpleDto) {
            return new SimpleCompanyDto(
                    companyApplicationSimpleDto.getCompanyId(), companyApplicationSimpleDto.getCompanyName(), CategoryMapper.enumToStr(companyApplicationSimpleDto.getCategory()),
                    AddressDto.of(companyApplicationSimpleDto.getCity(), companyApplicationSimpleDto.getDistrict(), companyApplicationSimpleDto.getDetail()));
        }
    }
}
