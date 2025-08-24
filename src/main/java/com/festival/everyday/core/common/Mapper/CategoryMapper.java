package com.festival.everyday.core.common.Mapper;

import com.festival.everyday.core.recruit.dto.command.CategoryDto;
import com.festival.everyday.core.user.domain.Category;

public class CategoryMapper {

    public static String enumToStr(Category category) {
        return switch (category) {
            case Category.ART -> "공연/예술";
            case Category.ENTERTAINMENT -> "오락";
            case Category.EXPERIENCE -> "체험";
            case Category.SALE -> "판매";
            case Category.FOOD -> "푸드";
            case Category.CAMPAIGN -> "홍보/캠페인";
            case Category.ETC -> "기타";
        };
    }

    public static String enumToStr(CategoryDto dto) {
        return switch (dto.getCategory()) {
            case Category.ART -> "공연/예술";
            case Category.ENTERTAINMENT -> "오락";
            case Category.EXPERIENCE -> "체험";
            case Category.SALE -> "판매";
            case Category.FOOD -> "푸드";
            case Category.CAMPAIGN -> "홍보/캠페인";
            case Category.ETC -> "기타";
        };
    }
}
