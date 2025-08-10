package com.festival.everyday.core.domain.notice;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = FestivalDuePayload.class, name = "FESTIVAL_DUE"),
        @JsonSubTypes.Type(value = CompanyAppliedPayload.class, name = "COMPANY_APPLIED")
})
public interface NoticePayload {

}
