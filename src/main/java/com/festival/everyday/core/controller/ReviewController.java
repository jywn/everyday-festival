package com.festival.everyday.core.controller;

import com.festival.everyday.core.config.jwt.TokenAuthenticationFilter;
import com.festival.everyday.core.domain.interaction.Review;
import com.festival.everyday.core.dto.request.CompanyReviewRequest;
import com.festival.everyday.core.dto.request.FestivalReviewRequest;
import com.festival.everyday.core.dto.response.*;
import com.festival.everyday.core.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.access.AccessDeniedException;

import java.net.URI;


@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("festivals/{festivalId}/reviews")
    public ResponseEntity<ApiResponse> createFestivalReview(@PathVariable Long festivalId, @RequestBody FestivalReviewRequest request, @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID) Long userId,
                                                            @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_TYPE) String userType) {
        Review savedReview=reviewService.createFestivalReview(festivalId, userId, userType, request);

        ReviewResponse responseBody=ReviewResponse.of(savedReview);

        String redirectURL="/";
        if ("COMPANY".equals(userType))
        {
            redirectURL = "/company-applications"; //업체라면 자신이 지원한 축제들 목록으로

        }
        else if ("LABOR".equals(userType))
        {
            redirectURL = "/labor-applications"; //근로자라면 자신이 지원한 축제 목록으로
        }

        return ResponseEntity
                .created(URI.create(redirectURL))
                .body(new ApiResponse(true, 200, "축제에게 리뷰를 등록하였습니다!", responseBody));
    }

    @PostMapping("/companies/{companyId}/reviews")
    public ResponseEntity<ApiResponse> createCompanyReview(@PathVariable Long companyId, @RequestBody CompanyReviewRequest request, @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID) Long holderId,
                                                           @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_TYPE) String userType) {
        if (!"HOLDER".equals(userType)) {
            throw new AccessDeniedException("리뷰 작성 권한이 없습니다.");
        }

        Review savedReview=reviewService.createCompanyReview(companyId, holderId, request);

        ReviewResponse responseBody=ReviewResponse.of(savedReview);

        String redirectURL="/festivals/{festivalId}/company-applications"; //자신에게 지원한 현황 페이지로 (디폴트는 company로)

        return ResponseEntity
                .created(URI.create(redirectURL))
                .body(new ApiResponse(true, 200, "업체에게 리뷰를 등록하였습니다!", responseBody));
    }

    @GetMapping("/festivals/{festivalId}/reviews")
    public ResponseEntity<ApiResponse> getFestivalReviews(@PathVariable Long festivalId) {
        FestivalReviewListResponse response=reviewService.getFestivalReviews(festivalId);

        return ResponseEntity
                .ok(new ApiResponse(true, 200, "리뷰 목록 조회에 성공했습니다.", response));
    }

    @GetMapping("/companies/{companyId}/reviews")
    public ResponseEntity<ApiResponse> getCompanyReviews(@PathVariable Long companyId) {
        CompanyReviewListResponse response=reviewService.getCompanyReviews(companyId);

        return ResponseEntity
                .ok(new ApiResponse(true, 200, "리뷰 목록 조회에 성공했습니다.", response));
    }

    @GetMapping("/festivals/{festivalId}/reviews/form")
    public ResponseEntity<ApiResponse> getFestivalReviewForm(@PathVariable Long festivalId) {

        FestivalReviewFormResponse response=reviewService.getFestivalReviewForm(festivalId);

        return ResponseEntity
                .ok(new ApiResponse(true,200,"축제 리뷰 작성 폼 조회에 성공하였습니다."));
    }

    @GetMapping("/festivals/{festivalId}/reviews/form")
    public ResponseEntity<ApiResponse> getCompanyReviewForm(@PathVariable Long companyId) {

        CompanyReviewFormResponse response=reviewService.getCompanyReviewForm(companyId);

        return ResponseEntity
                .ok(new ApiResponse(true,200,"업체 리뷰 작성 폼 조회에 성공하였습니다."));
    }
}
