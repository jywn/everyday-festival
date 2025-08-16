package com.festival.everyday.core.api;

import com.festival.everyday.core.dto.request.CreateCompanyRecruitRequest;
import com.festival.everyday.core.dto.request.CreateLaborRecruitRequest;
import com.festival.everyday.core.dto.response.ApiResponse;
import com.festival.everyday.core.dto.response.CompanyRecruitResponse;
import com.festival.everyday.core.dto.response.LaborRecruitResponse;
import com.festival.everyday.core.service.RecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RecruitApiController {

    private final RecruitService recruitService;

    @PostMapping("/festivals/{festivalId}/company-recruit")
    public ResponseEntity<ApiResponse<Long>> createCompanyRecruit(@RequestBody CreateCompanyRecruitRequest request) {
        return ResponseEntity.ok(ApiResponse.success("업체 모집 공고 등록에 성공했습니다.", recruitService.saveCompanyRecruit(request)));
    }

    @PostMapping("/festivals/{festivalId}/labor-recruit")
    public ResponseEntity<ApiResponse<Long>> createLaborRecruit(@RequestBody CreateLaborRecruitRequest request) {
        return ResponseEntity.ok(ApiResponse.success("근로자 모집 공고 등록에 성공했습니다.", recruitService.saveLaborRecruit(request)));
    }

    @GetMapping("/festivals/{festivalId}/company-recruit")
    public ResponseEntity<ApiResponse<CompanyRecruitResponse>> viewCompanyRecruit(@PathVariable Long festivalId) {
        CompanyRecruitResponse companyRecruitResponse = CompanyRecruitResponse.from(recruitService.findCompanyRecruit(festivalId));
        return ResponseEntity.ok(ApiResponse.success("업체 모집 공고 조회에 성공하였습니다.", companyRecruitResponse));
    }

    @GetMapping("/festivals/{festivalId}/labor-recruit")
    public ResponseEntity<ApiResponse<LaborRecruitResponse>> viewLaborRecruit(@PathVariable Long festivalId) {
        LaborRecruitResponse laborRecruitResponse = LaborRecruitResponse.from(recruitService.findLaborRecruit(festivalId));
        return ResponseEntity.ok(ApiResponse.success("근로자 모집 공고 조회에 성공하였습니다.", laborRecruitResponse));
    }

}
