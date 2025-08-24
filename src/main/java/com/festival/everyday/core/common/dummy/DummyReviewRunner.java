//package com.festival.everyday.core.common.dummy;
//
//import com.festival.everyday.core.review.dto.command.CompanyReviewFormDto;
//import com.festival.everyday.core.review.dto.command.FestivalReviewFormDto;
//import com.festival.everyday.core.review.service.ReviewCommandService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///**
// * 1 ~ 15: 짝수 근로자, 홀수 업체
// * 16 ~ 30: 짝수 업체
// * 31 ~ 45: 홀수 근로자
// * 46 ~ 60: 지원 X
// */
//
//@Order(7)
//@Component
//@RequiredArgsConstructor
//public class DummyReviewRunner implements CommandLineRunner {
//
//    private final ReviewCommandService reviewCommandService;
//
//    private static final Long ALL_PAIR_LABOR_ODD_COMPANY = 15L;
//    private static final Long PAIR_COMPANY = 30L;
//    private static final Long ODD_LABOR = 45L;
//    private static final Long NO_APPLY = 60L;
//
//    private final List<Long> pairCompany = List.of(2L, 4L, 6L, 8L, 10L);
//    private final List<Long> oddCompany = List.of(1L, 3L, 5L, 7L, 9L);
//
//    private final List<Long> pairLabor = List.of(32L, 34L, 36L, 38L, 40L);
//    private final List<Long> oddLabor = List.of(31L, 33L, 35L, 37L, 39L);
//
//    private final List<Long> reviewCompany = List.of(12L, 13L, 14L, 15L, 16L, 17L, 18L);
//    private final List<Long> reviewLabor = List.of(32L, 33L, 34L, 35L, 36L, 37L, 38L);
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        /**
//         * 1 ~ 15
//         * 짝수 근로자, 홀수 업체 -> 축제에 리뷰
//         * 축제 -> 홀수 업체에 리뷰
//         */
//        for (int i = 1; i <= ALL_PAIR_LABOR_ODD_COMPANY; i++) {
//            FestivalReviewFormDto pairLaborReview = new FestivalReviewFormDto("PAIR_LABOR_REVIEW_" + i);
//            FestivalReviewFormDto oddCompanyReview = new FestivalReviewFormDto("ODD_COMPANY_REVIEW" + i);
//
//            final Long index = (long) i;
//
//            // 짝수 근로자 -> 축제 리뷰
//            pairLabor.forEach(id -> reviewCommandService.createFestivalReview(index, id, "LABOR", pairLaborReview));
//            // 홀수 업체 -> 축제 리뷰
//            oddCompany.forEach(id -> reviewCommandService.createFestivalReview(index, id, "COMPANY", oddCompanyReview));
//
//            // 축제 -> 홀수 업체
//            CompanyReviewFormDto companyReviewForm = new CompanyReviewFormDto((long) i, "FESTIVAL_REVIEW_" + i);
//            oddCompany.forEach(id -> reviewCommandService.createCompanyReview(id, companyReviewForm));
//        }
//
//        /**
//         * 16 ~ 30
//         * 짝수 업체 -> 축제 리뷰
//         * 축제 -> 짝수 업체 리뷰
//         */
//        for (int i = 16; i <= PAIR_COMPANY; i++) {
//            FestivalReviewFormDto pairCompanyReview = new FestivalReviewFormDto("PAIR_COMPANY_REVIEW_" + i);
//
//            final Long index = (long) i;
//
//            // 짝수 업체 -> 축제 리뷰
//            pairCompany.forEach(id -> reviewCommandService.createFestivalReview(index, id, "COMPANY", pairCompanyReview));
//
//            // 축제 -> 짝수 업체 리뷰
//            CompanyReviewFormDto companyReviewFormDto = new CompanyReviewFormDto((long) i, "FESTIVAL_REVIEW_" + i);
//            pairCompany.forEach(id -> reviewCommandService.createCompanyReview(id, companyReviewFormDto));
//        }
//
//        /**
//         * 31 ~ 45
//         * 짝수 근로자 -> 축제 리뷰
//         */
//        for (int i = 31; i <= ODD_LABOR; i++) {
//            FestivalReviewFormDto oddLaborReview = new FestivalReviewFormDto("ODD_LABOR_REVIEW_" + i);
//
//            final Long index = (long) i;
//
//            // 홀수 근로자 -> 축제 리뷰
//            oddLabor.forEach(id -> reviewCommandService.createFestivalReview(index, id, "LABOR", oddLaborReview));
//        }
//
//        // 60번 축제에 모두 7명씩 리뷰를 작성합니다.
//        FestivalReviewFormDto cDto = new FestivalReviewFormDto("COMPANY_REVIEW_60");
//        FestivalReviewFormDto lDto = new FestivalReviewFormDto("LABOR_REVIEW_60");
//
//        reviewCompany.forEach(id -> reviewCommandService.createFestivalReview(60L, (long) id, "COMPANY", cDto));
//        reviewLabor.forEach(id -> reviewCommandService.createFestivalReview(60L, (long) id, "LABOR", lDto));
//    }
//}
