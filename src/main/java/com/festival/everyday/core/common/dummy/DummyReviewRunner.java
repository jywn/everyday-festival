package com.festival.everyday.core.common.dummy;

import com.festival.everyday.core.review.dto.command.CompanyReviewFormDto;
import com.festival.everyday.core.review.dto.command.FestivalReviewFormDto;
import com.festival.everyday.core.review.service.ReviewCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 1 ~ 15: 짝수 근로자, 홀수 업체
 * 16 ~ 30: 짝수 업체
 * 31 ~ 45: 홀수 근로자
 * 46 ~ 60: 지원 X
 */

@Order(7)
@Component
@RequiredArgsConstructor
public class DummyReviewRunner implements CommandLineRunner {

    private final ReviewCommandService reviewCommandService;

    @Override
    public void run(String... args) throws Exception {

        /**
         * 업체,근로자 -> 축제에게 리뷰
         */

        FestivalReviewFormDto CR1 = new FestivalReviewFormDto("주최자와 소통이 잘 이루어져 좋았습니다.");
        FestivalReviewFormDto CR2 = new FestivalReviewFormDto("전기를 지원 안해줍니다 ㅡㅡ");
        FestivalReviewFormDto CR3 = new FestivalReviewFormDto("다음에 또 참여하고 싶어요!");
        FestivalReviewFormDto CR4 = new FestivalReviewFormDto("방문자 수가 많아 좋습니다");

        FestivalReviewFormDto LR1 = new FestivalReviewFormDto("주최자와 소통이 잘 이루어져 좋았습니다.");
        FestivalReviewFormDto LR2 = new FestivalReviewFormDto("꿀 알바여서 좋습니다.");
        FestivalReviewFormDto LR3 = new FestivalReviewFormDto("주최자가 무례합니다.");
        FestivalReviewFormDto LR4 = new FestivalReviewFormDto("급여를 많이 주지만 일이 힘듦니다.");

        List<String> FR = List.of("시간을 잘 지킵니다.","친절했습니다.","현장에서 인기가 많았어요");
        //createFestivalReview(Long festivalId, Long userId, String userType, FestivalReviewFormDto formDto);
        reviewCommandService.createFestivalReview(2L, 12L, "COMPANY", CR1);
        reviewCommandService.createFestivalReview(2L, 42L, "COMPANY", CR2);
        CompanyReviewFormDto FR1 = new CompanyReviewFormDto(2L, FR.get(0));
        reviewCommandService.createCompanyReview(12L, FR1);
        CompanyReviewFormDto FR2 = new CompanyReviewFormDto(2L, FR.get(1));
        reviewCommandService.createCompanyReview(42L, FR2);



        reviewCommandService.createFestivalReview(6L, 16L, "COMPANY", CR3);
        reviewCommandService.createFestivalReview(6L, 46L, "COMPANY", CR4);
        CompanyReviewFormDto FR3 = new CompanyReviewFormDto(6L, FR.get(2));
        reviewCommandService.createCompanyReview(16L, FR3);
        CompanyReviewFormDto FR4 = new CompanyReviewFormDto(6L, FR.get(0));
        reviewCommandService.createCompanyReview(46L, FR4);



        reviewCommandService.createFestivalReview(7L, 17L, "COMPANY", CR1);
        reviewCommandService.createFestivalReview(7L, 47L, "COMPANY", CR2);
        reviewCommandService.createFestivalReview(7L, 61L, "LABOR", LR1);
        //reviewCommandService.createFestivalReview(7L, 64L, "LABOR", LR2);
        //reviewCommandService.createFestivalReview(7L, 67L, "LABOR", LR3);
        reviewCommandService.createFestivalReview(7L, 70L, "LABOR", LR4);
        reviewCommandService.createFestivalReview(7L, 73L, "LABOR", LR1);
        reviewCommandService.createFestivalReview(7L, 76L, "LABOR", LR2);
        reviewCommandService.createFestivalReview(7L, 82L, "LABOR", LR3);
        reviewCommandService.createFestivalReview(7L, 85L, "LABOR", LR4);
        reviewCommandService.createFestivalReview(7L, 88L, "LABOR", LR1);
        CompanyReviewFormDto FR5 = new CompanyReviewFormDto(7L, FR.get(1));
        reviewCommandService.createCompanyReview(17L, FR5);
        CompanyReviewFormDto FR6 = new CompanyReviewFormDto(7L, FR.get(2));
        reviewCommandService.createCompanyReview(47L, FR6);


        reviewCommandService.createFestivalReview(8L, 18L, "COMPANY", CR1);
        reviewCommandService.createFestivalReview(8L, 48L, "COMPANY", CR2);
        CompanyReviewFormDto FR7 = new CompanyReviewFormDto(7L, FR.get(0));
        reviewCommandService.createCompanyReview(18L, FR7);
        CompanyReviewFormDto FR8 = new CompanyReviewFormDto(7L, FR.get(1));
        reviewCommandService.createCompanyReview(48L, FR8);


        reviewCommandService.createFestivalReview(10L, 20L, "COMPANY", CR3);
        reviewCommandService.createFestivalReview(10L, 50L, "COMPANY", CR4);
        reviewCommandService.createFestivalReview(10L, 63L, "LABOR", LR1);
        reviewCommandService.createFestivalReview(10L, 66L, "LABOR", LR1);
        reviewCommandService.createFestivalReview(10L, 69L, "LABOR", LR1);
        reviewCommandService.createFestivalReview(10L, 72L, "LABOR", LR1);
        reviewCommandService.createFestivalReview(10L, 75L, "LABOR", LR1);
        reviewCommandService.createFestivalReview(10L, 78L, "LABOR", LR1);
        reviewCommandService.createFestivalReview(10L, 84L, "LABOR", LR1);
        reviewCommandService.createFestivalReview(10L, 87L, "LABOR", LR1);
        reviewCommandService.createFestivalReview(10L, 90L, "LABOR", LR1);
        CompanyReviewFormDto FR9 = new CompanyReviewFormDto(10L, FR.get(2));
        reviewCommandService.createCompanyReview(20L, FR9);
        CompanyReviewFormDto FR10 = new CompanyReviewFormDto(10L, FR.get(0));
        reviewCommandService.createCompanyReview(50L, FR10);



        reviewCommandService.createFestivalReview(12L, 52L, "COMPANY", CR1);
        CompanyReviewFormDto FR11 = new CompanyReviewFormDto(12L, FR.get(1));
        reviewCommandService.createCompanyReview(52L, FR11);

        reviewCommandService.createFestivalReview(15L, 25L, "COMPANY", CR2);
        reviewCommandService.createFestivalReview(15L, 55L, "COMPANY", CR3);
        CompanyReviewFormDto FR12 = new CompanyReviewFormDto(15L, FR.get(2));
        reviewCommandService.createCompanyReview(25L, FR12);
        CompanyReviewFormDto FR13 = new CompanyReviewFormDto(15L, FR.get(0));
        reviewCommandService.createCompanyReview(55L, FR13);


        reviewCommandService.createFestivalReview(17L, 27L, "COMPANY", CR3);
        reviewCommandService.createFestivalReview(17L, 57L, "COMPANY", CR4);
        CompanyReviewFormDto FR14 = new CompanyReviewFormDto(17L, FR.get(1));
        reviewCommandService.createCompanyReview(27L, FR14);
        CompanyReviewFormDto FR15 = new CompanyReviewFormDto(17L, FR.get(2));
        reviewCommandService.createCompanyReview(57L, FR15);

        reviewCommandService.createFestivalReview(18L, 28L, "COMPANY", CR2);
        CompanyReviewFormDto FR16 = new CompanyReviewFormDto(18L, FR.get(0));
        reviewCommandService.createCompanyReview(28L, FR16);

        reviewCommandService.createFestivalReview(20L, 30L, "COMPANY", CR4);
        reviewCommandService.createFestivalReview(20L, 60L, "COMPANY", CR1);
        CompanyReviewFormDto FR17 = new CompanyReviewFormDto(20L, FR.get(1));
        reviewCommandService.createCompanyReview(30L, FR17);
        CompanyReviewFormDto FR18 = new CompanyReviewFormDto(20L, FR.get(2));
        reviewCommandService.createCompanyReview(60L, FR18);


        reviewCommandService.createFestivalReview(24L, 4L, "COMPANY", CR2);
        reviewCommandService.createFestivalReview(24L, 34L, "COMPANY", CR3);
        CompanyReviewFormDto FR19 = new CompanyReviewFormDto(24L, FR.get(0));
        reviewCommandService.createCompanyReview(4L, FR19);
        CompanyReviewFormDto FR20 = new CompanyReviewFormDto(24L, FR.get(1));
        reviewCommandService.createCompanyReview(34L, FR20);


        reviewCommandService.createFestivalReview(26L, 6L, "COMPANY", CR4);
        CompanyReviewFormDto FR21 = new CompanyReviewFormDto(26L, FR.get(2));
        reviewCommandService.createCompanyReview(6L, FR21);

        reviewCommandService.createFestivalReview(27L, 37L, "COMPANY", CR1);
        CompanyReviewFormDto FR22 = new CompanyReviewFormDto(27L, FR.get(0));
        reviewCommandService.createCompanyReview(37L, FR22);

        reviewCommandService.createFestivalReview(28L, 8L, "COMPANY", CR1);
        reviewCommandService.createFestivalReview(28L, 38L, "COMPANY", CR4);
        reviewCommandService.createFestivalReview(28L, 38L, "LABOR", LR1);
        reviewCommandService.createFestivalReview(28L, 63L, "LABOR", LR2);
        reviewCommandService.createFestivalReview(28L, 66L, "LABOR", LR3);
        reviewCommandService.createFestivalReview(28L, 69L, "LABOR", LR4);
        reviewCommandService.createFestivalReview(28L, 75L, "LABOR", LR1);
        reviewCommandService.createFestivalReview(28L, 78L, "LABOR", LR2);
        reviewCommandService.createFestivalReview(28L, 81L, "LABOR", LR3);
        reviewCommandService.createFestivalReview(28L, 84L, "LABOR", LR4);
        reviewCommandService.createFestivalReview(28L, 87L, "LABOR", LR1);
        reviewCommandService.createFestivalReview(28L, 90L, "LABOR", LR2);
        CompanyReviewFormDto FR23 = new CompanyReviewFormDto(28L, FR.get(1));
        reviewCommandService.createCompanyReview(8L, FR22);
        CompanyReviewFormDto FR24 = new CompanyReviewFormDto(28L, FR.get(2));
        reviewCommandService.createCompanyReview(38L, FR22);
    }
}
