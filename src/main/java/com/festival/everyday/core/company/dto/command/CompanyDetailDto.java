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

    // ← 추가: 0/1을 받아 enum으로 변환하는 생성자 (프로젝션 순서와 정확히 맞출 것)
    public CompanyDetailDto(
            String name, Category category, String introduction, String ceoName, String tel, String email, String link,
            String city, String district, String detail,
            Integer favorBit,           // 0 or 1
            String imageUrl
    ) {
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

        this.favorStatus = (favorBit != null && favorBit == 1)
                ? FavorStatus.FAVORED : FavorStatus.NOT_FAVORED;

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
