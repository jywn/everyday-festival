package com.festival.everyday.core.controller;

import com.festival.everyday.core.config.jwt.TokenAuthenticationFilter;
import com.festival.everyday.core.dto.request.UpdateApplicationStatusRequest;
import com.festival.everyday.core.dto.response.*;
import com.festival.everyday.core.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //업체 수락/거절하기
    @PatchMapping("/festivals/{festivalId}/company-applications/{companyId}/status")
    public ResponseEntity<UpdateApplicationStatusResponse> updateCompanyStatus(
            @PathVariable Long festivalId,
            @PathVariable Long companyId,
            @RequestBody UpdateApplicationStatusRequest req,
            @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID) Long holderId,
            @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_TYPE) String userType
    ) {
        return ResponseEntity.ok(
                applicationService.updateCompanyApplicationStatus(
                        festivalId, holderId, userType, companyId, req.getSelected()
                )
        );
    }

    //근로자 수락/거절하기
    @PatchMapping("/festivals/{festivalId}/labor-applications/{laborId}/status")
    public ResponseEntity<UpdateApplicationStatusResponse> updateLaborStatus(
            @PathVariable Long festivalId,
            @PathVariable Long laborId,
            @RequestBody UpdateApplicationStatusRequest req,
            @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID) Long holderId,
            @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_TYPE) String userType
    ) {
        return ResponseEntity.ok(
                applicationService.updateLaborApplicationStatus(
                        festivalId, holderId, userType, laborId, req.getSelected()
                )
        );
    }

}
