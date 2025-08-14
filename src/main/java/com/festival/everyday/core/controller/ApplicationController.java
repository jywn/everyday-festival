package com.festival.everyday.core.controller;

import com.festival.everyday.core.config.jwt.TokenAuthenticationFilter;
import com.festival.everyday.core.dto.response.*;
import com.festival.everyday.core.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping("/festivals/{festivalId}/company-applications")
    public ResponseEntity<ApiResponse> getCompanyApplications(@PathVariable Long festivalId, @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID) Long holderId,
                                                              @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_TYPE) String userType)
    {
        CompanyApplicationListResponse response=applicationService.getCompanyApplications(festivalId, holderId, userType);

        return ResponseEntity
                .ok()
                .body(new ApiResponse(true,200,"업체 지원 목록 조회에 성공했습니다.",response));
    }

    @GetMapping("/festivals/{festivalId}/labor-applications")
    public ResponseEntity<ApiResponse> getLaborApplications(@PathVariable Long festivalId, @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID) Long holderId,
                                                            @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_TYPE) String userType)
    {
        LaborApplicationListResponse response=applicationService.getLaborApplications(festivalId, holderId, userType);

        return ResponseEntity
                .ok()
                .body(new ApiResponse(true,200,"근로자 지원 목록 조회에 성공했습니다.",response));
    }

    @GetMapping("/festivals/{festivalId}/company-applications/{companyApplicationId}")
    public ResponseEntity<ApiResponse> getCompanyApplicationDetail(@PathVariable Long companyApplicationId, @PathVariable Long festivalId, @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID) Long userId,
                                                                   @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_TYPE) String userType) {
        CompanyApplicationDetailResponse response=applicationService.getCompanyApplicationDetail(companyApplicationId, festivalId, userId, userType);

        return ResponseEntity
                .ok(new ApiResponse(true,200,"업체가 작성한 지원서 조회에 성공하였습니다.",response));
    }

    @GetMapping("/festivals/{festivalId}/labor-applications/{laborApplicationId}")
    public ResponseEntity<ApiResponse> getLaborApplicationDetail(@PathVariable Long laborApplicationId, @PathVariable Long festivalId, @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID) Long userId,
                                                                 @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_TYPE) String userType) {
        LaborApplicationDetailResponse response=applicationService.getLaborApplicationDetail(laborApplicationId, festivalId, userId, userType);

        return ResponseEntity
                .ok(new ApiResponse(true,200,"근로자가 작성한 지원서 조회에 성공하였습니다.",response));
    }

    @GetMapping("/company-applications")
    public ResponseEntity<ApiResponse> getSentApplicationsForCompany(@RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID) Long companyId) {
        List<CompanySentApplicationResponse> response=applicationService.getSentApplicationsForCompany(companyId);

        return ResponseEntity
                .ok(new ApiResponse(true,200, "업체가 지원한 축제 목록 조회에 성공했습니다."));
    }

    @GetMapping("/labor-applications")
    public ResponseEntity<ApiResponse> getSentApplicationsForLabor(@RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID) Long laborId) {
        List<LaborSentApplicationResponse> response=applicationService.getSentApplicationsForLabor(laborId);

        return ResponseEntity
                .ok(new ApiResponse(true,200, "근로자가 지원한 축제 목록 조회에 성공했습니다."));
    }

}
