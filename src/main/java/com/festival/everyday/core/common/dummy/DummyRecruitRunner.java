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
 * festivalId 1,4,..,28 : 업체+단기근로자 공고 등록
 * festivalId 1,2,3,4,~30 : 업체 공고 등록
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

        // 축제 1
        CreateCompanyRecruitRequest companyRequest1 = new CreateCompanyRecruitRequest(
                LocalDateTime.now(),
                LocalDateTime.of(2025, 8, 25, 12, 0).minusDays(14).toLocalDate().atStartOfDay(),
                List.of(Category.ENTERTAINMENT, Category.FOOD),
                null,
                "위생 등급 인증 푸드트럭, 보험 가입 업체 우대",
                "각 부스에서 발생한 쓰레기·폐기물은 개별 처리하셔야 합니다.",
                List.of("부스 운영 계획(구성 인원 수, 가능한 날짜 구체적으로 기재)", "특이사항(진행시 필요한 요구사항)", "푸드 트럭의 경우 음식 판매 가격")
        );
        recruitCommandService.saveCompanyRecruit(companyRequest1, 1L);

        CreateLaborRecruitRequest laborRequest1 = new CreateLaborRecruitRequest(
                LocalDateTime.now(),
                LocalDateTime.of(2025, 8, 25, 12, 0).minusDays(15).toLocalDate().atStartOfDay(),
                "주차장 관리",
                "시급 10000원",
                "일하는 곳이 땡볕입니다.(차가 없을 때는 그늘 진 곳에서 쉬면 됩니다.)",
                "소홀히 하면 바로 교체될 수 있습니다.",
                List.of("관련 경험", "진상 고객 응대 방법")

        );
        recruitCommandService.saveLaborRecruit(laborRequest1, 1L);

        // 축제 2
        CreateCompanyRecruitRequest companyRequest2 = new CreateCompanyRecruitRequest(
                LocalDateTime.of(2024, 8, 6, 12, 0).minusDays(21).toLocalDate().atStartOfDay(),
                LocalDateTime.of(2024, 8, 6, 12, 0).minusDays(14).toLocalDate().atStartOfDay(),
                List.of(Category.ART, Category.FOOD),
                null,
                "위생 등급 인증 푸드트럭, 보험 가입 업체 우대",
                "각 부스에서 발생한 쓰레기·폐기물은 개별 처리하셔야 합니다.",
                List.of("부스 운영 계획(구성 인원 수, 가능한 날짜 구체적으로 기재)", "특이사항(진행시 필요한 요구사항)", "푸드 트럭의 경우 음식 판매 가격")
        );
        recruitCommandService.saveCompanyRecruit(companyRequest2, 2L);

        // 축제 3
        CreateCompanyRecruitRequest companyRequest3 = new CreateCompanyRecruitRequest(
                LocalDateTime.now(),
                LocalDateTime.of(2025, 11, 18, 12, 0).minusDays(14).toLocalDate().atStartOfDay(),
                List.of(Category.FOOD,Category.SALE),
                null,
                "위생 등급 인증 푸드트럭, 보험 가입 업체 우대",
                "각 부스에서 발생한 쓰레기·폐기물은 개별 처리하셔야 합니다.",
                List.of("부스 운영 계획(구성 인원 수, 가능한 날짜 구체적으로 기재)", "특이사항(진행시 필요한 요구사항)", "푸드 트럭의 경우 음식 판매 가격")
        );
        recruitCommandService.saveCompanyRecruit(companyRequest3, 3L);

        // 축제 4
        CreateCompanyRecruitRequest companyRequest4 = new CreateCompanyRecruitRequest(
                LocalDateTime.now(),
                LocalDateTime.of(2025, 10, 9, 10, 0).minusDays(14).toLocalDate().atStartOfDay(),
                List.of(Category.FOOD,Category.ART,Category.EXPERIENCE),
                null,
                "위생 등급 인증 푸드트럭, 보험 가입 업체 우대",
                "각 부스에서 발생한 쓰레기·폐기물은 개별 처리하셔야 합니다.",
                List.of("부스 운영 계획(구성 인원 수, 가능한 날짜 구체적으로 기재)", "특이사항(진행시 필요한 요구사항)", "푸드 트럭의 경우 음식 판매 가격")
        );
        recruitCommandService.saveCompanyRecruit(companyRequest4, 4L);

        CreateLaborRecruitRequest laborRequest4 = new CreateLaborRecruitRequest(
                LocalDateTime.now(),
                LocalDateTime.of(2025, 10, 9, 10, 0).minusDays(15).toLocalDate().atStartOfDay(),
                "주차장 관리",
                "시급 10000원",
                "일하는 곳이 땡볕입니다.(차가 없을 때는 그늘 진 곳에서 쉬면 됩니다.)",
                "소홀히 하면 바로 교체될 수 있습니다.",
                List.of("관련 경험", "진상 고객 응대 방법")

        );
        recruitCommandService.saveLaborRecruit(laborRequest4, 4L);
        // 축제 5
        CreateCompanyRecruitRequest companyRequest5 = new CreateCompanyRecruitRequest(
                LocalDateTime.now(),
                LocalDateTime.of(2026, 1, 20, 0, 0).minusDays(14).toLocalDate().atStartOfDay(),
                List.of(Category.FOOD,Category.SALE),
                null,
                "위생 등급 인증 푸드트럭, 보험 가입 업체 우대",
                "각 부스에서 발생한 쓰레기·폐기물은 개별 처리하셔야 합니다.",
                List.of("부스 운영 계획(구성 인원 수, 가능한 날짜 구체적으로 기재)", "특이사항(진행시 필요한 요구사항)", "푸드 트럭의 경우 음식 판매 가격")
        );
        recruitCommandService.saveCompanyRecruit(companyRequest5, 5L);

        // 축제 6
        CreateCompanyRecruitRequest companyRequest6 = new CreateCompanyRecruitRequest(
                LocalDateTime.of(2023, 9, 23, 10, 30).minusDays(21).toLocalDate().atStartOfDay(),
                LocalDateTime.of(2023, 9, 23, 10, 30).minusDays(14).toLocalDate().atStartOfDay(),
                List.of(Category.FOOD,Category.EXPERIENCE,Category.ENTERTAINMENT),
                null,
                "위생 등급 인증 푸드트럭, 보험 가입 업체 우대",
                "각 부스에서 발생한 쓰레기·폐기물은 개별 처리하셔야 합니다.",
                List.of("부스 운영 계획(구성 인원 수, 가능한 날짜 구체적으로 기재)", "특이사항(진행시 필요한 요구사항)", "푸드 트럭의 경우 음식 판매 가격")
        );
        recruitCommandService.saveCompanyRecruit(companyRequest6, 6L);

        // 축제 7
        CreateCompanyRecruitRequest companyRequest7 = new CreateCompanyRecruitRequest(
                LocalDateTime.of(2023, 11, 1, 10, 0).minusDays(21).toLocalDate().atStartOfDay(),
                LocalDateTime.of(2023, 11, 1, 10, 0).minusDays(14).toLocalDate().atStartOfDay(),
                List.of(Category.FOOD,Category.ART),
                null,
                "위생 등급 인증 푸드트럭, 보험 가입 업체 우대",
                "각 부스에서 발생한 쓰레기·폐기물은 개별 처리하셔야 합니다.",
                List.of("부스 운영 계획(구성 인원 수, 가능한 날짜 구체적으로 기재)", "특이사항(진행시 필요한 요구사항)", "푸드 트럭의 경우 음식 판매 가격")
        );
        recruitCommandService.saveCompanyRecruit(companyRequest7, 7L);

        CreateLaborRecruitRequest laborRequest7 = new CreateLaborRecruitRequest(
                LocalDateTime.of(2023, 11, 1, 10, 0).minusDays(22).toLocalDate().atStartOfDay(),
                LocalDateTime.of(2023, 11, 1, 10, 0).minusDays(15).toLocalDate().atStartOfDay(),
                "주차장 관리",
                "시급 10000원",
                "일하는 곳이 땡볕입니다.(차가 없을 때는 그늘 진 곳에서 쉬면 됩니다.)",
                "소홀히 하면 바로 교체될 수 있습니다.",
                List.of("관련 경험", "진상 고객 응대 방법")

        );
        recruitCommandService.saveLaborRecruit(laborRequest7, 7L);

        // 축제 8
        CreateCompanyRecruitRequest companyRequest8 = new CreateCompanyRecruitRequest(
                LocalDateTime.of(2024, 9, 27, 10, 0).minusDays(21).toLocalDate().atStartOfDay(),
                LocalDateTime.of(2024, 9, 27, 10, 0).minusDays(14).toLocalDate().atStartOfDay(),
                List.of(Category.FOOD,Category.CAMPAIGN,Category.ENTERTAINMENT,Category.EXPERIENCE),
                null,
                "위생 등급 인증 푸드트럭, 보험 가입 업체 우대",
                "각 부스에서 발생한 쓰레기·폐기물은 개별 처리하셔야 합니다.",
                List.of("부스 운영 계획(구성 인원 수, 가능한 날짜 구체적으로 기재)", "특이사항(진행시 필요한 요구사항)", "푸드 트럭의 경우 음식 판매 가격")
        );
        recruitCommandService.saveCompanyRecruit(companyRequest8, 8L);

        // 축제 9
        CreateCompanyRecruitRequest companyRequest9 = new CreateCompanyRecruitRequest(
                LocalDateTime.now(),
                LocalDateTime.of(2024, 9, 27, 10, 0).minusDays(14).toLocalDate().atStartOfDay(),
                List.of(Category.FOOD,Category.CAMPAIGN,Category.ENTERTAINMENT,Category.EXPERIENCE),
                null,
                "위생 등급 인증 푸드트럭, 보험 가입 업체 우대",
                "각 부스에서 발생한 쓰레기·폐기물은 개별 처리하셔야 합니다.",
                List.of("부스 운영 계획(구성 인원 수, 가능한 날짜 구체적으로 기재)", "특이사항(진행시 필요한 요구사항)", "푸드 트럭의 경우 음식 판매 가격")
        );
        recruitCommandService.saveCompanyRecruit(companyRequest9, 9L);

        // 축제 10
        CreateCompanyRecruitRequest companyRequest10 = new CreateCompanyRecruitRequest(
                LocalDateTime.of(2025, 5, 12, 0, 0).minusDays(21).toLocalDate().atStartOfDay(),
                LocalDateTime.of(2025, 5, 12, 0, 0).minusDays(14).toLocalDate().atStartOfDay(),
                List.of(Category.FOOD,Category.ART),
                null,
                "위생 등급 인증 푸드트럭, 보험 가입 업체 우대",
                "각 부스에서 발생한 쓰레기·폐기물은 개별 처리하셔야 합니다.",
                List.of("부스 운영 계획(구성 인원 수, 가능한 날짜 구체적으로 기재)", "특이사항(진행시 필요한 요구사항)", "푸드 트럭의 경우 음식 판매 가격")
        );
        recruitCommandService.saveCompanyRecruit(companyRequest10, 10L);

        CreateLaborRecruitRequest laborRequest10 = new CreateLaborRecruitRequest(
                LocalDateTime.of(2025, 5, 12, 0, 0).minusDays(22).toLocalDate().atStartOfDay(),
                LocalDateTime.of(2025, 5, 12, 0, 0).minusDays(15).toLocalDate().atStartOfDay(),
                "주차장 관리",
                "시급 10000원",
                "일하는 곳이 땡볕입니다.(차가 없을 때는 그늘 진 곳에서 쉬면 됩니다.)",
                "소홀히 하면 바로 교체될 수 있습니다.",
                List.of("관련 경험", "진상 고객 응대 방법")

        );
        recruitCommandService.saveLaborRecruit(laborRequest10, 10L);

        // 축제 11
        CreateCompanyRecruitRequest companyRequest11 = new CreateCompanyRecruitRequest(
                LocalDateTime.now(),
                LocalDateTime.of(2025, 9, 25, 12, 0).minusDays(14).toLocalDate().atStartOfDay(),
                List.of(Category.FOOD,Category.ART),
                null,
                "위생 등급 인증 푸드트럭, 보험 가입 업체 우대",
                "각 부스에서 발생한 쓰레기·폐기물은 개별 처리하셔야 합니다.",
                List.of("부스 운영 계획(구성 인원 수, 가능한 날짜 구체적으로 기재)", "특이사항(진행시 필요한 요구사항)", "푸드 트럭의 경우 음식 판매 가격")
        );
        recruitCommandService.saveCompanyRecruit(companyRequest11, 11L);

        // 축제 12
        CreateCompanyRecruitRequest companyRequest12 = new CreateCompanyRecruitRequest(
                LocalDateTime.of(2025, 5, 30, 19, 0).minusDays(21).toLocalDate().atStartOfDay(),
                LocalDateTime.of(2025, 5, 30, 19, 0).minusDays(14).toLocalDate().atStartOfDay(),
                List.of(Category.FOOD,Category.ART),
                null,
                "위생 등급 인증 푸드트럭, 보험 가입 업체 우대",
                "각 부스에서 발생한 쓰레기·폐기물은 개별 처리하셔야 합니다.",
                List.of("부스 운영 계획(구성 인원 수, 가능한 날짜 구체적으로 기재)", "특이사항(진행시 필요한 요구사항)", "푸드 트럭의 경우 음식 판매 가격")
        );
        recruitCommandService.saveCompanyRecruit(companyRequest12, 12L);

        // 축제 13
        CreateCompanyRecruitRequest companyRequest13 = new CreateCompanyRecruitRequest(
                LocalDateTime.now(),
                LocalDateTime.of(2025, 11, 2, 19, 0).minusDays(14).toLocalDate().atStartOfDay(),
                List.of(Category.FOOD,Category.ART,Category.SALE),
                null,
                "위생 등급 인증 푸드트럭, 보험 가입 업체 우대",
                "각 부스에서 발생한 쓰레기·폐기물은 개별 처리하셔야 합니다.",
                List.of("부스 운영 계획(구성 인원 수, 가능한 날짜 구체적으로 기재)", "특이사항(진행시 필요한 요구사항)", "푸드 트럭의 경우 음식 판매 가격")
        );
        recruitCommandService.saveCompanyRecruit(companyRequest13, 13L);

        CreateLaborRecruitRequest laborRequest13 = new CreateLaborRecruitRequest(
                LocalDateTime.now(),
                LocalDateTime.of(2025, 11, 2, 19, 0).minusDays(15).toLocalDate().atStartOfDay(),
                "주차장 관리",
                "시급 10000원",
                "일하는 곳이 땡볕입니다.(차가 없을 때는 그늘 진 곳에서 쉬면 됩니다.)",
                "소홀히 하면 바로 교체될 수 있습니다.",
                List.of("관련 경험", "진상 고객 응대 방법")

        );
        recruitCommandService.saveLaborRecruit(laborRequest13, 13L);

        // 축제 14
        CreateCompanyRecruitRequest companyRequest14 = new CreateCompanyRecruitRequest(
                LocalDateTime.now(),
                LocalDateTime.of(2025, 11, 2, 19, 0).minusDays(14).toLocalDate().atStartOfDay(),
                List.of(Category.FOOD,Category.ART,Category.ENTERTAINMENT,Category.CAMPAIGN,Category.EXPERIENCE),
                null,
                "위생 등급 인증 푸드트럭, 보험 가입 업체 우대",
                "각 부스에서 발생한 쓰레기·폐기물은 개별 처리하셔야 합니다.",
                List.of("부스 운영 계획(구성 인원 수, 가능한 날짜 구체적으로 기재)", "특이사항(진행시 필요한 요구사항)", "푸드 트럭의 경우 음식 판매 가격")
        );
        recruitCommandService.saveCompanyRecruit(companyRequest14, 14L);

        // 축제 15
        CreateCompanyRecruitRequest companyRequest15 = new CreateCompanyRecruitRequest(
                LocalDateTime.of(2024, 12, 5, 19, 0).minusDays(21).toLocalDate().atStartOfDay(),
                LocalDateTime.of(2024, 12, 5, 19, 0).minusDays(14).toLocalDate().atStartOfDay(),
                List.of(Category.FOOD,Category.ART),
                null,
                "위생 등급 인증 푸드트럭, 보험 가입 업체 우대",
                "각 부스에서 발생한 쓰레기·폐기물은 개별 처리하셔야 합니다.",
                List.of("부스 운영 계획(구성 인원 수, 가능한 날짜 구체적으로 기재)", "특이사항(진행시 필요한 요구사항)", "푸드 트럭의 경우 음식 판매 가격")
        );

        recruitCommandService.saveCompanyRecruit(companyRequest15, 15L);

        // 축제 16
        CreateCompanyRecruitRequest companyRequest16 = new CreateCompanyRecruitRequest(
                LocalDateTime.now(),
                LocalDateTime.of(2025, 11, 7, 21, 0).minusDays(14).toLocalDate().atStartOfDay(),
                List.of(Category.ENTERTAINMENT, Category.EXPERIENCE, Category.FOOD),
                null,
                "옥구슬 같은 목소리를 가지신 분, 시간 개념 정확하신 분 우대",
                "음향 시설, 전기 콘센트 등 전부 지원합니다.",
                List.of("부스 운영 계획(구성 인원 수, 가능한 날짜 구체적으로 기재)", "특이사항(진행시 필요한 요구사항)", "푸드 트럭의 경우 음식 판매 가격")
        );
        recruitCommandService.saveCompanyRecruit(companyRequest16, 16L);

        CreateLaborRecruitRequest laborRequest16 = new CreateLaborRecruitRequest(
                LocalDateTime.now(),
                LocalDateTime.of(2025, 11, 7, 21, 0).minusDays(15).toLocalDate().atStartOfDay(),
                "주차장 관리",
                "시급 10000원",
                "일하는 곳이 땡볕입니다.(차가 없을 때는 그늘 진 곳에서 쉬면 됩니다.)",
                "소홀히 하면 바로 교체될 수 있습니다.",
                List.of("관련 경험", "진상 고객 응대 방법")

        );
        recruitCommandService.saveLaborRecruit(laborRequest16, 16L);

        // 축제 17
        CreateCompanyRecruitRequest companyRequest17 = new CreateCompanyRecruitRequest(
                LocalDateTime.of(2024, 9, 28, 10, 0).minusDays(21).toLocalDate().atStartOfDay(),
                LocalDateTime.of(2024, 9, 28, 10, 0).minusDays(14).toLocalDate().atStartOfDay(),
                List.of(Category.CAMPAIGN, Category.FOOD),
                null,
                "소통이 잘 되시는 분, 시간 개념 정확하신 분 우대",
                "각 부스에서 발생한 쓰레기·폐기물은 개별 처리하셔야 합니다.",
                List.of("부스 운영 계획(구성 인원 수, 가능한 날짜 구체적으로 기재)", "특이사항(진행시 필요한 요구사항)", "푸드 트럭의 경우 음식 판매 가격")
        );
        recruitCommandService.saveCompanyRecruit(companyRequest17, 17L);

        // 축제 18
        CreateCompanyRecruitRequest companyRequest18 = new CreateCompanyRecruitRequest(
                LocalDateTime.of(2024, 6, 15, 10, 0).minusDays(21).toLocalDate().atStartOfDay(),
                LocalDateTime.of(2024, 6, 15, 10, 0).minusDays(14).toLocalDate().atStartOfDay(),
                List.of(Category.ENTERTAINMENT, Category.FOOD, Category.ART),
                null,
                "쓰레기를 최소화할 수 있는 용기를 사용하는 업체 우대",
                "모든 음식 재료는 신선하고 안전한 식자재를 사용해야합니다.",
                List.of("부스 운영 계획(구성 인원 수, 가능한 날짜 구체적으로 기재)", "특이사항(진행시 필요한 요구사항)", "푸드 트럭의 경우 음식 판매 가격")
        );
        recruitCommandService.saveCompanyRecruit(companyRequest18, 18L);

        // 축제 19
        CreateCompanyRecruitRequest companyRequest19 = new CreateCompanyRecruitRequest(
                LocalDateTime.now(),
                LocalDateTime.of(2025, 10, 9, 10, 0).minusDays(14).toLocalDate().atStartOfDay(),
                List.of(Category.ENTERTAINMENT, Category.FOOD, Category.EXPERIENCE),
                null,
                "체험에 필요한 도구 및 재료를 자체적으로 준비 가능한 업체 우대",
                "모든 체험 재료는 인체에 무해한 재료를 사용해야 합니다.",
                List.of("부스 운영 계획(구성 인원 수, 가능한 날짜 구체적으로 기재)", "특이사항(진행시 필요한 요구사항)", "푸드 트럭의 경우 음식 판매 가격")
        );
        recruitCommandService.saveCompanyRecruit(companyRequest19, 19L);

        CreateLaborRecruitRequest laborRequest19 = new CreateLaborRecruitRequest(
                LocalDateTime.now(),
                LocalDateTime.of(2025, 10, 9, 10, 0).minusDays(15).toLocalDate().atStartOfDay(),
                "주차장 관리",
                "시급 10000원",
                "일하는 곳이 땡볕입니다.(차가 없을 때는 그늘 진 곳에서 쉬면 됩니다.)",
                "소홀히 하면 바로 교체될 수 있습니다.",
                List.of("관련 경험", "진상 고객 응대 방법")

        );
        recruitCommandService.saveLaborRecruit(laborRequest19, 19L);

        // 축제 20
        CreateCompanyRecruitRequest companyRequest20 = new CreateCompanyRecruitRequest(
                LocalDateTime.of(2024, 10, 11, 10, 0).minusDays(21).toLocalDate().atStartOfDay(),
                LocalDateTime.of(2024, 10, 11, 10, 0).minusDays(14).toLocalDate().atStartOfDay(),
                List.of(Category.ENTERTAINMENT, Category.FOOD, Category.EXPERIENCE),
                null,
                "안전 수칙을 준수하며 프로그램을 진행할 수 있는 팀 우대",
                "공연/행사 시간 및 장소는 사전 협의된 내용을 따라야 합니다.",
                List.of("부스 운영 계획(구성 인원 수, 가능한 날짜 구체적으로 기재)", "특이사항(진행시 필요한 요구사항)", "푸드 트럭의 경우 음식 판매 가격")
        );
        recruitCommandService.saveCompanyRecruit(companyRequest20, 20L);

        // 축제 21
        CreateCompanyRecruitRequest companyRequest21 = new CreateCompanyRecruitRequest(
                LocalDateTime.now(),
                LocalDateTime.of(2025, 11, 17, 12, 0).minusDays(14).toLocalDate().atStartOfDay(),
                List.of(Category.ENTERTAINMENT, Category.FOOD, Category.EXPERIENCE),
                null,
                "짧은 시간 내에 다수가 참여할 수 있는 프로그램을 우대",
                "체험 중 발생한 쓰레기는 자체적으로 수거하여 지정된 장소에 버려주세요.",
                List.of("부스 운영 계획(구성 인원 수, 가능한 날짜 구체적으로 기재)", "특이사항(진행시 필요한 요구사항)", "푸드 트럭의 경우 음식 판매 가격")
        );
        recruitCommandService.saveCompanyRecruit(companyRequest21, 21L);

        // 축제 22
        CreateCompanyRecruitRequest companyRequest22 = new CreateCompanyRecruitRequest(
                LocalDateTime.now(),
                LocalDateTime.of(2025, 9, 1, 12, 0).minusDays(14).toLocalDate().atStartOfDay(),
                List.of(Category.ENTERTAINMENT, Category.FOOD, Category.EXPERIENCE),
                null,
                "위생 관리에 철저하며 관련 자격증을 보유한 업체 우대",
                "체험 중 발생한 쓰레기는 자체적으로 수거하여 지정된 장소에 버려주세요.",
                List.of("부스 운영 계획(구성 인원 수, 가능한 날짜 구체적으로 기재)", "특이사항(진행시 필요한 요구사항)", "푸드 트럭의 경우 음식 판매 가격")
        );
        recruitCommandService.saveCompanyRecruit(companyRequest22, 22L);

        CreateLaborRecruitRequest laborRequest22 = new CreateLaborRecruitRequest(
                LocalDateTime.now(),
                LocalDateTime.of(2025, 9, 1, 12, 0).minusDays(15).toLocalDate().atStartOfDay(),
                "주차장 관리",
                "시급 10000원",
                "일하는 곳이 땡볕입니다.(차가 없을 때는 그늘 진 곳에서 쉬면 됩니다.)",
                "소홀히 하면 바로 교체될 수 있습니다.",
                List.of("관련 경험", "진상 고객 응대 방법")

        );
        recruitCommandService.saveLaborRecruit(laborRequest22, 22L);
        // 축제 23
        CreateCompanyRecruitRequest companyRequest23 = new CreateCompanyRecruitRequest(
                LocalDateTime.now(),
                LocalDateTime.of(2025, 10, 19, 10, 0).minusDays(14).toLocalDate().atStartOfDay(),
                List.of(Category.ENTERTAINMENT, Category.FOOD, Category.EXPERIENCE),
                null,
                "네컷만화 그리기 수업을 주관할 수 있는 분 우대",
                "종이와 펜은 직접 준비하셔야합니다.",
                List.of("부스 운영 계획(구성 인원 수, 가능한 날짜 구체적으로 기재)", "특이사항(진행시 필요한 요구사항)", "푸드 트럭의 경우 음식 판매 가격")
        );
        recruitCommandService.saveCompanyRecruit(companyRequest23, 23L);

        // 축제 24
        CreateCompanyRecruitRequest companyRequest24 = new CreateCompanyRecruitRequest(
                LocalDateTime.of(2017, 7, 20, 10, 0).minusDays(21).toLocalDate().atStartOfDay(),
                LocalDateTime.of(2017, 7, 20, 10, 0).minusDays(14).toLocalDate().atStartOfDay(),
                List.of(Category.ART, Category.FOOD, Category.CAMPAIGN),
                null,
                "지속 가능한 환경 메시지를 전달할 수 있는 부스 우대",
                "부스는 상업적 목적이 아닌 공익 목적으로만 운영되어야 합니다.",
                List.of("부스 운영 계획(구성 인원 수, 가능한 날짜 구체적으로 기재)", "특이사항(진행시 필요한 요구사항)", "푸드 트럭의 경우 음식 판매 가격")
        );
        recruitCommandService.saveCompanyRecruit(companyRequest24, 24L);

        // 축제 25
        CreateCompanyRecruitRequest companyRequest25 = new CreateCompanyRecruitRequest(
                LocalDateTime.now(),
                LocalDateTime.of(2025, 10, 6, 12, 0).minusDays(14).toLocalDate().atStartOfDay(),
                List.of(Category.SALE, Category.FOOD, Category.EXPERIENCE),
                null,
                "제품에 대한 제조자, 원산지, 유통기한 등 명확한 정보 제공 가능 업체 우대",
                "판매 부스에서는 과도한 호객 행위를 삼가해주시기 바랍니다.",
                List.of("부스 운영 계획(구성 인원 수, 가능한 날짜 구체적으로 기재)", "특이사항(진행시 필요한 요구사항)", "푸드 트럭의 경우 음식 판매 가격")
        );
        recruitCommandService.saveCompanyRecruit(companyRequest25, 25L);

        CreateLaborRecruitRequest laborRequest25 = new CreateLaborRecruitRequest(
                LocalDateTime.of(2025, 10, 6, 12, 0).minusDays(22).toLocalDate().atStartOfDay(),
                LocalDateTime.of(2025, 10, 6, 12, 0).minusDays(15).toLocalDate().atStartOfDay(),
                "주차장 관리",
                "시급 10000원",
                "일하는 곳이 땡볕입니다.(차가 없을 때는 그늘 진 곳에서 쉬면 됩니다.)",
                "소홀히 하면 바로 교체될 수 있습니다.",
                List.of("관련 경험", "진상 고객 응대 방법")

        );
        recruitCommandService.saveLaborRecruit(laborRequest25, 25L);
        // 축제 26
        CreateCompanyRecruitRequest companyRequest26 = new CreateCompanyRecruitRequest(
                LocalDateTime.of(2024, 10, 5, 10, 0).minusDays(21).toLocalDate().atStartOfDay(),
                LocalDateTime.of(2024, 10, 5, 10, 0).minusDays(14).toLocalDate().atStartOfDay(),
                List.of(Category.SALE, Category.FOOD, Category.EXPERIENCE),
                null,
                "남녀노소 누구나 즐길 수 있는 창의적인 체험 콘텐츠 우대",
                "안전 수칙에 대한 사전 안내 후 체험을 진행해야 합니다.",
                List.of("부스 운영 계획(구성 인원 수, 가능한 날짜 구체적으로 기재)", "특이사항(진행시 필요한 요구사항)", "푸드 트럭의 경우 음식 판매 가격")
        );
        recruitCommandService.saveCompanyRecruit(companyRequest26, 26L);

        // 축제 27
        CreateCompanyRecruitRequest companyRequest27 = new CreateCompanyRecruitRequest(
                LocalDateTime.of(2025, 2, 19, 12, 0).minusDays(21).toLocalDate().atStartOfDay(),
                LocalDateTime.of(2025, 2, 19, 12, 0).minusDays(14).toLocalDate().atStartOfDay(),
                List.of(Category.CAMPAIGN, Category.FOOD, Category.ART),
                null,
                "서울 각 지역의 대학생들과 소통, 친목에 유능하신 분 우대",
                "음향 장비는 주최 측과 협의 후 반입 가능합니다.",
                List.of("부스 운영 계획(구성 인원 수, 가능한 날짜 구체적으로 기재)", "특이사항(진행시 필요한 요구사항)", "푸드 트럭의 경우 음식 판매 가격")
        );
        recruitCommandService.saveCompanyRecruit(companyRequest27, 27L);

        // 축제 28
        CreateCompanyRecruitRequest companyRequest28 = new CreateCompanyRecruitRequest(
                LocalDateTime.of(2025, 5, 10, 13, 0).minusDays(21).toLocalDate().atStartOfDay(),
                LocalDateTime.of(2025, 5, 10, 13, 0).minusDays(14).toLocalDate().atStartOfDay(),
                List.of(Category.CAMPAIGN, Category.FOOD, Category.EXPERIENCE),
                null,
                "지속 가능한 환경 메시지를 전달할 수 있는 부스 우대",
                "사전에 협의된 캠페인 목적과 내용만을 준수하여 운영해야 합니다.",
                List.of("부스 운영 계획(구성 인원 수, 가능한 날짜 구체적으로 기재)", "특이사항(진행시 필요한 요구사항)", "푸드 트럭의 경우 음식 판매 가격")
        );
        recruitCommandService.saveCompanyRecruit(companyRequest28, 28L);

        CreateLaborRecruitRequest laborRequest28 = new CreateLaborRecruitRequest(
                LocalDateTime.of(2025, 5, 10, 13, 0).minusDays(22).toLocalDate().atStartOfDay(),
                LocalDateTime.of(2025, 5, 10, 13, 0).minusDays(15).toLocalDate().atStartOfDay(),
                "주차장 관리",
                "시급 10000원",
                "일하는 곳이 땡볕입니다.(차가 없을 때는 그늘 진 곳에서 쉬면 됩니다.)",
                "소홀히 하면 바로 교체될 수 있습니다.",
                List.of("관련 경험", "진상 고객 응대 방법")

        );
        recruitCommandService.saveLaborRecruit(laborRequest28, 28L);

        // 축제 29
        CreateCompanyRecruitRequest companyRequest29 = new CreateCompanyRecruitRequest(
                LocalDateTime.now(),
                LocalDateTime.of(2025, 11, 3, 10, 0).minusDays(14).toLocalDate().atStartOfDay(),
                List.of(Category.ENTERTAINMENT, Category.FOOD, Category.SALE),
                null,
                "다양한 관람객을 유치할 수 있는 매력을 가진 부스 우대",
                "모든 부스 운영자는 축제 운영본부의 지침을 따라야 합니다.",
                List.of("부스 운영 계획(구성 인원 수, 가능한 날짜 구체적으로 기재)", "특이사항(진행시 필요한 요구사항)", "푸드 트럭의 경우 음식 판매 가격")
        );
        recruitCommandService.saveCompanyRecruit(companyRequest29, 29L);

    }
}