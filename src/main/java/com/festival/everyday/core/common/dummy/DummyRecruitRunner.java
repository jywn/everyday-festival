package com.festival.everyday.core.common.dummy;

import com.festival.everyday.core.recruit.domain.ExtraQuestion;
import com.festival.everyday.core.recruit.dto.request.CreateCompanyRecruitRequest;
import com.festival.everyday.core.recruit.dto.request.CreateLaborRecruitRequest;
import com.festival.everyday.core.recruit.service.RecruitCommandService;
import com.festival.everyday.core.user.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 1 ~ 15: 모든 모집공고가 존재한다. (짝수: 모집중, 홀수: 모집 완료)
 * 16 ~ 30: 업체 모집 공고만 존재한다. (짝수: 모집중, 홀수: 모집 완료)
 * 31 ~ 45: 근로자 모집 공고만 존재한다. (짝수: 모집중, 홀수: 모집 완료)
 * 46 ~ 60: 모집 공고가 존재하지 않는다.
 */

@Order(5)
@Component
@RequiredArgsConstructor
public class DummyRecruitRunner implements CommandLineRunner {

    private final RecruitCommandService recruitCommandService;
    private static final Long ALL_RECRUIT = 15L;
    private static final Long COMPANY_RECRUIT = 30L;
    private static final Long LABOR_RECRUIT = 45L;
    private static final Long NO_RECRUIT = 60L;
    private List<Category> categoriesPair = List.of(Category.ART, Category.SALE);
    private List<Category> categoriesOdd = List.of(Category.FOOD, Category.ENTERTAINMENT);
    private List<String> strExQuestionsOdd = List.of("홀수 추가 질문 1", "홀수 추가 질문 2", "홀수 추가 질문 3");
    private List<String> strExQuestionsPair = List.of("짝수 추가 질문 1", "짝수 추가 질문 2", "짝수 추가 질문 3");

    /**
     *     private LocalDateTime begin;
     *     private LocalDateTime end;
     *     private List<Category> categories;
     *     private String etc;
     *     private String preferred;
     *     private String notice;
     *     private List<String> extraQuestions;
     */

    @Override
    public void run(String... args) throws Exception {

        LocalDateTime beginPast = LocalDateTime.of(2025, 7, 10, 14, 30);
        LocalDateTime endPast = LocalDateTime.of(2025, 7, 15, 15, 30);

        LocalDateTime beginFuture = LocalDateTime.of(2025, 10, 1, 14, 30);
        LocalDateTime endFuture = LocalDateTime.of(2025, 10, 5, 15, 30);

        // 축제 1 ~ 15 모집 공고 등록
        for (int i = 1; i <= ALL_RECRUIT; i++) {
            CreateCompanyRecruitRequest companyRequest = new CreateCompanyRecruitRequest(
                    i % 2 == 0 ? beginFuture : beginPast,
                    i % 2 == 0 ? endFuture : endPast,
                    i % 2 == 0 ? categoriesPair : categoriesOdd,
                    null,
                    "CR_preferred_" + i,
                    "CR_notice_" + i,
                    i % 2 == 0 ? strExQuestionsPair : strExQuestionsOdd
            );
            recruitCommandService.saveCompanyRecruit(companyRequest, (long) i);

            CreateLaborRecruitRequest laborRequest = new CreateLaborRecruitRequest(
                    i % 2 == 0 ? beginFuture : beginPast,
                    i % 2 == 0 ? endFuture : endPast,
                    "LR_job_" + i,
                    "LR_wage_" + i,
                    "LR_remark_" + i,
                    "LR_notice_" + i,
                    i % 2 == 0 ? strExQuestionsPair : strExQuestionsOdd
            );

            recruitCommandService.saveLaborRecruit(laborRequest, (long) i);
        }

        // 16 ~ 30 업체 모집 공고만 등록
        for (int i = 16; i <= COMPANY_RECRUIT; i++) {
            CreateCompanyRecruitRequest companyRequest = new CreateCompanyRecruitRequest(
                    i % 2 == 0 ? beginFuture : beginPast,
                    i % 2 == 0 ? endFuture : endPast,
                    i % 2 == 0 ? categoriesPair : categoriesOdd,
                    null,
                    "CR_preferred_" + i,
                    "CR_notice_" + i,
                    i % 2 == 0 ? strExQuestionsPair : strExQuestionsOdd
            );
            recruitCommandService.saveCompanyRecruit(companyRequest, (long) i);
        }

        // 31 ~ 45 근로자 모집 공고만 등록
        for (int i = 31; i <= LABOR_RECRUIT; i++) {
            CreateLaborRecruitRequest laborRequest = new CreateLaborRecruitRequest(
                    i % 2 == 0 ? beginFuture : beginPast,
                    i % 2 == 0 ? endFuture : endPast,
                    "LR_job_" + i,
                    "LR_wage_" + i,
                    "LR_remark_" + i,
                    "LR_notice_" + i,
                    i % 2 == 0 ? strExQuestionsPair : strExQuestionsOdd
            );
            recruitCommandService.saveLaborRecruit(laborRequest, (long) i);
        }

        // 60은 공고를 모두 등록한다.
        CreateCompanyRecruitRequest companyRequest = new CreateCompanyRecruitRequest(
                beginPast,
                endPast,
                categoriesPair,
                null,
                "CR_preferred_" + "60",
                "CR_notice_" + "60",
                strExQuestionsPair
        );
        recruitCommandService.saveCompanyRecruit(companyRequest, (long) 60);

        CreateLaborRecruitRequest laborRequest = new CreateLaborRecruitRequest(
                beginPast,
                endPast,
                "LR_job_" + "60",
                "LR_wage_" + "60",
                "LR_remark_" + "60",
                "LR_notice_" + "60",
                strExQuestionsPair
        );

        recruitCommandService.saveLaborRecruit(laborRequest, (long) 60);
    }
}
