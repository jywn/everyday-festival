//package com.festival.everyday.core.application.dto.response;
//
//
//import com.festival.everyday.core.application.dto.command.ApplicationDetailDto;
//import com.festival.everyday.core.user.domain.Category;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//
//import java.util.List;
//
//@Getter
//@Builder
//@AllArgsConstructor
//public class LaborApplicationDetailResponse {
//
//    // company
//    private Category category;
//    private String name;
//    private String tel;
//    private String email;
//
//    // application
//    private List<String> answers;
//    private List<String> extraAnswers;
//
//    // recruit
//    private List<String> extraQuestions;
//
//    public static CompanyApplicationDetailResponse from(ApplicationDetailDto applicationDetailDto) {
//        return new CompanyApplicationDetailResponse(
//                applicationDetailDto.getCategory(),
//                applicationDetailDto.getName(),
//                applicationDetailDto.getTel(),
//                applicationDetailDto.getEmail(),
//                applicationDetailDto.getAnswers(),
//                applicationDetailDto.getExtraAnswers(),
//                applicationDetailDto.getExtraQuestions()
//        );
//    }
//}
