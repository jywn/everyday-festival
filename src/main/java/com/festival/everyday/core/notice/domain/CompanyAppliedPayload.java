package com.festival.everyday.core.notice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyAppliedPayload implements NoticePayload {
    private Long companyId;
    private Long festivalId;
    private String festivalName;
    private Interested interested;
}
