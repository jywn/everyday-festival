package com.festival.everyday.core.common.dummy;

import com.festival.everyday.core.application.dto.request.ApplicationRequest;
import com.festival.everyday.core.application.service.ApplicationCommandService;
import com.festival.everyday.core.recruit.dto.request.CreateCompanyRecruitRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 1 ~ 15: 모두 지원, 짝수 근로자, 홀수 업체
 * 16 ~ 30: 업체만 지원, 짝수 업체
 * 31 ~ 45: 근로자만 지원, 홀수 근로자
 * 46 ~ 60: 지원 X
 */

@Order(6)
@Component
@RequiredArgsConstructor
public class DummyApplyRunner implements CommandLineRunner {

    private final ApplicationCommandService applicationCommandService;

    private static final Long ALL_PAIR_LABOR_ODD_COMPANY = 15L;
    private static final Long PAIR_COMPANY = 30L;
    private static final Long ODD_LABOR = 45L;
    private static final Long NO_APPLY = 60L;

    private List<String> companyAnswersOdd = List.of("홀수 업체의 답변 1", "홀수 업체의 답변 2", "홀수 업체의 답변 3");
    private List<String> companyEAnswersOdd = List.of("홀수 업체의 추가 답변 1", "홀수 업체의 추가 답변 2", "홀수 업체의 추가 답변 3");
    private List<String> companyAnswersPair = List.of("짝수 업체의 답변 1", "짝수 업체의 답변 2", "짝수 업체의 답변 3");
    private List<String> companyEAnswersPair = List.of("짝수 업체의 추가 답변 1", "짝수 업체의 추가 답변 2", "짝수 업체의 추가 답변 3");

    private List<String> laborAnswersOdd = List.of("홀수 근로자의 답변 1", "홀수 근로자의 답변 2", "홀수 근로자의 답변 3");
    private List<String> laborEAnswersOdd = List.of("홀수 근로자의 추가 답변 1", "홀수 근로자의 추가 답변 2", "홀수 근로자의 추가 답변 3");
    private List<String> laborAnswersPair = List.of("짝수 근로자의 답변 1", "짝수 근로자의 답변 2", "짝수 근로자의 답변 3");
    private List<String> laborEAnswersPair = List.of("짝수 근로자의 추가 답변 1", "짝수 근로자의 추가 답변 2", "짝수 근로자의 추가 답변 3");

    private final List<Long> pairCompany = List.of(2L, 4L, 6L, 8L, 10L);
    private final List<Long> oddCompany = List.of(1L, 3L, 5L, 7L, 9L);

    private final List<Long> pairLabor = List.of(32L, 34L, 36L, 38L, 40L);
    private final List<Long> oddLabor = List.of(31L, 33L, 35L, 37L, 39L);

    @Override
    public void run(String... args) throws Exception {

        // 1~ 15
        for (int i = 1; i <= ALL_PAIR_LABOR_ODD_COMPANY; i++) {
            // 홀수 업체의 지원 답변
            ApplicationRequest companyRequest = new ApplicationRequest(companyAnswersOdd, companyEAnswersOdd);
            // 짝수 근로자의 지원 답변
            ApplicationRequest laborRequest = new ApplicationRequest(laborAnswersPair, laborEAnswersPair);

            final Long index = (long) i;

            oddCompany.forEach(id -> applicationCommandService.createCompanyApplication(id, index, companyRequest));
            pairLabor.forEach(id -> applicationCommandService.createLaborApplication(id, index, laborRequest));
        }

        // 16 ~ 30
        for (int i = 16; i <= PAIR_COMPANY; i++) {
            // 짝수 업체
            ApplicationRequest companyRequest = new ApplicationRequest(companyAnswersPair, companyEAnswersPair);

            final Long index = (long) i;

            pairCompany.forEach(id -> applicationCommandService.createCompanyApplication(id, index, companyRequest));
        }

        // 31 ~ 45
        for (int i = 31; i <= ODD_LABOR; i++) {
            // 홀수 근로자
            ApplicationRequest laborRequest = new ApplicationRequest(laborAnswersOdd, laborEAnswersOdd);

            final Long index = (long) i;

            oddLabor.forEach(id -> applicationCommandService.createLaborApplication(id, index, laborRequest));
        }

        // 60번 축제는 종료된 행사에 다수의 지원자가 존재한다.
        ApplicationRequest companyRequest = new ApplicationRequest(companyAnswersPair, companyEAnswersPair);

        pairCompany.forEach(id -> applicationCommandService.createCompanyApplication(id, 60L, companyRequest));
        pairLabor.forEach(id -> applicationCommandService.createLaborApplication(id, 60L, companyRequest));
    }
}
