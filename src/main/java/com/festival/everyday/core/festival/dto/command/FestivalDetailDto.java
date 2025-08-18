package com.festival.everyday.core.festival.dto.command;

import com.festival.everyday.core.application.dto.ApplyStatus;
import com.festival.everyday.core.common.dto.command.PeriodDto;
import com.festival.everyday.core.favorite.dto.FavorStatus;
import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.common.domain.Period;
import com.festival.everyday.core.common.dto.command.AddressDto;
import com.festival.everyday.core.user.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.ai.moderation.Categories;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class FestivalDetailDto {

    private String name;
    private String fee;
    private String time;
    private String introduction;
    private String tel;
    private String link;

    // holder
    private String holderName;

    // period
    private LocalDateTime holdBegin;
    private LocalDateTime holdEnd;

    // address
    private String city;
    private String district;
    private String detail;

    // favorite
    private FavorStatus favorStatus;

    // image
    private String imageUrl;

    // companyRecruit
    private LocalDateTime companyRecruitBegin;
    private LocalDateTime companyRecruitEnd;
    private String companyNotice;
    private String preferred;
    private List<Category> categories;

    // laborRecruit
    private LocalDateTime laborRecruitBegin;
    private LocalDateTime laborRecruitEnd;
    private String laborNotice;
    private String job;
    private String wage;
    private String remark;

    // applyStatus
    private ApplyStatus applyStatus;


}
