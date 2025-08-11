package com.festival.everyday.core.controller;

import com.festival.everyday.core.dto.response.*;
import com.festival.everyday.core.security.UserDetailsImpl;
import com.festival.everyday.core.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping("/festivals/{festivalId}/company-applications")
    public ResponseEntity<ApiResponse> getCompanyApplications(@PathVariable Long festivalId)
    {
        FestivalCompanyApplicationResponse response=applicationService.getCompanyApplications(festivalId);

        return ResponseEntity
                .ok()
                .body(new ApiResponse(true,200,"업체 지원서 목록 조회에 성공했습니다.",response));
    }

    @GetMapping("/festivals/{festivalId}/labor-applications")
    public ResponseEntity<ApiResponse> getLaborApplications(@PathVariable Long festivalId)
    {
        FestivalLaborApplicationResponse response=applicationService.getLaborApplications(festivalId);

        return ResponseEntity
                .ok()
                .body(new ApiResponse(true,200,"근로자 지원서 목록 조회에 성공했습니다.",response));
    }

    @GetMapping("/company-applications/{companyApplicationId}")
    public ResponseEntity<ApiResponse> getCompanyApplicationDetail(@PathVariable Long companyApplicationId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long companyId=userDetails.getUser().getId();

        CompanyApplicationDetailResponse response=applicationService.getCompanyApplicationDetail(companyApplicationId, companyId);

        return ResponseEntity
                .ok(new ApiResponse(true,200,"업체가 작성한 지원서 조회에 성공하였습니다.",response));
    }

    @GetMapping("/labor-applications/{laborApplicationId}")
    public ResponseEntity<ApiResponse> getLaborApplicationDetail(@PathVariable Long laborApplicationId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long laborId=userDetails.getUser().getId();

        LaborApplicationDetailResponse response=applicationService.getLaborApplicationDetail(laborApplicationId, laborId);

        return ResponseEntity
                .ok(new ApiResponse(true,200,"근로자가 작성한 지원서 조회에 성공하였습니다.",response));
    }

    @GetMapping("/company-applications")
    public ResponseEntity<ApiResponse> getSentApplicationsForCompany(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long companyId=userDetails.getUser().getId();

        List<CompanySentApplicationResponse> response=applicationService.getSentApplicationsForCompany(companyId);

        return ResponseEntity
                .ok(new ApiResponse(true,200, "업체가 지원한 축제 목록 조회에 성공했습니다."));
    }

    @GetMapping("/labor-applications")
    public ResponseEntity<ApiResponse> getSentApplicationsForLabor(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long laborId=userDetails.getUser().getId();

        List<LaborSentApplicationResponse> response=applicationService.getSentApplicationsForLabor(laborId);

        return ResponseEntity
                .ok(new ApiResponse(true,200, "근로자가 지원한 축제 목록 조회에 성공했습니다."));
    }

}
