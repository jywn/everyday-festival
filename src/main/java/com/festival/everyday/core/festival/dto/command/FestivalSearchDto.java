package com.festival.everyday.core.festival.dto.command;
import com.festival.everyday.core.favorite.dto.FavorStatus;
import com.festival.everyday.core.festival.domain.Festival;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class FestivalSearchDto {

    private Long id;
    private String name;
    private String holderName;

    // address
    private String city;
    private String district;
    private String detail;

    // period
    private LocalDateTime begin;
    private LocalDateTime end;

    // favor
    private FavorStatus favorStatus;

    // image
    private String imageUrl;

    public FestivalSearchDto(Long id, String name, String holderName,
                             String city, String district, String detail,
                             LocalDateTime begin, LocalDateTime end,
                             String strFavor, String url) {
        this.id = id;
        this.name = name;
        this.holderName = holderName;
        this.city = city;
        this.district = district;
        this.detail = detail;
        this.begin = begin;
        this.end = end;
        this.favorStatus = FavorStatus.valueOf(strFavor);
        this.imageUrl = url;
    }

//    public static FestivalSearchDto of(Festival festival, FavorStatus favorStatus, String imageUrl) {
//        return new FestivalSearchDto(festival.getId(), festival.getName(),
//                festival.getAddress().getCity(), festival.getAddress().getDistrict(), festival.getAddress().getDetail(),
//                festival.getPeriod().getBegin(), festival.getPeriod().getEnd(),
//                favorStatus, imageUrl);
//    }
}
