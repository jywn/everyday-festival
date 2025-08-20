package com.festival.everyday.core.review.controller;

import com.festival.everyday.core.common.config.jwt.TokenAuthenticationFilter;
import com.festival.everyday.core.common.dto.response.ApiResponse;
import com.festival.everyday.core.review.dto.command.CompanyReviewFormDto;
import com.festival.everyday.core.review.dto.command.FestivalReviewFormDto;
import com.festival.everyday.core.review.dto.request.CompanyReviewRequest;
import com.festival.everyday.core.review.dto.request.FestivalReviewRequest;
import com.festival.everyday.core.review.dto.response.*;
import com.festival.everyday.core.review.service.ReviewCommandService;
import com.festival.everyday.core.review.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    @PostMapping("festivals/{festivalId}/reviews")
    public ResponseEntity<ApiResponse<Long>> createFestivalReview(@PathVariable Long festivalId, @RequestBody FestivalReviewRequest request,
                                                            @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID) Long userId,
                                                            @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_TYPE) String userType) {

        Long id = reviewCommandService.createFestivalReview(festivalId, userId, userType, FestivalReviewFormDto.from(request));

        return ResponseEntity.ok(ApiResponse.success("축제에 리뷰를 작성하였습니다.", id));
    }

    @PostMapping("/companies/{companyId}/reviews")
    public ResponseEntity<ApiResponse<Long>> createCompanyReview(@PathVariable Long companyId, @RequestBody CompanyReviewRequest request,
                                                           @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID) Long holderId,
                                                           @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_TYPE) String userType) {

        Long id = reviewCommandService.createCompanyReview(companyId, CompanyReviewFormDto.from(request));

        return ResponseEntity.ok(ApiResponse.success("업체에 리뷰를 작성하였습니다.", id));
    }

    @GetMapping("/festivals/{festivalId}/reviews")
    public ResponseEntity<ApiResponse<List<ReviewAndSenderResponse>>> getFestivalReviews(@PathVariable Long festivalId,
                                                                                   @RequestParam SenderNotFestival senderType) {
        // 리뷰 작성자에 따라 구분한다.
        List<ReviewAndSenderResponse> result = switch (senderType) {
            case COMPANY -> reviewQueryService.getFestivalReviewsByCompanies(festivalId);
            case LABOR -> reviewQueryService.getFestivalReviewsByLabors(festivalId);
        };

        return ResponseEntity.ok(ApiResponse.success("축제 리뷰 목록 조회에 성공했습니다.", result));
    }

    @GetMapping("/companies/{companyId}/reviews")
    public ResponseEntity<ApiResponse<List<ReviewAndSenderResponse>>> getCompanyReviews(@PathVariable Long companyId) {

        List<ReviewAndSenderResponse> result = reviewQueryService.getCompanyReviews(companyId);

        return ResponseEntity.ok(ApiResponse.success("업체 리뷰 목록 조회에 성공했습니다.", result));
    }

    @GetMapping("/festivals/{festivalId}/reviews/form")
    public ResponseEntity<ApiResponse<FestivalReviewFormResponse>> getFestivalReviewForm(@PathVariable Long festivalId) {

        FestivalReviewFormResponse response = reviewQueryService.getFestivalReviewForm(festivalId);

        return ResponseEntity.ok(ApiResponse.success("축제 리뷰 작성 폼 조회에 성공하였습니다.", response));
    }

    @GetMapping("/companies/{companyId}/reviews/form")
    public ResponseEntity<ApiResponse<CompanyReviewFormResponse>> getCompanyReviewForm(@PathVariable Long companyId) {

        CompanyReviewFormResponse response = reviewQueryService.getCompanyReviewForm(companyId);

        return ResponseEntity.ok(ApiResponse.success("업체 리뷰 작성 폼 조회에 성공하였습니다.", response));
    }

    public enum SenderNotFestival {
        COMPANY, LABOR
    }
}
