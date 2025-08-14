package com.festival.everyday.core.dto.response;

import com.festival.everyday.core.domain.recruit.ExtraQuestion;
import com.festival.everyday.core.dto.LaborRecruitDto;
import com.festival.everyday.core.dto.LaborRecruitWithApplyDto;
import com.festival.everyday.core.dto.PeriodDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class LaborRecruitResponse {

    private PeriodDto periodDto;
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
