package com.festival.everyday.core.interest.controller;

import com.festival.everyday.core.common.dto.response.ApiResponse;
import com.festival.everyday.core.interest.service.InterestCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class InterestController {
    private final InterestCommandService interestCommandService;

    @PostMapping("/companies/{companyId}/interests")
    public ResponseEntity<ApiResponse<Long>> createInterest(@PathVariable Long companyId, @RequestBody Long festivalId)
    {
        // 관심을 보냅니다.
        Long result = interestCommandService.createInterest(festivalId, companyId);
        return ResponseEntity.ok(ApiResponse.success("관심을 보내기를 성공하였습니다.", result));
    }
}
