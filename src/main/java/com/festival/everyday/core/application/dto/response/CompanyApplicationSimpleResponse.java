package com.festival.everyday.core.application.dto.response;

import com.festival.everyday.core.application.domain.SELECTED;
import com.festival.everyday.core.application.dto.command.CompanyApplicationSimpleDto;
import com.festival.everyday.core.common.dto.command.AddressDto;
import com.festival.everyday.core.company.domain.Company;
import com.festival.everyday.core.user.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
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
                SimpleCompanyDto.of(companyApplicationSimpleDto), companyApplicationSimpleDto.getImageUrl());
    }

    @Data
    @AllArgsConstructor
    static class SimpleCompanyDto {
        private Long id;
        private String name;
        private Category category;
        private AddressDto addressDto;

        public static SimpleCompanyDto of(CompanyApplicationSimpleDto companyApplicationSimpleDto) {
            return new SimpleCompanyDto(
                    companyApplicationSimpleDto.getCompanyId(), companyApplicationSimpleDto.getCompanyName(), companyApplicationSimpleDto.getCategory(),
                    AddressDto.of(companyApplicationSimpleDto.getCity(), companyApplicationSimpleDto.getDistrict(), companyApplicationSimpleDto.getDetail()));
        }
    }
}
