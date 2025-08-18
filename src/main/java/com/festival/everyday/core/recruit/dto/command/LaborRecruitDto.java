package com.festival.everyday.core.recruit.dto.command;

import com.festival.everyday.core.recruit.domain.ExtraQuestion;
import com.festival.everyday.core.recruit.domain.LaborRecruit;
import com.festival.everyday.core.common.dto.command.PeriodDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LaborRecruitDto {
    private PeriodDto periodDto;
    private String notice;
    private String job;
    private String wage;
    private String remark;
    private List<String> extraQuestions;


    public static LaborRecruitDto from(LaborRecruit laborRecruit) {
        return new LaborRecruitDto(
                PeriodDto.from(laborRecruit.getPeriod()),
                laborRecruit.getNotice(),
                laborRecruit.getJob(),
                laborRecruit.getWage(),
                laborRecruit.getRemark(),
                laborRecruit.getExtraQuestions().stream().map(ExtraQuestion::getContent).toList()
        );

    }
}
