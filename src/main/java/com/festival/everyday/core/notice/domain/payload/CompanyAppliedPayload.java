package com.festival.everyday.core.notice.domain.payload;

import com.festival.everyday.core.company.domain.Company;
import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.notice.domain.Interested;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class CompanyAppliedPayload implements NoticePayload {

    // company
    private Long companyId;
    private String companyName;

    // festival
    private Long festivalId;
    private String festivalName;

    // interest
    private Interested interested;

    public static CompanyAppliedPayload of(Long companyId, String companyName, Long festivalId, String festivalName, Interested interested) {
        return new CompanyAppliedPayload(companyId, companyName, festivalId, festivalName, interested);
    }
}
