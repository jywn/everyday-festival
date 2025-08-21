package com.festival.everyday.core.recruit.controller;

import com.festival.everyday.core.recruit.dto.request.CreateCompanyRecruitRequest;
import com.festival.everyday.core.recruit.dto.request.CreateLaborRecruitRequest;
import com.festival.everyday.core.common.dto.response.ApiResponse;
import com.festival.everyday.core.recruit.dto.response.CompanyRecruitResponse;
import com.festival.everyday.core.recruit.dto.response.LaborRecruitResponse;
import com.festival.everyday.core.recruit.dto.response.RecruitWithQuestionsResponse;
import com.festival.everyday.core.recruit.service.RecruitCommandService;
import com.festival.everyday.core.recruit.service.RecruitQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RecruitApiController {

    private final RecruitCommandService recruitCommandService;
    private final RecruitQueryService recruitQueryService;

    @PostMapping("/festivals/{festivalId}/company-recruit")
    public ResponseEntity<ApiResponse<Long>> createCompanyRecruit(@RequestBody CreateCompanyRecruitRequest request, @PathVariable Long festivalId) {
        return ResponseEntity.ok(ApiResponse.success("업체 모집 공고 등록에 성공했습니다.", recruitCommandService.saveCompanyRecruit(request, festivalId)));
    }

    @PostMapping("/festivals/{festivalId}/labor-recruit")
    public ResponseEntity<ApiResponse<Long>> createLaborRecruit(@RequestBody CreateLaborRecruitRequest request, @PathVariable Long festivalId) {
        return ResponseEntity.ok(ApiResponse.success("근로자 모집 공고 등록에 성공했습니다.", recruitCommandService.saveLaborRecruit(request, festivalId)));
    }

    @GetMapping("/recruits/{recruitId}")
    public ResponseEntity<ApiResponse<RecruitWithQuestionsResponse>> getRecruitWithQuestions(@PathVariable Long recruitId) {
        RecruitWithQuestionsResponse response = RecruitWithQuestionsResponse.from(recruitQueryService.findRecruitWithQuestions(recruitId));
        return ResponseEntity.ok(ApiResponse.success("모집 공고와 추가 질문 조회에 성공하였습니다.", response));
    }
//    @GetMapping("/festivals/{festivalId}/company-recruit")
//    public ResponseEntity<ApiResponse<CompanyRecruitResponse>> viewCompanyRecruit(@PathVariable Long festivalId) {
//        CompanyRecruitResponse companyRecruitResponse = CompanyRecruitResponse.from(recruitQueryService.findCompanyRecruit(festivalId));
//        return ResponseEntity.ok(ApiResponse.success("업체 모집 공고 조회에 성공하였습니다.", companyRecruitResponse));
//    }
//
//    @GetMapping("/festivals/{festivalId}/labor-recruit")
//    public ResponseEntity<ApiResponse<LaborRecruitResponse>> viewLaborRecruit(@PathVariable Long festivalId) {
//        LaborRecruitResponse laborRecruitResponse = LaborRecruitResponse.from(recruitQueryService.findLaborRecruit(festivalId));
//        return ResponseEntity.ok(ApiResponse.success("근로자 모집 공고 조회에 성공하였습니다.", laborRecruitResponse));
//    }
}
