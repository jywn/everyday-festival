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
 * 1 ~ 15: 모든 모집공고가 존재한다.
 * 16 ~ 30: 업체 모집 공고만 존재한다.
 * 31 ~ 45: 근로자 모집 공고만 존재한다.
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

        // 축제 1 ~ 15 모집 공고 등록
        for (int i = 1; i <= ALL_RECRUIT; i++) {
            CreateCompanyRecruitRequest companyRequest = new CreateCompanyRecruitRequest(
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    i % 2 == 0 ? categoriesPair : categoriesOdd,
                    null,
                    "CR_preferred_" + i,
                    "CR_notice_" + i,
                    i % 2 == 0 ? strExQuestionsPair : strExQuestionsOdd
            );
            recruitCommandService.saveCompanyRecruit(companyRequest, (long) i);

            CreateLaborRecruitRequest laborRequest = new CreateLaborRecruitRequest(
                    LocalDateTime.now(),
                    LocalDateTime.now(),
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
                    LocalDateTime.now(),
                    LocalDateTime.now(),
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
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    "LR_job_" + i,
                    "LR_wage_" + i,
                    "LR_remark_" + i,
                    "LR_notice_" + i,
                    i % 2 == 0 ? strExQuestionsPair : strExQuestionsOdd
            );
            recruitCommandService.saveLaborRecruit(laborRequest, (long) i);
        }
    }
}
