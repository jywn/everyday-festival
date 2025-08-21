package com.festival.everyday.core.company.dto.command;

import com.festival.everyday.core.user.domain.Category;
import com.festival.everyday.core.company.domain.Company;
import com.festival.everyday.core.common.dto.command.AddressDto;
import com.festival.everyday.core.favorite.dto.FavorStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyDetailDto {
    private String name;
    private Category category;
    private String introduction;
    private String ceoName;
    private String tel;
    private String email;
    private String link;

    // address
    private String city;
    private String district;
    private String detail;

    // favorite
    private FavorStatus favorStatus;

    // image
    private String imageUrl;

    public CompanyDetailDto(String name, Category category, String introduction,
                            String ceoName, String tel, String email, String link,
                            String city, String district, String detail,
                            String favorStatus, String imageUrl) {
        this.name = name;
        this.category = category;
        this.introduction = introduction;
        this.ceoName = ceoName;
        this.tel = tel;
        this.email = email;
        this.link = link;
        this.city = city;
        this.district = district;
        this.detail = detail;
        this.favorStatus = FavorStatus.valueOf(favorStatus);
        this.imageUrl = imageUrl;
    }

    public static CompanyDetailDto from(Company company, FavorStatus favorStatus, String imageUrl) {
        return new CompanyDetailDto(
                company.getName(),
                company.getCategory(),
                company.getIntroduction(),
                company.getCeoName(),
                company.getTel(),
                company.getEmail(),
                company.getLink(),
                company.getAddress().getCity(), company.getAddress().getDistrict(), company.getAddress().getDetail(),
                favorStatus, imageUrl);
    }

}
