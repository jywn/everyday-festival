package com.festival.everyday.core.controller;

import com.festival.everyday.core.config.jwt.TokenAuthenticationFilter;
import com.festival.everyday.core.domain.interaction.Interest;
import com.festival.everyday.core.dto.request.InterestRequest;
import com.festival.everyday.core.dto.response.ApiResponse;
import com.festival.everyday.core.dto.response.InterestResponse;
import com.festival.everyday.core.service.InterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import org.springframework.security.access.AccessDeniedException;

@RestController
@RequiredArgsConstructor
public class InterestController {
    private final InterestService interestService;

    @PostMapping("/companies/{companyId}/interests")
    public ResponseEntity <ApiResponse> createInterest(@PathVariable Long companyId, @RequestBody InterestRequest request, @RequestAttribute(name= TokenAuthenticationFilter.ATTR_USER_ID) Long holderId, @RequestAttribute(name=TokenAuthenticationFilter.ATTR_USER_TYPE) String userType)
    {
        if (!"HOLDER".equals(userType))
        {
            throw new AccessDeniedException("관심 보내기 권한이 없습니다.");
        }

        Interest savedInterest=interestService.createInterest(holderId,companyId,request);

        InterestResponse response=InterestResponse.of(savedInterest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body((new ApiResponse(true,201,"관심 보내기에 성공했습니다.",response)));
    }
}
