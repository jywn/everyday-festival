package com.festival.everyday.core.application.controller;

import com.festival.everyday.core.application.dto.command.CompanyApplicationSimpleDto;
import com.festival.everyday.core.application.dto.command.MyApplicationSimpleDto;
import com.festival.everyday.core.application.dto.request.UpdateApplicationStatusRequest;
import com.festival.everyday.core.application.dto.response.*;
import com.festival.everyday.core.application.service.ApplicationCommandService;
import com.festival.everyday.core.application.service.ApplicationQueryService;
import com.festival.everyday.core.common.config.jwt.TokenAuthenticationFilter;
import com.festival.everyday.core.common.dto.response.ApiResponse;
import com.festival.everyday.core.application.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;
    private final ApplicationQueryService applicationQueryService;
    private final ApplicationCommandService applicationCommandService;

    // 업체 지원 목록을 조회합니다.
    @GetMapping("/festivals/{festivalId}/company-applications")
    public ResponseEntity<ApiResponse<List<CompanyApplicationSimpleResponse>>> getCompanyApplications(@PathVariable Long festivalId,
                                                                                                      @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID) Long holderId,
                                                                                                      @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_TYPE) String userType)
    {
        List<CompanyApplicationSimpleResponse> result = applicationQueryService.getCompanyApplications(festivalId, holderId, userType).stream()
                .map(CompanyApplicationSimpleResponse::from).toList();

        return ResponseEntity.ok(ApiResponse.success("업체 지원 목록 조회를 성공하였습니다.", result));
    }

    // 근로자 지원 목록을 조회합니다.
    @GetMapping("/festivals/{festivalId}/labor-applications")
    public ResponseEntity<ApiResponse<List<LaborApplicationSimpleResponse>>> getLaborApplications(@PathVariable Long festivalId,
                                                            @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID) Long holderId,
                                                            @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_TYPE) String userType)
    {
        List<LaborApplicationSimpleResponse> result = applicationQueryService.getLaborApplications(festivalId, holderId, userType)
                .stream().map(LaborApplicationSimpleResponse::from).toList();

        return ResponseEntity.ok(ApiResponse.success("근로자 지원 목록 조회를 성공하였습니다.", result));
    }

    @GetMapping("/festivals/{festivalId}/company-applications/{companyApplicationId}")
    public ResponseEntity<ApiResponse> getCompanyApplicationDetail(@PathVariable Long companyApplicationId, @PathVariable Long festivalId, @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID) Long userId,
                                                                   @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_TYPE) String userType) {
        CompanyApplicationDetailResponse response = applicationService.getCompanyApplicationDetail(companyApplicationId, festivalId, userId, userType);

        return ResponseEntity
                .ok(new ApiResponse(true,200,"업체가 작성한 지원서 조회에 성공하였습니다.",response));
    }

    @GetMapping("/festivals/{festivalId}/labor-applications/{laborApplicationId}")
    public ResponseEntity<ApiResponse> getLaborApplicationDetail(@PathVariable Long laborApplicationId, @PathVariable Long festivalId, @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID) Long userId,
                                                                 @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_TYPE) String userType) {
        LaborApplicationDetailResponse response = applicationService.getLaborApplicationDetail(laborApplicationId, festivalId, userId, userType);

        return ResponseEntity
                .ok(new ApiResponse(true,200,"근로자가 작성한 지원서 조회에 성공하였습니다.",response));
    }

    @GetMapping("/company-applications")
    public ResponseEntity<ApiResponse<List<MyApplicationSimpleResponse>>> getMyApplications(@RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID) Long userId) {
        List<MyApplicationSimpleResponse> result = applicationQueryService.getMyApplications(userId).stream().map(MyApplicationSimpleResponse::from).toList();

        return ResponseEntity.ok(ApiResponse.success("나의 지원 목록 조회에 성공하였습니다.", result));
    }

    //수락하기
    @PatchMapping("/festivals/{festivalId}/applications/{applicationId}/accept")
    public ResponseEntity<Void> acceptApplication(Long applicationId)
    {
        applicationCommandService.selectApplication(applicationId);
        return ResponseEntity.ok().build();
    }

    //거절하기
    @PatchMapping("/festivals/{festivalId}/applications/{applicationId}/deny")
    public ResponseEntity<Void> denyApplication(Long applicationId)
    {
        applicationCommandService.denyApplication(applicationId);
        return ResponseEntity.ok().build();
    }



}
