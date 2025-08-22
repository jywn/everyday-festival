package com.festival.everyday.core.festival.dto.response;

import com.festival.everyday.core.application.dto.ApplyStatus;
import com.festival.everyday.core.common.dto.command.AddressDto;
import com.festival.everyday.core.common.dto.command.PeriodDto;
import com.festival.everyday.core.favorite.dto.FavorStatus;
import com.festival.everyday.core.festival.dto.command.FestivalSimpleDto;
import com.festival.everyday.core.recruit.dto.command.CompanyRecruitDto;
import com.festival.everyday.core.recruit.dto.command.CompanyRecruitWithApplyDto;
import com.festival.everyday.core.festival.dto.command.FestivalDetailDto;
import com.festival.everyday.core.recruit.dto.command.LaborRecruitWithApplyDto;
import com.festival.everyday.core.recruit.dto.RecruitStatus;
import com.festival.everyday.core.user.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
public class FestivalDetailResponse {

    private FestivalOnlyDto festivalOnlyDto;

    private RecruitStatus companyRecruitStatus;
    private SimpleCompanyRecruitDto companyRecruitDto;

    private RecruitStatus laborRecruitStatus;
    private SimpleLaborRecruitDto laborRecruitDto;

    public static FestivalDetailResponse from(FestivalDetailDto festivalDetailDto) {
        RecruitStatus companyStatus = RecruitStatus.RECRUITING;
        if (festivalDetailDto.getCompanyRecruitBegin() == null) {
            companyStatus = RecruitStatus.NOT_RECRUITING;
        }
        RecruitStatus laborStatus = RecruitStatus.RECRUITING;
        if (festivalDetailDto.getLaborRecruitBegin() == null) {
            laborStatus = RecruitStatus.NOT_RECRUITING;
        }

        return new FestivalDetailResponse(
            FestivalOnlyDto.from(festivalDetailDto),
                companyStatus,
                SimpleCompanyRecruitDto.from(festivalDetailDto),
                laborStatus,
                SimpleLaborRecruitDto.from(festivalDetailDto)
        );
    }

    @Data
    @AllArgsConstructor
    public static class FestivalOnlyDto {
        private String name;
        private String fee;
        private String time;
        private String introduction;
        private String tel;
        private String link;

        private String holderName;

        private String imageUrl;

        private PeriodDto period;
        private AddressDto address;

        private FavorStatus favorStatus;

        private ApplyStatus applyStatus;

        public static FestivalOnlyDto from(FestivalDetailDto festivalDetailDto) {
            return new FestivalOnlyDto(
                    festivalDetailDto.getName(), festivalDetailDto.getFee(), festivalDetailDto.getTime(), festivalDetailDto.getIntroduction(),
                    festivalDetailDto.getTel(), festivalDetailDto.getLink(), festivalDetailDto.getHolderName(), festivalDetailDto.getImageUrl(),
                    PeriodDto.of(festivalDetailDto.getHoldBegin(), festivalDetailDto.getHoldEnd()),
                    AddressDto.of(festivalDetailDto.getCity(), festivalDetailDto.getDistrict(), festivalDetailDto.getDetail()),
                            festivalDetailDto.getFavorStatus(), festivalDetailDto.getApplyStatus());
        }
    }

    @Data
    @AllArgsConstructor
    public static class SimpleCompanyRecruitDto {
        public Long id;
        private PeriodDto period;
        private String notice;
        private String preferred;
        private List<Category> categories;

        public static SimpleCompanyRecruitDto from(FestivalDetailDto festivalDetailDto) {
            return new SimpleCompanyRecruitDto(
                    festivalDetailDto.getCompanyRecruitId(),
                    PeriodDto.of(festivalDetailDto.getCompanyRecruitBegin(), festivalDetailDto.getCompanyRecruitEnd()),
                    festivalDetailDto.getCompanyNotice(), festivalDetailDto.getPreferred(), festivalDetailDto.getCategories()
            );
        }
    }

    @Data
    @AllArgsConstructor
    public static class SimpleLaborRecruitDto {
        public Long id;
        private PeriodDto period;
        private String notice;
        private String job;
        private String wage;
        private String remark;

        public static SimpleLaborRecruitDto from(FestivalDetailDto festivalDetailDto) {
            return new SimpleLaborRecruitDto(
                    festivalDetailDto.getLaborRecruitId(),
                    PeriodDto.of(festivalDetailDto.getLaborRecruitBegin(), festivalDetailDto.getLaborRecruitEnd()),
                    festivalDetailDto.getLaborNotice(), festivalDetailDto.getJob(), festivalDetailDto.getWage(), festivalDetailDto.getRemark()
            );
        }
    }
}
