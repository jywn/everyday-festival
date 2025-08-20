package com.festival.everyday.core.notice.domain.payload;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = FestivalDuePayload.class, name = "FESTIVAL_DUE"),
        @JsonSubTypes.Type(value = CompanyAppliedPayload.class, name = "COMPANY_APPLIED"),
        @JsonSubTypes.Type(value = LaborAppliedPayload.class, name = "LABOR_APPLIED"),
        @JsonSubTypes.Type(value = ApplyAcceptedPayload.class, name = "APPLY_ACCEPTED"),
        @JsonSubTypes.Type(value = ApplyDeniedPayload.class, name = "APPLY_DENIED"),
        @JsonSubTypes.Type(value = FestivalInterestPayload.class, name = "FESTIVAL_INTEREST"),
        @JsonSubTypes.Type(value = RecruitDeadPayload.class, name = "RECRUIT_DEAD")
})
public interface NoticePayload {

}
