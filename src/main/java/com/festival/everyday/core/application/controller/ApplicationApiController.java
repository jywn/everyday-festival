package com.festival.everyday.core.application.controller;

import com.festival.everyday.core.application.dto.request.ApplicationRequest;
import com.festival.everyday.core.application.service.ApplicationCommandService;
import com.festival.everyday.core.common.config.jwt.TokenAuthenticationFilter;
import com.festival.everyday.core.common.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@Transactional
@RequiredArgsConstructor
public class ApplicationApiController {

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
}
