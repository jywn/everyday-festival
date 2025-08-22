package com.festival.everyday.core.recruit.dto.response;

import com.festival.everyday.core.recruit.dto.command.LaborRecruitDto;
import com.festival.everyday.core.common.dto.command.PeriodDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LaborRecruitResponse {

    private PeriodDto period;
    private String job;
    private String wage;
    private String notice;
    private String remark;
    private List<String> extraQuestions;

    public static LaborRecruitResponse from(LaborRecruitDto laborRecruitDto) {
        return new LaborRecruitResponse(
                laborRecruitDto.getPeriodDto(), laborRecruitDto.getJob(), laborRecruitDto.getWage(),
                laborRecruitDto.getNotice(), laborRecruitDto.getRemark(), laborRecruitDto.getExtraQuestions());
    }
}
