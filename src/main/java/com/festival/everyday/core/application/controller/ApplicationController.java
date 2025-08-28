package com.festival.everyday.core.application.controller;

import com.festival.everyday.core.application.domain.SELECTED;
import com.festival.everyday.core.application.dto.Progress;
import com.festival.everyday.core.application.dto.command.CompanyApplicationSimpleDto;
import com.festival.everyday.core.application.dto.request.ApplicationRequest;
import com.festival.everyday.core.application.dto.response.*;
import com.festival.everyday.core.application.service.ApplicationCommandService;
import com.festival.everyday.core.application.service.ApplicationQueryService;
import com.festival.everyday.core.common.config.jwt.TokenAuthenticationFilter;
import com.festival.everyday.core.common.dto.response.ApiResponse;
import com.festival.everyday.core.common.dto.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationQueryService applicationQueryService;
    private final ApplicationCommandService applicationCommandService;

    // 업체가 축제에 지원합니다.
    @PostMapping("/festivals/{festivalId}/company-applications")
    public ResponseEntity<ApiResponse<Long>> createCompanyApplication(@RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID)Long userId,
                                                                      @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_TYPE)String userType,
                                                                      @PathVariable Long festivalId, @RequestBody ApplicationRequest request) {
        // 지원서를 생성하고 식별자를 반환합니다.
        Long applicationId = applicationCommandService.createCompanyApplication(userId, festivalId, request);
        return ResponseEntity.ok(ApiResponse.success("지원서 제출에 성공하였습니다.", applicationId));
    }

    // 근로자가 축제에 지원합니다.
    @PostMapping("/festivals/{festivalId}/labor-applications")
    public ResponseEntity<ApiResponse<Long>> createLaborApplication(@RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID)Long userId,
                                                                    @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_TYPE)String userType,
                                                                    @PathVariable Long festivalId, @RequestBody ApplicationRequest request) {
        // 지원서를 생성하고 식별자를 반환합니다.
        Long applicationId = applicationCommandService.createLaborApplication(userId, festivalId, request);
        return ResponseEntity.ok(ApiResponse.success("지원서 제출에 성공하였습니다.", applicationId));
    }

    // 업체 지원 목록을 조회합니다.
    @GetMapping("/festivals/{festivalId}/company-applications")
    public ResponseEntity<ApiResponse<PageResponse<CompanyApplicationSimpleResponse>>> getCompanyApplications(@PathVariable Long festivalId,
                                                                                                              @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID) Long holderId,
                                                                                                              @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_TYPE) String userType,
                                                                                                              @RequestParam(required = false) SELECTED status,
                                                                                                              Pageable pageable) {

        PageResponse<CompanyApplicationSimpleResponse> result = PageResponse.from(applicationQueryService.getCompanyApplications(festivalId, holderId, userType, pageable, status)
                .map(CompanyApplicationSimpleResponse::from));

        return ResponseEntity.ok(ApiResponse.success("업체 지원 목록 조회를 성공하였습니다.", result));
    }

    // 근로자 지원 목록을 조회합니다.
    @GetMapping("/festivals/{festivalId}/labor-applications")
    public ResponseEntity<ApiResponse<PageResponse<LaborApplicationSimpleResponse>>> getLaborApplications(@PathVariable Long festivalId,
                                                            @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID) Long holderId,
                                                            @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_TYPE) String userType,
                                                                                                  @RequestParam(required = false) SELECTED status,
                                                                                                  Pageable pageable)
    {
        PageResponse<LaborApplicationSimpleResponse> result = PageResponse.from(applicationQueryService.getLaborApplications(festivalId, holderId, userType, pageable, status)
                .map(LaborApplicationSimpleResponse::from));

        return ResponseEntity.ok(ApiResponse.success("근로자 지원 목록 조회를 성공하였습니다.", result));
    }

    // 내가 지원한 목록 조회하기
    @GetMapping("/my-applications")
    public ResponseEntity<ApiResponse<PageResponse<MyApplicationSimpleResponse>>> getMyApplications(@RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID) Long userId,
                                                                                                    @RequestParam(required = false) SELECTED status,
                                                                                                    @RequestParam(required = false) Progress progress,
                                                                                                    Pageable pageable) {

        PageResponse<MyApplicationSimpleResponse> result = PageResponse.from(applicationQueryService.getMyApplications(userId, pageable, status, progress)
                .map(MyApplicationSimpleResponse::from));

        return ResponseEntity.ok(ApiResponse.success("나의 지원 목록 조회에 성공하였습니다.", result));
    }

    // 수락하기
    @PatchMapping("applications/{applicationId}/accept")
    public ResponseEntity<Void> acceptApplication(@PathVariable Long applicationId)
    {
        applicationCommandService.acceptApplication(applicationId);
        return ResponseEntity.ok().build();
    }

    // 거절하기
    @PatchMapping("applications/{applicationId}/deny")
    public ResponseEntity<Void> denyApplication(@PathVariable Long applicationId)
    {
        applicationCommandService.denyApplication(applicationId);
        return ResponseEntity.ok().build();
    }

    // 지원서 조회하기
    @GetMapping("/applications/{applicationId}")
    public ResponseEntity<ApiResponse<ApplicationDetailResponse>> getApplicationDetail(@PathVariable Long applicationId) {
        ApplicationDetailResponse result = ApplicationDetailResponse.from(applicationQueryService.getApplicationDetail(applicationId));
        return ResponseEntity.ok(ApiResponse.success("지원서 조회에 성공하였습니다.", result));
    }

}
