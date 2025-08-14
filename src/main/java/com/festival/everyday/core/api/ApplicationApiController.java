package com.festival.everyday.core.api;

import com.festival.everyday.core.domain.application.Application;
import com.festival.everyday.core.dto.request.ApplicationRequest;
import com.festival.everyday.core.dto.response.ApiResponse;
import com.festival.everyday.core.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequiredArgsConstructor
public class ApplicationApiController {

    private final ApplicationService applicationService;

    @PostMapping("/festivals/{festivalId}/company-applications")
    public ResponseEntity<ApiResponse<Long>> createCompanyApplication(@PathVariable Long festivalId, @RequestBody ApplicationRequest request) {
        Long userId = 1L; // 수정 필요
        Long applicationId = applicationService.createCompanyApplication(userId, festivalId, request);
        return ResponseEntity.ok(ApiResponse.success("지원서 제출에 성공하였습니다.", applicationId));
    }

    @PostMapping("/festivals/{festivalId}/labor-applications")
    public ResponseEntity<ApiResponse<Long>> createLaborApplication(@PathVariable Long festivalId, @RequestBody ApplicationRequest request) {
        Long userId = 1L; // 수정 필요
        Long applicationId = applicationService.createLaborApplication(userId, festivalId, request);
        return ResponseEntity.ok(ApiResponse.success("지원서 제출에 성공하였습니다.", applicationId));
    }
}
