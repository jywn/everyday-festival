package com.festival.everyday.core.controller;

import com.festival.everyday.core.dto.request.InterestRequest;
import com.festival.everyday.core.dto.response.ApiResponse;
import com.festival.everyday.core.dto.response.InterestResponse;
import com.festival.everyday.core.security.UserDetailsImpl;
import com.festival.everyday.core.service.InterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InterestController {
    private final InterestService interestService;

    @PostMapping("/companies/{companyId}/interests")
    public ResponseEntity <ApiResponse> sendInterest(@PathVariable Long companyId, @RequestBody InterestRequest request, @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        Long holderId=userDetails.getUser().getId();
        InterestResponse response=interestService.createInterest(holderId,companyId,request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse(true, 200, "관심 보내기에 성공했습니다.",response));
    }
}
