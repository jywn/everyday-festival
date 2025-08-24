package com.festival.everyday.core.company.dto.command;

import com.festival.everyday.core.common.Mapper.ImageMapper;
import com.festival.everyday.core.favorite.dto.FavorStatus;
import com.festival.everyday.core.image.domain.Image;
import com.festival.everyday.core.user.domain.Category;
import com.festival.everyday.core.company.domain.Company;
import com.festival.everyday.core.common.dto.command.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanySearchDto {

    private Long id;
    private String name;
    private Category category;

    // address
    private String city;
    private String district;
    private String detail;

    // favor
    private FavorStatus favorStatus;

    // image
    private String imageUrl;

    public CompanySearchDto(Long id, String name, Category category, String city, String district, String detail, String strFavor, String imageUrl) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.city = city;
        this.district = district;
        this.detail = detail;
        this.favorStatus = FavorStatus.valueOf(strFavor);
        this.imageUrl = ImageMapper.serverUrlToDomain(imageUrl);
    }
}
