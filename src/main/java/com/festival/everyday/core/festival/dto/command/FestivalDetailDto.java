package com.festival.everyday.core.festival.dto.command;

import com.festival.everyday.core.application.dto.ApplyStatus;
import com.festival.everyday.core.common.dto.command.PeriodDto;
import com.festival.everyday.core.favorite.dto.FavorStatus;
import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.common.domain.Period;
import com.festival.everyday.core.common.dto.command.AddressDto;
import com.festival.everyday.core.recruit.dto.command.CategoryDto;
import com.festival.everyday.core.user.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.ai.moderation.Categories;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private List<Category> categories = new ArrayList<>();

    // laborRecruit
    private LocalDateTime laborRecruitBegin;
    private LocalDateTime laborRecruitEnd;
    private String laborNotice;
    private String job;
    private String wage;
    private String remark;

    // applyStatus
    private ApplyStatus applyStatus;

    public FestivalDetailDto(String name, String fee, String time, String introduction, String tel,
                             String link, String holderName, LocalDateTime holdBegin, LocalDateTime holdEnd,
                             String city, String district, String detail, String strFavor, String url,
                             LocalDateTime companyRecruitBegin, LocalDateTime companyRecruitEnd,
                             String companyNotice, String preferred,
                             LocalDateTime laborRecruitBegin, LocalDateTime laborRecruitEnd,
                             String laborNotice, String job, String wage, String remark,
                             String strApply) {
        this.name = name;
        this.fee = fee;
        this.time = time;
        this.introduction = introduction;
        this.tel = tel;
        this.link = link;
        this.holderName = holderName;
        this.holdBegin = holdBegin;
        this.holdEnd = holdEnd;
        this.city = city;
        this.district = district;
        this.detail = detail;
        this.favorStatus = FavorStatus.valueOf(strFavor);
        this.imageUrl = url;
        this.companyRecruitBegin = companyRecruitBegin;
        this.companyRecruitEnd = companyRecruitEnd;
        this.companyNotice = companyNotice;
        this.preferred = preferred;
        this.laborRecruitBegin = laborRecruitBegin;
        this.laborRecruitEnd = laborRecruitEnd;
        this.laborNotice = laborNotice;
        this.job = job;
        this.wage = wage;
        this.remark = remark;
        this.applyStatus = ApplyStatus.valueOf(strApply);
    }

    public void addCategories(List<Category> list) {
        this.categories.addAll(list);
    }
}
