//package com.festival.everyday.core.application.dto.response;
//
//import com.festival.everyday.core.festival.domain.Festival;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//
//import java.util.List;
//
//@Getter
//@Builder
//@AllArgsConstructor
//public class CompanyApplicationListResponse {
//    private final Long festivalId;
//    private final String festivalName;
//    private final List<CompanyApplicationResponse> companyList;
//
//    public static CompanyApplicationListResponse of(Festival festival, List<CompanyApplicationResponse> companyList) {
//        return CompanyApplicationListResponse.builder()
//                .festivalId(festival.getId())
//                .festivalName(festival.getName())
//                .companyList(companyList)
//                .build();
//    }
//
//}
