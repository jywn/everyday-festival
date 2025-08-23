package com.festival.everyday.core.common.dummy;

import com.festival.everyday.core.application.dto.request.ApplicationRequest;
import com.festival.everyday.core.application.service.ApplicationCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Order(6)
@Component
@RequiredArgsConstructor
public class DummyApplyRunner implements CommandLineRunner {

    private final ApplicationCommandService applicationCommandService;

    // 회사/근로자 공통 더미 답변
    private static final List<String> COMPANY_ANS_1 = List.of("푸드", "음식 업체", "010-6515-7415", "언제든지 가능합니다.");
    private static final List<String> COMPANY_ANS_2 = List.of("공연/예술", "공연/예술 업체", "010-3253-6115", "언제든지 가능합니다.");
    private static final List<String> COMPANY_ANS_3 = List.of("오락", "오락 업체", "010-7518-9954", "언제든지 가능합니다.");
    private static final List<String> COMPANY_ANS_4 = List.of("체험", "체험 업체", "010-0815-1239", "언제든지 가능합니다.");
    private static final List<String> COMPANY_ANS_5 = List.of("판매", "판매 업체", "010-9891-1289", "언제든지 가능합니다.");
    private static final List<String> COMPANY_ANS_6 = List.of("홍보(캠페인)", "푸드트럭 업체", "010-6515-7415", "언제든지 가능합니다.");
    private static final List<String> COMPANY_EANS_ODD =
            List.of(
                    "운영 인원은 총 3명이며, 행사 당일에는 2명이 교대로 부스를 관리합니다. 축제 시작일부터 마감일까지 전부 가능합니다.",
                    "부스 운영에 필요한 전력은 1500W이며, 별도의 수도 시설은 필요하지 않습니다.",
                    "메인 음식의 경우 6000원, 사이드 음식의 경우 3000원으로 통일합니다."
            );;

    private static final List<String> COMPANY_EANS_EVEN =
            List.of(
                    "운영 인원은 총 5명이며, 평일을 제외하고 주말 운영 가능합니다.",
                    "운영에 필요한 테이블 2개와 의자 4개, 그리고 간판을 자체적으로 준비했습니다. 필요한 경우 비상용 소형 발전기를 가져와도 되는지 확인 부탁드립니다.",
                    "식사의 경우 7000원, 디저트와 음료의 경우 2000원입니다."
            );
    ;
    private static final List<String> LABOR_ANS = List.of("근로자 답변 1", "근로자 답변 2", "근로자 답변 3");
    private static final List<String> LABOR_EANS_1 = List.of(
            "지난 2년간 여러 행사에서 총 5회 이상 단기 근로자로 활동했습니다. 특히, 작년 부산국제영화제에서는 안내 요원으로 근무하며 인파 통제와 동선 안내를 담당했습니다. 여러 돌발 상황에 유연하게 대처한 경험이 있습니다.",
            "진상 응대가 필요한 상황에서는 감정적으로 대응하기보다, 우선 고객의 불만 사항을 명확히 파악하는 것을 최우선으로 생각합니다. 예를 들어, '무엇이 가장 불편하셨나요?'라고 질문하며 문제의 핵심을 파악하고, 제 선에서 해결 가능한 부분은 즉시 조치하고, 규정을 벗어나는 요구에는 '규정상 어렵지만 다른 방법을 찾아보겠습니다'와 같이 대안을 제시하며 정중하게 거절하는 방법을 선호합니다."
    );

    private static final List<String> LABOR_EANS_2 = List.of(
            "지난해 지역 축제에서 판매 부스 보조 업무를 3일간 수행했습니다. 주로 손님 응대와 재고 관리, 간단한 판매 업무를 맡았습니다. 축제 분위기를 즐기며 일하는 것에 익숙합니다.",
            "진상 손님 응대 경험은 많지 않지만, 평소 아르바이트를 하며 불만 고객을 마주했을 때는 먼저 경청하고, 죄송하다는 말씀과 함께 해결 방안을 찾아보려고 노력했습니다. 제 권한 밖의 문제는 즉시 관리자에게 보고하여 도움을 요청하겠습니다."
    );

    private static final List<String> LABOR_EANS_3 = List.of(
            "축제 관련 근로 경험은 없지만, 레스토랑에서 1년간 홀 서빙 아르바이트를 하며 다양한 손님을 응대해 본 경험이 있습니다. 빠르고 정확한 업무 처리와 친절한 서비스에 대한 자신감이 있습니다.",
            "불만 고객을 마주했을 때는 먼저 저의 긍정적인 에너지를 전달하려고 노력합니다. '제가 도울 방법이 있을까요?'와 같이 정중하게 질문하고, 만약 문제가 해결되지 않을 경우 즉시 담당자에게 상황을 공유하고 함께 해결책을 찾겠습니다."
    );

    // 근로자 공고가 등록된 축제: 1,4,7,10,13,16,19,22,25,28
    private static final int[] LABOR_FESTIVALS = {1,4,7,10,13,16,19,22,25,28};

    private final List<Long> reviewCompany = List.of(12L, 13L, 14L, 15L, 16L, 17L, 18L);
    private final List<Long> reviewLabor = List.of(32L, 33L, 34L, 35L, 36L, 37L, 38L);


    @Override
    public void run(String... args) throws Exception {
        createCompanyApplications();
        createLaborApplications();
    }

    private List<String> selectCompanyAns(int companyId) {
        if (companyId >= 1 && companyId <= 30) return COMPANY_ANS_1;
        if (companyId >= 31 && companyId <= 40) return COMPANY_ANS_2;
        if (companyId >= 41 && companyId <= 45) return COMPANY_ANS_3;
        if (companyId >= 46 && companyId <= 50) return COMPANY_ANS_4;
        if (companyId >= 51 && companyId <= 55) return COMPANY_ANS_5;
        return COMPANY_ANS_6; // 56~60
    }

    private void createCompanyApplications() {
        for (int companyId = 1; companyId <= 60; companyId++) {
            boolean isEven = (companyId % 2 == 0);

            // companyId 구간별로 COMPANY_ANS 선택
            List<String> ans = selectCompanyAns(companyId);
            List<String> eAnswers = isEven ? COMPANY_EANS_EVEN : COMPANY_EANS_ODD;

            ApplicationRequest req = new ApplicationRequest(ans, eAnswers);

            int base = companyId % 10;
            if (base == 0) base = 10;

            for (int offset = 0; offset <= 20; offset += 10) {
                int fest = base + offset;
                if (fest <= 29) {
                    applicationCommandService.createCompanyApplication(
                            (long) companyId, (long) fest, req
                    );
                }
            }
        }
    }


    /** 근로자(61..90) → 1,4,7,10,13,16,19,22,25,28 전부 지원 */
    private List<String> selectLaborEans(int laborId) {
        int r = laborId % 3;
        if (r == 1) return LABOR_EANS_1;
        if (r == 2) return LABOR_EANS_2;
        return LABOR_EANS_3;
    }

    private void createLaborApplications() {
        for (int laborId = 61; laborId <= 90; laborId++) {
            List<String> eans = selectLaborEans(laborId);
            ApplicationRequest req = new ApplicationRequest(LABOR_ANS, eans);
            for (int fest : LABOR_FESTIVALS) {                         // 1,4,7,10,13,16,19,22,25,28
                applicationCommandService.createLaborApplication(
                        (long) laborId, (long) fest, req
                );
            }
        }

        // 60번 축제는 종료된 행사에 다수의 지원자가 존재한다.
        ApplicationRequest companyRequest = new ApplicationRequest(companyAnswersPair, companyEAnswersPair);

        pairCompany.forEach(id -> applicationCommandService.createCompanyApplication(id, 60L, companyRequest));
        pairLabor.forEach(id -> applicationCommandService.createLaborApplication(id, 60L, companyRequest));

        // 60번 축제는 종료된 행사에 다수의 지원자가 존재하고, 이들은 모두 선택된다.
        reviewCompany.forEach(id -> applicationCommandService.createCompanyApplication(id, 60L, companyRequest));
        reviewLabor.forEach(id -> applicationCommandService.createLaborApplication(id, 60L, companyRequest));
    }
}
