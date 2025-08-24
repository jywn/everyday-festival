package com.festival.everyday.core.common.dummy;

import com.festival.everyday.core.common.dto.command.AddressDto;
import com.festival.everyday.core.image.dto.common.ImageDto;
import com.festival.everyday.core.image.service.ImageCommandService;
import com.festival.everyday.core.user.domain.Category;
import com.festival.everyday.core.user.domain.Gender;
import com.festival.everyday.core.user.dto.request.CompanyRegisterRequest;
import com.festival.everyday.core.user.dto.request.HolderRegisterRequest;
import com.festival.everyday.core.user.dto.request.LaborRegisterRequest;
import com.festival.everyday.core.user.service.UserCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.ThreadLocalRandom;

@Order(1)
@Component
@RequiredArgsConstructor
public class DummyUserRunner implements CommandLineRunner {

    private final UserCommandService userCommandService;
    private final ImageCommandService imageCommandService;

    @Override
    public void run(String... args) throws Exception {
        /**
         * Company Dummy Data (60개)
         * userId 1은 사이트에서 접속할 대표 Dummy
         */
        //1
        CompanyRegisterRequest requestKing1 = new CompanyRegisterRequest(
                "C_acc_0",
                "C_pwd_0",
                "머엇사 꼬치집",
                "010-1111-7777",
                "likelion13thC@gmail.com",
                AddressDto.of("서울특별시", "마포구", "행복하구로 1"),
                "최고의 꼬치로 선사하겠습니다.",
                "likelion13thC.com",
                "머엇사",
                Category.FOOD
        );
        userCommandService.companyJoin(requestKing1);

        //2
        CompanyRegisterRequest Crequest2 = new CompanyRegisterRequest(
                "C_acc_1", "C_pwd_1",
                "기가막힌 꼬치집",
                "010-7777-0001",
                "likelion13thC1@gmail.com",
                AddressDto.of("서울특별시", "마포구", "서교동 와우산로 21길 3"),
                "숯향 그득한 꼬치로 모시겠습니다.",
                "likelion13thC1.com",
                "김지오",
                Category.FOOD
        );
        userCommandService.companyJoin(Crequest2);

        //3
        CompanyRegisterRequest Crequest3 = new CompanyRegisterRequest(
                "C_acc_2", "C_pwd_2",
                "50년 원조 주점",
                "010-7777-0002",
                "likelion13thC2@gmail.com",
                AddressDto.of("서울특별시", "마포구", "합정동 양화로 45"),
                "세월이 담긴 안주와 한 잔.",
                "likelion13thC2.com",
                "김종윤",
                Category.FOOD
        );
        userCommandService.companyJoin(Crequest3);

        //4
        CompanyRegisterRequest Crequest4 = new CompanyRegisterRequest(
                "C_acc_3", "C_pwd_3",
                "염리동 생과일주스",
                "010-7777-0003",
                "likelion13thC3@gmail.com",
                AddressDto.of("서울특별시", "마포구", "망원동 포은로6길 27"),
                "과즙 가득, 방금 갈아드립니다.",
                "likelion13thC3.com",
                "김지윤",
                Category.FOOD
        );
        userCommandService.companyJoin(Crequest4);

        //5
        CompanyRegisterRequest Crequest5 = new CompanyRegisterRequest(
                "C_acc_4", "C_pwd_4",
                "잡숴봐 만두집",
                "010-7777-0004",
                "likelion13thC4@gmail.com",
                AddressDto.of("서울특별시", "마포구", "상수동 와우산로 48"),
                "속 꽉 찬 수제만두의 진수.",
                "likelion13thC4.com",
                "김예나",
                Category.FOOD
        );
        userCommandService.companyJoin(Crequest5);

        //6
        CompanyRegisterRequest Crequest6 = new CompanyRegisterRequest(
                "C_acc_5", "C_pwd_5",
                "엄마손 분식집",
                "010-7777-0005",
                "likelion13thC5@gmail.com",
                AddressDto.of("서울특별시", "마포구", "연남동 성미산로 153"),
                "따뜻한 국물과 정성 한가득.",
                "likelion13thC5.com",
                "김서정",
                Category.FOOD
        );
        userCommandService.companyJoin(Crequest6);

        //7
        CompanyRegisterRequest Crequest7 = new CompanyRegisterRequest(
                "C_acc_6", "C_pwd_6",
                "홍대앞 세계 음식",
                "010-7777-0006",
                "likelion13thC6@gmail.com",
                AddressDto.of("서울특별시", "마포구", "성산동 월드컵로 235"),
                "세계 길거리 미식 투어!",
                "likelion13thC6.com",
                "김현서",
                Category.FOOD
        );
        userCommandService.companyJoin(Crequest7);

        //8
        CompanyRegisterRequest Crequest8 = new CompanyRegisterRequest(
                "C_acc_7", "C_pwd_7",
                "상암동 꼬치집",
                "010-7777-0007",
                "likelion13thC7@gmail.com",
                AddressDto.of("서울특별시", "마포구", "상암동 상암산로 66"),
                "불맛 살린 즉석 꼬치.",
                "likelion13thC7.com",
                "원지오",
                Category.FOOD
        );
        userCommandService.companyJoin(Crequest8);

        //9
        CompanyRegisterRequest Crequest9 = new CompanyRegisterRequest(
                "C_acc_8", "C_pwd_8",
                "합정역 주점",
                "010-7777-0008",
                "likelion13thC8@gmail.com",
                AddressDto.of("서울특별시", "마포구", "공덕동 마포대로 92"),
                "분위기 좋은 한 잔의 쉼.",
                "likelion13thC8.com",
                "원종윤",
                Category.FOOD
        );
        userCommandService.companyJoin(Crequest9);

        //10
        CompanyRegisterRequest Crequest10 = new CompanyRegisterRequest(
                "C_acc_9", "C_pwd_9",
                "망원 생과일주스",
                "010-7777-0009",
                "likelion13thC9@gmail.com",
                AddressDto.of("서울특별시", "마포구", "아현동 마포대로 12"),
                "달콤상큼 비타민 충전!",
                "likelion13thC9.com",
                "원지윤",
                Category.FOOD
        );
        userCommandService.companyJoin(Crequest10);

        //11
        CompanyRegisterRequest Crequest11 = new CompanyRegisterRequest(
                "C_acc_10", "C_pwd_10",
                "성산 만두집",
                "010-7777-0010",
                "likelion13thC10@gmail.com",
                AddressDto.of("서울특별시", "마포구", "염리동 백범로 35"),
                "속이 꽉 찬 수제만두 전문.",
                "likelion13thC10.com",
                "원예나",
                Category.FOOD
        );
        userCommandService.companyJoin(Crequest11);

        //12
        CompanyRegisterRequest Crequest12 = new CompanyRegisterRequest(
                "C_acc_11", "C_pwd_11",
                "공덕 분식집",
                "010-7777-0011",
                "likelion13thC11@gmail.com",
                AddressDto.of("서울특별시", "마포구", "신수동 대흥로 16"),
                "분식은 사랑입니다.",
                "likelion13thC11.com",
                "원서정",
                Category.FOOD
        );
        userCommandService.companyJoin(Crequest12);

        //13
        CompanyRegisterRequest Crequest13 = new CompanyRegisterRequest(
                "C_acc_12", "C_pwd_12",
                "아현 세계 음식",
                "010-7777-0012",
                "likelion13thC12@gmail.com",
                AddressDto.of("서울특별시", "마포구", "도화동 마포대로 63"),
                "골라먹는 월드 스트리트 푸드.",
                "likelion13thC12.com",
                "원현서",
                Category.FOOD
        );
        userCommandService.companyJoin(Crequest13);

        //14
        CompanyRegisterRequest Crequest14 = new CompanyRegisterRequest(
                "C_acc_13", "C_pwd_13",
                "연남 꼬치집",
                "010-7777-0013",
                "likelion13thC13@gmail.com",
                AddressDto.of("서울특별시", "마포구", "용강동 토정로 285"),
                "한 꼬치의 행복.",
                "likelion13thC13.com",
                "박지오",
                Category.FOOD
        );
        userCommandService.companyJoin(Crequest14);

        //15
        CompanyRegisterRequest Crequest15 = new CompanyRegisterRequest(
                "C_acc_14", "C_pwd_14",
                "서교 주점",
                "010-7777-0014",
                "likelion13thC14@gmail.com",
                AddressDto.of("서울특별시", "마포구", "대흥동 백범로 47"),
                "오늘 밤의 안주 페어링.",
                "likelion13thC14.com",
                "박종윤",
                Category.FOOD
        );
        userCommandService.companyJoin(Crequest15);

        //16
        CompanyRegisterRequest Crequest16 = new CompanyRegisterRequest(
                "C_acc_15", "C_pwd_15",
                "상수 생과일주스",
                "010-7777-0015",
                "likelion13thC15@gmail.com",
                AddressDto.of("서울특별시", "마포구", "서교동 어울마당로 84"),
                "꽉 찬 과일, 꽉 찬 하루.",
                "likelion13thC15.com",
                "박지윤",
                Category.FOOD
        );
        userCommandService.companyJoin(Crequest16);

        //17
        CompanyRegisterRequest Crequest17 = new CompanyRegisterRequest(
                "C_acc_16", "C_pwd_16",
                "DMC 만두집",
                "010-7777-0016",
                "likelion13thC16@gmail.com",
                AddressDto.of("서울특별시", "마포구", "합정동 양화로 55"),
                "만두 한 입, 행복 만점.",
                "likelion13thC16.com",
                "박예나",
                Category.FOOD
        );
        userCommandService.companyJoin(Crequest17);

        //18
        CompanyRegisterRequest Crequest18 = new CompanyRegisterRequest(
                "C_acc_17", "C_pwd_17",
                "마포나루 분식집",
                "010-7777-0017",
                "likelion13thC17@gmail.com",
                AddressDto.of("서울특별시", "마포구", "망원동 망원로 62"),
                "추억의 분식 맛 그대로.",
                "likelion13thC17.com",
                "박서정",
                Category.FOOD
        );
        userCommandService.companyJoin(Crequest18);

        //19
        CompanyRegisterRequest Crequest19 = new CompanyRegisterRequest(
                "C_acc_18", "C_pwd_18",
                "경의선숲길 소금집",
                "010-7777-0018",
                "likelion13thC18@gmail.com",
                AddressDto.of("서울특별시", "마포구", "성산동 성산로 128"),
                "소금이 정말 곱고 달아요",
                "likelion13thC18.com",
                "박현서",
                Category.FOOD
        );
        userCommandService.companyJoin(Crequest19);

        //20
        CompanyRegisterRequest Crequest20 = new CompanyRegisterRequest(
                "C_acc_19", "C_pwd_19",
                "서강대앞 꼬치집",
                "010-7777-0019",
                "likelion13thC19@gmail.com",
                AddressDto.of("서울특별시", "마포구", "상암동 월드컵북로 400"),
                "바삭쫄깃 꼬치의 정석.",
                "likelion13thC19.com",
                "이지오",
                Category.FOOD
        );
        userCommandService.companyJoin(Crequest20);

        //21
        CompanyRegisterRequest Crequest21 = new CompanyRegisterRequest(
                "C_acc_20", "C_pwd_20",
                "월드컵공원 주점",
                "010-7777-0020",
                "likelion13thC20@gmail.com",
                AddressDto.of("서울특별시", "마포구", "연남동 경의선숲길 146"),
                "한강 바람 맞으며 한 잔.",
                "likelion13thC20.com",
                "이종윤",
                Category.FOOD
        );
        userCommandService.companyJoin(Crequest21);

        //22
        CompanyRegisterRequest Crequest22 = new CompanyRegisterRequest(
                "C_acc_21", "C_pwd_21",
                "신수동 생과일주스",
                "010-7777-0021",
                "likelion13thC21@gmail.com",
                AddressDto.of("서울특별시", "마포구", "상수동 독막로15길 12"),
                "당도 높은 과일만 고집합니다.",
                "likelion13thC21.com",
                "이지윤",
                Category.FOOD
        );
        userCommandService.companyJoin(Crequest22);

        //23
        CompanyRegisterRequest Crequest23 = new CompanyRegisterRequest(
                "C_acc_22", "C_pwd_22",
                "용강동 만두집",
                "010-7777-0022",
                "likelion13thC22@gmail.com",
                AddressDto.of("서울특별시", "마포구", "공덕동 공덕로 15"),
                "야들야들 피, 육즙 가득 속.",
                "likelion13thC22.com",
                "이예나",
                Category.FOOD
        );
        userCommandService.companyJoin(Crequest23);

        //24
        CompanyRegisterRequest Crequest24 = new CompanyRegisterRequest(
                "C_acc_23", "C_pwd_23",
                "도화동 분식집",
                "010-7777-0023",
                "likelion13thC23@gmail.com",
                AddressDto.of("서울특별시", "마포구", "아현동 아현로 98"),
                "매일 아침 우린 육수로 맛을 냅니다.",
                "likelion13thC23.com",
                "이서정",
                Category.FOOD
        );
        userCommandService.companyJoin(Crequest24);

        //25
        CompanyRegisterRequest Crequest25 = new CompanyRegisterRequest(
                "C_acc_24", "C_pwd_24",
                "대흥동 세계 음식",
                "010-7777-0024",
                "likelion13thC24@gmail.com",
                AddressDto.of("서울특별시", "마포구", "염리동 백범로 34"),
                "한 자리에서 즐기는 글로벌 간식.",
                "likelion13thC24.com",
                "이현서",
                Category.FOOD
        );
        userCommandService.companyJoin(Crequest25);

        //26
        CompanyRegisterRequest Crequest26 = new CompanyRegisterRequest(
                "C_acc_25", "C_pwd_25",
                "창전동 꼬치집",
                "010-7777-0025",
                "likelion13thC25@gmail.com",
                AddressDto.of("서울특별시", "마포구", "신수동 광성로 23"),
                "즉석 화로구이 꼬치.",
                "likelion13thC25.com",
                "한지오",
                Category.FOOD
        );
        userCommandService.companyJoin(Crequest26);

        //27
        CompanyRegisterRequest Crequest27 = new CompanyRegisterRequest(
                "C_acc_26", "C_pwd_26",
                "노고산 주점",
                "010-7777-0026",
                "likelion13thC26@gmail.com",
                AddressDto.of("서울특별시", "마포구", "도화동 도화길 6"),
                "분위기 좋은 저녁 한 잔.",
                "likelion13thC26.com",
                "한종윤",
                Category.FOOD
        );
        userCommandService.companyJoin(Crequest27);

        //28
        CompanyRegisterRequest Crequest28 = new CompanyRegisterRequest(
                "C_acc_27", "C_pwd_27",
                "토정로 생과일주스",
                "010-7777-0027",
                "likelion13thC27@gmail.com",
                AddressDto.of("서울특별시", "마포구", "용강동 토정로37길 32"),
                "착즙으로 살아있는 신선함.",
                "likelion13thC27.com",
                "한지윤",
                Category.FOOD
        );
        userCommandService.companyJoin(Crequest28);

        //29
        CompanyRegisterRequest Crequest29 = new CompanyRegisterRequest(
                "C_acc_28", "C_pwd_28",
                "대흥로 소금집",
                "010-7777-0028",
                "likelion13thC28@gmail.com",
                AddressDto.of("서울특별시", "마포구", "대흥동 대흥로20길 28"),
                "국에 넣어도, 음식에 뿌려도 맛있는 달디단 소금 팝니다.",
                "likelion13thC28.com",
                "한예나",
                Category.FOOD
        );
        userCommandService.companyJoin(Crequest29);

        //30
        CompanyRegisterRequest Crequest30 = new CompanyRegisterRequest(
                "C_acc_29", "C_pwd_29",
                "성미산 분식집",
                "010-7777-0029",
                "likelion13thC29@gmail.com",
                AddressDto.of("서울특별시", "마포구", "서교동 와우산로 94"),
                "따끈한 분식으로 힘내세요!",
                "likelion13thC29.com",
                "한서정",
                Category.FOOD
        );
        userCommandService.companyJoin(Crequest30);

        //31
        CompanyRegisterRequest Crequest31 = new CompanyRegisterRequest(
                "C_acc_30", "C_pwd_30",
                "NEON STEP 커버댄스",
                "010-9898-0030",
                "likelion13thC30@gmail.com",
                AddressDto.of("서울특별시", "마포구", "서교동 연습실 A동 3F"),
                "K-POP 커버 중심 동아리. 군무/포메이션 맞춰 무대 지원합니다.",
                "neonstep-team.com",
                "김민준",
                Category.ART
        );
        userCommandService.companyJoin(Crequest31);

        //32
        CompanyRegisterRequest Crequest32 = new CompanyRegisterRequest(
                "C_acc_31", "C_pwd_31",
                "RHYTHM KIDZ 힙합 크루",
                "010-8787-0031",
                "likelion13thC31@gmail.com",
                AddressDto.of("서울특별시", "마포구", "연남동 연습실 B1"),
                "힙합/스트릿 기반 프리스타일 & 쇼케이스 지원.",
                "rhythmkidz.kr",
                "박서연",
                Category.ART
        );
        userCommandService.companyJoin(Crequest32);

        //33
        CompanyRegisterRequest Crequest33 = new CompanyRegisterRequest(
                "C_acc_32", "C_pwd_32",
                "홍익대학교 버스킹 밴드 H-Note",
                "010-9696-0032",
                "likelion13thC32@gmail.com",
                AddressDto.of("서울특별시", "마포구", "서교동 홍대입구역 인근 스튜디오"),
                "어쿠스틱/록 중심 버스킹 & 축소 밴드 세트 가능.",
                "h-note.live",
                "이지후",
                Category.ART
        );
        userCommandService.companyJoin(Crequest33);

        //34
        CompanyRegisterRequest Crequest34 = new CompanyRegisterRequest(
                "C_acc_33", "C_pwd_33",
                "서강대학교 보이스 앙상블",
                "010-7979-0033",
                "likelion13thC33@gmail.com",
                AddressDto.of("서울특별시", "마포구", "신수동 캠퍼스 근처 연습실"),
                "보컬 합창/하모니 팀. 마이크 세팅형 무대 지원.",
                "sogang-voice.club",
                "최도윤",
                Category.ART
        );
        userCommandService.companyJoin(Crequest34);

        //35
        CompanyRegisterRequest Crequest35 = new CompanyRegisterRequest(
                "C_acc_34", "C_pwd_34",
                "WAKKERS 왁킹 팀",
                "010-9090-0034",
                "likelion13thC34@gmail.com",
                AddressDto.of("서울특별시", "마포구", "합정동 연습실 2F"),
                "왁킹 포즈/암무브 중심. 3~5곡 쇼케이스 가능.",
                "wakkers.art",
                "정하린",
                Category.ART
        );
        userCommandService.companyJoin(Crequest35);

        //36
        CompanyRegisterRequest Crequest36 = new CompanyRegisterRequest(
                "C_acc_35", "C_pwd_35",
                "POPSHOT 팝핀 팀",
                "010-8686-0035",
                "likelion13thC35@gmail.com",
                AddressDto.of("서울특별시", "마포구", "상수동 연습실 201호"),
                "팝핀(히트) 라인업. 솔로/듀오/단체 무대 모두 가능.",
                "popshot-crew.com",
                "윤지호",
                Category.ART
        );
        userCommandService.companyJoin(Crequest36);

        //37
        CompanyRegisterRequest Crequest37 = new CompanyRegisterRequest(
                "C_acc_36", "C_pwd_36",
                "URBAN SCENE 어반 코레오",
                "010-7575-0036",
                "likelion13thC36@gmail.com",
                AddressDto.of("서울특별시", "마포구", "동교동 연습실 C동 5F"),
                "어반 코레오그래피. 곡 맞춤 컨셉/촬영형 무대 지원.",
                "urbanscene.team",
                "장유진",
                Category.ART
        );
        userCommandService.companyJoin(Crequest37);

        //38
        CompanyRegisterRequest Crequest38 = new CompanyRegisterRequest(
                "C_acc_37", "C_pwd_37",
                "STREET VOICE 보컬팀",
                "010-9494-0037",
                "likelion13thC37@gmail.com",
                AddressDto.of("서울특별시", "마포구", "공덕동 보컬룸 3호"),
                "가요/팝 보컬 유닛. 듀엣/합창 세트 지원.",
                "streetvoice.kr",
                "한서준",
                Category.ART
        );
        userCommandService.companyJoin(Crequest38);

        //39
        CompanyRegisterRequest Crequest39 = new CompanyRegisterRequest(
                "C_acc_38", "C_pwd_38",
                "MAPO JAZZ UNIT 밴드",
                "010-8888-0038",
                "likelion13thC38@gmail.com",
                AddressDto.of("서울특별시", "마포구", "상암동 합주실 B2"),
                "재즈 표준곡/라운지셋. 3~6인 구성 가능.",
                "mapojazzunit.com",
                "오예린",
                Category.ART
        );
        userCommandService.companyJoin(Crequest39);

        //40
        CompanyRegisterRequest Crequest40 = new CompanyRegisterRequest(
                "C_acc_39", "C_pwd_39",
                "BREAKERS 브레이킹 크루",
                "010-9393-0039",
                "likelion13thC39@gmail.com",
                AddressDto.of("서울특별시", "마포구", "성산동 연습실 지하"),
                "브레이킹 쇼케이스/배틀 데모. 풋워크·파워무브 구성.",
                "breakers-crew.io",
                "임현서",
                Category.ART
        );
        userCommandService.companyJoin(Crequest40);

        //41
        CompanyRegisterRequest Crequest41 = new CompanyRegisterRequest(
                "C_acc_40", "C_pwd_40",
                "360 스튜디오 포토부스",
                "010-8282-0040",
                "likelion13thC40@gmail.com",
                AddressDto.of("서울특별시", "마포구", "서교동 팝업스튜디오 2F"),
                "360도 회전 영상 촬영 포토부스. SNS 하이라이트 영상 즉시 제공.",
                "mapo360booth.com",
                "김하준",
                Category.ENTERTAINMENT
        );
        userCommandService.companyJoin(Crequest41);

        //42
        CompanyRegisterRequest Crequest42 = new CompanyRegisterRequest(
                "C_acc_41", "C_pwd_41",
                "상암 VR 체험존",
                "010-7373-0041",
                "likelion13thC41@gmail.com",
                AddressDto.of("서울특별시", "마포구", "상암동 장비보관소 1층"),
                "VR 레이싱·코스터·좀비 등 테마 라인업. 헤드셋/콘솔/안전요원 지원.",
                "sangam-vr.fun",
                "박지아",
                Category.ENTERTAINMENT
        );
        userCommandService.companyJoin(Crequest42);

        //43
        CompanyRegisterRequest Crequest43 = new CompanyRegisterRequest(
                "C_acc_42", "C_pwd_42",
                "ESC LAB 모바일 방탈출",
                "010-6464-0042",
                "likelion13thC42@gmail.com",
                AddressDto.of("서울특별시", "마포구", "합정동 텐트존 A동"),
                "텐트형 방탈출. 15~20분 러닝타임으로 회차 운영(팀 단위).",
                "esc-lab-mapo.com",
                "이도현",
                Category.ENTERTAINMENT
        );
        userCommandService.companyJoin(Crequest43);

        //44
        CompanyRegisterRequest Crequest44 = new CompanyRegisterRequest(
                "C_acc_43", "C_pwd_43",
                "FUN CLAW 팝업 오락트럭",
                "010-5555-0043",
                "likelion13thC43@gmail.com",
                AddressDto.of("서울특별시", "마포구", "망원동 게임트럭 주차장"),
                "집게뽑기·미니게임 부스. 경품/브랜딩 커스터마이즈 가능.",
                "funclaw-pop.kr",
                "정은채",
                Category.ENTERTAINMENT
        );
        userCommandService.companyJoin(Crequest44);

        //45
        CompanyRegisterRequest Crequest45 = new CompanyRegisterRequest(
                "C_acc_44", "C_pwd_44",
                "COLOR POP 페이스&벌룬",
                "010-4747-0044",
                "likelion13thC44@gmail.com",
                AddressDto.of("서울특별시", "마포구", "염리동 페이스페인팅 스튜디오"),
                "페이스페인팅·풍선아트/스티커 타투 키오스크. 키즈·패밀리 타깃.",
                "colorpop-face.com",
                "오세진",
                Category.ENTERTAINMENT
        );
        userCommandService.companyJoin(Crequest45);

        //46
        CompanyRegisterRequest Crequest46 = new CompanyRegisterRequest(
                "C_acc_45", "C_pwd_45",
                "페이스페인팅 원데이 페인터즈",
                "010-7171-0045",
                "likelion13thC45@gmail.com",
                AddressDto.of("서울특별시", "마포구", "합정동 페인트 스튜디오 2F"),
                "아이·성인 모두 참여 가능. 기본 도안+맞춤 드로잉, 소요 60~90분.",
                "facepainters-oneclass.com",
                "김수현",
                Category.EXPERIENCE
        );
        userCommandService.companyJoin(Crequest46);

        //47
        CompanyRegisterRequest Crequest47 = new CompanyRegisterRequest(
                "C_acc_46", "C_pwd_46",
                "라떼아트 클래스 LATTE LAB",
                "010-6262-0046",
                "likelion13thC46@gmail.com",
                AddressDto.of("서울특별시", "마포구", "서교동 바리스타룸 101호"),
                "우유 스티밍 기본부터 하트/튤립까지. 1:1 교정, 90분 진행.",
                "lattelab-class.kr",
                "박서윤",
                Category.EXPERIENCE
        );
        userCommandService.companyJoin(Crequest47);

        //48
        CompanyRegisterRequest Crequest48 = new CompanyRegisterRequest(
                "C_acc_47", "C_pwd_47",
                "도예 핸드빌딩 CLAY DAY",
                "010-5353-0047",
                "likelion13thC47@gmail.com",
                AddressDto.of("서울특별시", "마포구", "망원동 도자공방 A동"),
                "코일링/판작업으로 컵·접시 제작. 초벌/재벌 후 수령 안내.",
                "clayday-handbuild.com",
                "이도윤",
                Category.EXPERIENCE
        );
        userCommandService.companyJoin(Crequest48);

        //49
        CompanyRegisterRequest Crequest49 = new CompanyRegisterRequest(
                "C_acc_48", "C_pwd_48",
                "향수 만들기 PERFUME LAB",
                "010-4444-0048",
                "likelion13thC48@gmail.com",
                AddressDto.of("서울특별시", "마포구", "연남동 아로마 스튜디오 3층"),
                "시향→블렌딩→라벨링. 나만의 향 30ml 완성, 포장 제공.",
                "perfume-lab.one",
                "정민재",
                Category.EXPERIENCE
        );
        userCommandService.companyJoin(Crequest49);

        //50
        CompanyRegisterRequest Crequest50 = new CompanyRegisterRequest(
                "C_acc_49", "C_pwd_49",
                "백드롭/나이프 페인팅 드로잉클럽",
                "010-3333-0049",
                "likelion13thC49@gmail.com",
                AddressDto.of("서울특별시", "마포구", "동교동 아트룸 5F"),
                "아크릴·나이프 기법으로 추상화 한 점 완성. 재료 일체 제공.",
                "drawingclub-workshop.com",
                "최아린",
                Category.EXPERIENCE
        );
        userCommandService.companyJoin(Crequest50);

        //51
        CompanyRegisterRequest Crequest51 = new CompanyRegisterRequest(
                "C_acc_50", "C_pwd_50",
                "연남 캔들&디퓨저 HAND FRAGRANCE",
                "010-5050-0050",
                "likelion13thC50@gmail.com",
                AddressDto.of("서울특별시", "마포구", "연남동 공방 2F"),
                "천연 소이캔들·디퓨저·석고 방향. 라벨/메시지 현장 커스터마이즈.",
                "handfragrance-mapo.com",
                "정다은",
                Category.SALE
        );
        userCommandService.companyJoin(Crequest51);

        //52
        CompanyRegisterRequest Crequest52 = new CompanyRegisterRequest(
                "C_acc_51", "C_pwd_51",
                "서교 마크라메 & 위빙 LOOM",
                "010-5151-0051",
                "likelion13thC51@gmail.com",
                AddressDto.of("서울특별시", "마포구", "서교동 공방 3층"),
                "월행잉/코스터/키링 등 섬유 공예 소품. 색상·패턴 커스텀 판매.",
                "loom-mapo.kr",
                "이준호",
                Category.SALE
        );
        userCommandService.companyJoin(Crequest52);

        //53
        CompanyRegisterRequest Crequest53 = new CompanyRegisterRequest(
                "C_acc_52", "C_pwd_52",
                "망원 도예 CLAY STUDIO",
                "010-5252-0052",
                "likelion13thC52@gmail.com",
                AddressDto.of("서울특별시", "마포구", "망원동 도자공방 A동"),
                "머그·접시·소접시·인센스 홀더 등 소량 수작업 완제품 판매.",
                "claystudio-map.kr",
                "박하린",
                Category.SALE
        );
        userCommandService.companyJoin(Crequest53);

        //54
        CompanyRegisterRequest Crequest54 = new CompanyRegisterRequest(
                "C_acc_53", "C_pwd_53",
                "합정 레더 HAND STITCH",
                "010-5353-0053",
                "likelion13thC53@gmail.com",
                AddressDto.of("서울특별시", "마포구", "합정동 레더룸 1층"),
                "핸드스티치 카드지갑·키링·북마크. 이니셜 각인 서비스.",
                "handstitch-leather.com",
                "김도윤",
                Category.SALE
        );
        userCommandService.companyJoin(Crequest54);

        //55
        CompanyRegisterRequest Crequest55 = new CompanyRegisterRequest(
                "C_acc_54", "C_pwd_54",
                "홍대 레진&주얼리 RESIN POP",
                "010-5454-0054",
                "likelion13thC54@gmail.com",
                AddressDto.of("서울특별시", "마포구", "서교동 악세스튜디오 202호"),
                "드라이플라워·자개 인레이 레진 귀걸이/목걸이 커스텀 판매.",
                "resinpop-hongdae.com",
                "최서우",
                Category.SALE
        );
        userCommandService.companyJoin(Crequest55);

        //56
        CompanyRegisterRequest Crequest56 = new CompanyRegisterRequest(
                "C_acc_55", "C_pwd_55",
                "헌혈의 집",
                "010-5656-0055",
                "likelion13thC55@gmail.com",
                AddressDto.of("서울특별시", "마포구", "상암동 월드컵로 240 캠페인존"),
                "헌혈 절차/유의사항 안내, 현장 예약 QR 및 단체헌혈 신청 연계.",
                "mapo-blood-campaign.org",
                "김태윤",
                Category.CAMPAIGN
        );
        userCommandService.companyJoin(Crequest56);

        //57
        CompanyRegisterRequest Crequest57 = new CompanyRegisterRequest(
                "C_acc_56", "C_pwd_56",
                "아동권리 캠페인 ‘굿액션’",
                "010-5757-0056",
                "likelion13thC56@gmail.com",
                AddressDto.of("서울특별시", "마포구", "합정동 양화로 55 캠페인부스 A"),
                "아동권리·학대예방 홍보, 1분 서약 & 소액 모금 QR 운영.",
                "goodaction-kids.org",
                "이하린",
                Category.CAMPAIGN
        );
        userCommandService.companyJoin(Crequest57);

        //58
        CompanyRegisterRequest Crequest58 = new CompanyRegisterRequest(
                "C_acc_57", "C_pwd_57",
                "야생동물 서식지 지키기",
                "010-5858-0057",
                "likelion13thC57@gmail.com",
                AddressDto.of("서울특별시", "마포구", "상암동 월드컵공원 평화의공원 안내존"),
                "멸종위기 서식지 보호 서명·굿즈 모금, 서식지 정보 패널 전시.",
                "habitat-guardian.kr",
                "정우빈",
                Category.CAMPAIGN
        );
        userCommandService.companyJoin(Crequest58);

        //59
        CompanyRegisterRequest Crequest59 = new CompanyRegisterRequest(
                "C_acc_58", "C_pwd_58",
                "플라스틱 프리 MAPO",
                "010-5959-0058",
                "likelion13thC58@gmail.com",
                AddressDto.of("서울특별시", "마포구", "홍대 걷고싶은거리 캠페인 라인"),
                "일회용 줄이기 서약, 재사용 보틀 스티커·리유즈 팁 카드 배포.",
                "plasticfree-mapo.org",
                "박지안",
                Category.CAMPAIGN
        );
        userCommandService.companyJoin(Crequest59);

        //60
        CompanyRegisterRequest Crequest60 = new CompanyRegisterRequest(
                "C_acc_59", "C_pwd_59",
                "맵보 플로깅 러너스",
                "010-6060-0059",
                "likelion13thC59@gmail.com",
                AddressDto.of("서울특별시", "마포구", "마포나루길 한강공원 집결지"),
                "걷거나 뛰며 쓰레기 줍기(플로깅) 코스 운영·인증 미션 진행.",
                "plogging-mapo.run",
                "최서준",
                Category.CAMPAIGN
        );
        userCommandService.companyJoin(Crequest60);

        /**
         * Labor Dummy Data (30개)
         * userId 61은 사이트에서 접속할 대표 Dummy
         */
        //61
        LaborRegisterRequest LrequestKing = new LaborRegisterRequest(
                "L_acc_0",
                "L_pwd_0",
                "김멋사",
                "010-1233-2334",
                "likelion13thL@gmail.com",
                AddressDto.of("서울특별시", "마포구", "아름답구로 1"),
                31,
                Gender.MALE
        );
        userCommandService.laborJoin(LrequestKing);

        String[] laborLastNames = {"이", "정", "제갈", "설", "남궁", "백"};
        String[] laborFirstNames = {"현진", "승연", "현석", "준수", "경우", "민우"};
        //62~90
        for (int i = 1; i < 30; i++) {
            String fullName = laborLastNames[(i - 1) % laborLastNames.length] + laborFirstNames[(i - 1) % laborFirstNames.length];
            int telMid = ThreadLocalRandom.current().nextInt(1000, 10000);
            int telEnd = ThreadLocalRandom.current().nextInt(1000, 10000);
            String tel = "010-" + telMid + "-" + telEnd;
            String email = "labor" + i + "@gmail.com";

            AddressDto address = AddressDto.of("서울특별시", "마포구", "멋사로" + i+100);
            LaborRegisterRequest request = new LaborRegisterRequest(
                    "L_acc_" + i,
                    "L_pwd_" + i,
                    fullName,
                    tel,
                    email,
                    address,
                    i+15,
                    i % 2 == 0 ? Gender.MALE : Gender.FEMALE
            );
            userCommandService.laborJoin(request);
        }

        /**
         * Holder Dummy Data (10개)
         * userId 91은 사이트에서 접속할 대표 Dummy
         */
        //91
        HolderRegisterRequest HrequestKing = new HolderRegisterRequest(
                "H_acc_0",
                "H_pwd_0",
                "멋쟁이사자처럼_기획자",
                "010-1234-5679",
                "likelion13thH@sogang.ac.kr",
                AddressDto.of("서울특별시", "마포구", "멋사로 1")
        );
        userCommandService.holderJoin(HrequestKing);

        //92
        HolderRegisterRequest Hrequest1 = new HolderRegisterRequest(
                    "H_acc_1",
                    "H_pwd_1",
                    "염리동 주민자치 주관회",
                    "02-3153-6679",
                    "yeomrijumin@hanmail.net",
                    AddressDto.of("서울특별시" , "마포구", "숭문길 14")
        );
        userCommandService.holderJoin(Hrequest1);

        //93
        HolderRegisterRequest Hrequest2 = new HolderRegisterRequest(
                "H_acc_2",
                "H_pwd_2",
                "용강동 주민자치 위원회",
                "02-3153-6590",
                "mapolovedream@naver.com",
                AddressDto.of("서울특별시" , "마포구", "토정로 31길 31")
        );
        userCommandService.holderJoin(Hrequest2);

        //94
        HolderRegisterRequest Hrequest3 = new HolderRegisterRequest(
                "H_acc_3",
                "H_pwd_3",
                "서강대학교 대동제 축제준비위원회",
                "02-1174-5673",
                "sgstudent@sogang.ac.kr",
                AddressDto.of("서울특별시" , "마포구", "백범로 35")
        );
        userCommandService.holderJoin(Hrequest3);

        //95
        HolderRegisterRequest Hrequest4 = new HolderRegisterRequest(
                "H_acc_4",
                "H_pwd_4",
                "서강대학교 중앙스트릿댄스 동아리 SHOCK",
                "010-9338-5888",
                "yoodeok0801@gmail.com",
                AddressDto.of("서울특별시" , "마포구", "백범로 35 엠마오관 B106")
        );
        userCommandService.holderJoin(Hrequest4);

        //96
        HolderRegisterRequest Hrequest5 = new HolderRegisterRequest(
                "H_acc_5",
                "H_pwd_5",
                "엑스타시",
                "010-2384-5999",
                "sjhan@naver.com",
                AddressDto.of("서울특별시" , "마포구", "백범로 35 우정관 4층")
        );
        userCommandService.holderJoin(Hrequest5);

        //97
        HolderRegisterRequest Hrequest6 = new HolderRegisterRequest(
                "H_acc_6",
                "H_pwd_6",
                "대흥동 주민자치 위원회",
                 "02-3153-6620",
                "daeheunglove@naver.com",
                AddressDto.of("서울특별시" , "마포구", "신촌로 26길 10")
        );
        userCommandService.holderJoin(Hrequest6);

        //98
        HolderRegisterRequest Hrequest7 = new HolderRegisterRequest(
                "H_acc_7",
                "H_pwd_7",
                "잔다리 축제 기획단",
                "02-2133-2538",
                " jandarifestival@naver.com",
                AddressDto.of("서울특별시" , "마포구", "동교로 15길 7")
        );
        userCommandService.holderJoin(Hrequest7);

        //99
        HolderRegisterRequest Hrequest8 = new HolderRegisterRequest(
                "H_acc_8",
                "H_pwd_8",
                "연남동 축제 추진위원회",
                "02-3153-6860",
                " yeonnamfestival@gmail.com",
                AddressDto.of("서울특별시" , "마포구", "성미산로29길 17-9")
        );
        userCommandService.holderJoin(Hrequest8);

        //100
        HolderRegisterRequest Hrequest9 = new HolderRegisterRequest(
                "H_acc_9",
                "H_pwd_9",
                "서울연합동아리",
                "010-6264-7222",
                " Seouldongari@gmail.com",
                AddressDto.of("서울특별시" , "강남구", "강남로 1")
        );
        userCommandService.holderJoin(Hrequest9);
    }


}
