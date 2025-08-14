package com.festival.everyday.core.dto;

import com.festival.everyday.core.domain.interaction.Favorite;
import com.festival.everyday.core.domain.user.Category;
import com.festival.everyday.core.domain.user.Company;
import lombok.Data;

@Data
public class CompanyDetailDto {
    private String name;
    private FavorStatus favored;
    private AddressDto addressdto;
    private Category category;
    private String introduction;
    private String ceoName;
    private String tel;
    private String email;
    private String link;

    private CompanyDetailDto(String name, AddressDto addressDto, Category category, String introduction, String ceoName, String tel, String email, String link) {
        this.name = name;
        this.addressdto = addressDto;
        this.category = category;
        this.introduction = introduction;
        this.ceoName = ceoName;
        this.tel = tel;
        this.email = email;
        this.link = link;
    }

    public static CompanyDetailDto from(Company company) {
        return new CompanyDetailDto(
                company.getName(),
                AddressDto.from(company.getAddress()),
                company.getCategory(),
                company.getIntroduction(),
                company.getCeoName(),
                company.getTel(),
                company.getEmail(),
                company.getLink());
    }

}
