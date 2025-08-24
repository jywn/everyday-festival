//package com.festival.everyday.core.common.dummy;
//
//import com.festival.everyday.core.ai.service.EmbeddingService;
//import com.festival.everyday.core.common.dto.command.AddressDto;
//import com.festival.everyday.core.common.dto.command.PeriodDto;
//import com.festival.everyday.core.festival.dto.request.FestivalFormRequest;
//import com.festival.everyday.core.festival.service.FestivalCommandService;
//import com.festival.everyday.core.user.domain.Category;
//import com.festival.everyday.core.user.dto.request.CompanyRegisterRequest;
//import com.festival.everyday.core.user.dto.request.HolderRegisterRequest;
//import com.festival.everyday.core.user.service.UserCommandService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//
//@Order(1)
//@Component
//@RequiredArgsConstructor
//public class DummyRecommendRunner implements CommandLineRunner {
//
//    private final UserCommandService userCommandService;
//    private final FestivalCommandService festivalCommandService;
//    private final EmbeddingService embeddingService;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        userCommandService.holderJoin(new HolderRegisterRequest(
//                "acc",
//                "pwd",
//                "대표 기획자",
//                "010-6346-1851",
//                "jywon1128@gmail.com",
//                AddressDto.of("서울시", "마포구", "월드컵로 15길 83")
//        ));
//
//        FestivalFormRequest request1 = new FestivalFormRequest(
//                "멋쟁이사자처럼 13기 전국연합 해커톤 대회",
//                PeriodDto.of(
//                        LocalDateTime.of(2025, 8, 25, 12, 0),
//                        LocalDateTime.of(2025,8,26,6,0)),
//                AddressDto.of("서울특별시", "마포구", "연세로 19 앞 광장"),
//                "참가비 무료",
//                "25일 점심 12시부터 26일 아침 6시까지 진행",
//                "국내 최대 규모의 IT 창s업 동아리 네트워크가 모여, 무박 2일 동안 아이디어를 ‘실제 서비스’로 구현하는 전국 연합 해커톤입니다.\n"
//                        + "팀원과 함께 한계를 넘고, 현장에서 성장과 네트워킹을 경험하세요.\n"
//                        + "대회장에 오시면 다들 심심하지 않게 여러 오락시설들과 푸드 트럭도 있습니다.\n",
//                "https://likelion.net/",
//                "010-9411-2750"
//        );
//        saveAndEmbedFestival(request1);
//
//        //2
//        FestivalFormRequest request2 = new FestivalFormRequest(
//                "멋쟁이사자처럼 12기 전국연합 해커톤 대회",
//                PeriodDto.of(
//                        LocalDateTime.of(2024, 8, 6, 12, 0),
//                        LocalDateTime.of(2024,8,7,6,0)),
//                AddressDto.of("서울특별시", "마포구", "백범로 35"),
//                "참가비 무료",
//                "6일 점심 12시부터 7일 아침 6시까지 진행",
//                "전공·학교를 넘어 모인 동료들과 팀을 꾸려 문제를 정의하고 해결합니다.\n"
//                        +"밤새 협업하며 프로토타입을 완성하고, 다음 스텝으로 이어질 파트너와 멘토를 만나보세요.\n"
//                        +"하루가 지루하지 않도록 여러 학생 밴드와 춤 동아리를 섭외할 예정입니다!",
//                "https://likelion.net/",
//                "010-5656-3434"
//        );
//
//        saveAndEmbedFestival(request2);
//
//        //3
//        FestivalFormRequest request3 = new FestivalFormRequest(
//                "멋쟁이사자처럼 13기 신촌 멋쟁이사자처럼 대학 연합 데모데이",
//                PeriodDto.of(
//                        LocalDateTime.of(2025, 11, 18, 12, 0),
//                        LocalDateTime.of(2025,11,19,18,0)),
//                AddressDto.of("서울특별시", "마포구", "백범로 35"),
//                "참가비 무료",
//                "18일 : 오후 12시 ~ 오후 21시\n"+"19일 : 오전 10시 ~ 오후 18시",
//                "멋쟁이사자처럼에서 열심히 가꿔온 실력을 마음껏 표출하세요!\n"
//                        +"마지막 프로젝트인만큼 힘내시라고 맛있는 음식들이 즐비할 것입니다!",
//                "https://sglikelion.com",
//                "010-5959-3399"
//        );
//
//        saveAndEmbedFestival(request3);
//
//        //4
//        FestivalFormRequest request4 = new FestivalFormRequest(
//                "2025 염리동 소금 축제",
//                PeriodDto.of(
//                        LocalDateTime.of(2025, 10, 9, 10, 0),
//                        LocalDateTime.of(2025,10,9,16,0)),
//                AddressDto.of("서울특별시", "마포구", "대흥로 20길 28 마포아트센터 광장 앞"),
//                "참가비 1000원",
//                "10월 9일 오전 10시 ~ 오후 4시",
//                "염리동의 이야기와 소금의 매력을 한자리에서! 푸드트럭,체험,라이브 공연으로 하루를 꽉 채우는 로컬 페스티벌",
//                "yeomrisaltfestival.com",
//                "010-5111-8181"
//        );
//        saveAndEmbedFestival(request4);
//
//        //5
//        FestivalFormRequest request5 = new FestivalFormRequest(
//                "2026 염리동 눈꽃 축제",
//                PeriodDto.of(
//                        LocalDateTime.of(2026, 1, 20, 0, 0),
//                        LocalDateTime.of(2026,1,29,18,0)),
//                AddressDto.of("서울특별시", "마포구", "백범로 35 서강대학교 후문 거리"),
//                "참가비 무료",
//                "1월 26,27일 : 오전 10시 ~ 오후 6시\n"+
//                        "그 외 : 오전 10시 ~ 오후 9시",
//                "염리동에도 새하얀 눈의 축제가 찾아왔어요! 새하얀 눈과 따뜻하고 다양한 먹거리를 체험하세요",
//                "snowfestival.com",
//                "010-1313-5678"
//        );
//        saveAndEmbedFestival(request5);
//        //6
//        FestivalFormRequest request6 = new FestivalFormRequest(
//                "2023 염리동 소금 축제",
//                PeriodDto.of(
//                        LocalDateTime.of(2023, 9, 23, 10, 30),
//                        LocalDateTime.of(2023,9,23,16,0)),
//                AddressDto.of("서울특별시", "마포구", "대흥로 20길 28 마포아트센터 광장 앞"),
//                "참가비 1000원",
//                "10월 9일 오전 10시 ~ 오후 4시",
//                "한 줌의 소금에서 시작되는 특별한 체험, 공연, 포토존까지 염리동을 가득 담았습니다.",
//                "yeomrisaltfestival.com",
//                "010-5111-8181"
//        );
//        saveAndEmbedFestival(request6);
//
//        //7
//        FestivalFormRequest request7 = new FestivalFormRequest(
//                "제1회 용강동 마을축제 '토정아 놀자!'",
//                PeriodDto.of(
//                        LocalDateTime.of(2023, 11, 1, 10, 0),
//                        LocalDateTime.of(2023, 11, 1, 18, 0)
//                ),
//                AddressDto.of("서울특별시", "마포구", "도화소어린이공원"),
//                "참가비 무료",
//                "2024년 11월 1일 : 오전 10시 ~ 오후 18시",
//                "용강동 주민자치위원회가 주관하는 마을 축제 '토정아 놀자!' 🎉\n" +
//                        "아이부터 어르신까지 주민 모두가 함께 즐기는 화합의 장입니다.\n" +
//                        "다양한 공연, 먹거리, 토정이랑 술래잡기 체험이 준비되어 있으며,\n" +
//                        "이웃과 함께 즐겁게 어울릴 수 있는 따뜻한 마을 축제입니다.",
//                "https://yonggang.letsgo.kr",
//                "010-3535-7788"
//        );
//        saveAndEmbedFestival(request7);
//
//        //8
//        FestivalFormRequest request8 = new FestivalFormRequest(
//                "2024 용강동 가면무도화 축제",
//                PeriodDto.of(
//                        LocalDateTime.of(2024, 9, 27, 10, 0),
//                        LocalDateTime.of(2024, 10, 6, 18, 0)
//                ),
//                AddressDto.of("서울특별시", "마포구", "용강관무용공원 일대"),
//                "참가비 무료",
//                "9월 27일(금) ~ 10월 6일(일)\n" +
//                        "매일 오전 10시 ~ 오후 18시",
//                "용강동 주민자치위원회가 주관하는 화려한 가면무도화 축제!\n" +
//                        "다양한 전통 공연, 가면 퍼레이드, 문화 체험과 함께\n" +
//                        "주민과 방문객 모두가 즐길 수 있는 특별한 가을 축제입니다.\n" +
//                        "여러 캠페인과 체험 부스 환영입니다!",
//                "https://yonggang.letsgo.kr",
//                "010-3535-7788"
//        );
//        saveAndEmbedFestival(request8);
//
//        //9
//        FestivalFormRequest request9 = new FestivalFormRequest(
//                "2025 제2회 옹장 불꽃축제",
//                PeriodDto.of(
//                        LocalDateTime.of(2025, 10, 11, 10, 0),
//                        LocalDateTime.of(2025, 10, 11, 21, 0)
//                ),
//                AddressDto.of("서울특별시", "마포구", "용강해수욕장 일원"),
//                "참가비 무료",
//                "2025년 10월 11일(금) : 오전 10시 ~ 오후 9시",
//                "용강동 주민자치위원회가 주관하는 화려한 불꽃축제 !\n" +
//                        "한강 위를 수놓는 불꽃과 함께 다양한 공연, 폭죽 터트리기, 별가루 솜사탕부스,\n" +
//                        "주민과 방문객이 모두 어울리는 특별한 가을 밤 축제입니다.",
//                "https://yonggang.letsgo.kr",
//                "010-3535-7788"
//        );
//        saveAndEmbedFestival(request9);
//
//        //10
//        FestivalFormRequest request10 = new FestivalFormRequest(
//                "서강대학교 대동제",
//                PeriodDto.of(
//                        LocalDateTime.of(2025, 5, 12, 0, 0),
//                        LocalDateTime.of(2025, 5, 16, 23, 0)
//                ),
//                AddressDto.of("서울특별시", "마포구", "백범로 35"),
//                "참가비 무료",
//                "5월 12,13,14일 : 12시 ~ 18시\n" +
//                        "5월 15,16일 : 12시 ~ 23시",
//                "서강대학교 2025 대동제 ‘RED:volution’\n" +
//                        "함께하는 모든 순간이 하나의 축제가 됩니다.\n" +
//                        "음악, 먹거리, 다양한 부스가 가득한 캠퍼스 축제에서\n" +
//                        "서강인의 열정을 느껴보세요!",
//                "https://www.sogang.ac.kr",
//                "02-6674-7817"
//        );
//        saveAndEmbedFestival(request10);
//
//        CompanyRegisterRequest Crequest25 = new CompanyRegisterRequest(
//                "C_acc_24", "C_pwd_24",
//                "대흥동 세계 음식",
//                "010-7777-0024",
//                "likelion13thC24@gmail.com",
//                AddressDto.of("서울특별시", "마포구", "염리동 백범로 34"),
//                "한 자리에서 즐기는 글로벌 간식.",
//                "likelion13thC24.com",
//                "이현서",
//                Category.FOOD
//        );
//        savedAndEmbedCompany(Crequest25);
//
//        //26
//        CompanyRegisterRequest Crequest26 = new CompanyRegisterRequest(
//                "C_acc_25", "C_pwd_25",
//                "창전동 꼬치집",
//                "010-7777-0025",
//                "likelion13thC25@gmail.com",
//                AddressDto.of("서울특별시", "마포구", "신수동 광성로 23"),
//                "즉석 화로구이 꼬치.",
//                "likelion13thC25.com",
//                "한지오",
//                Category.FOOD
//        );
//        savedAndEmbedCompany(Crequest26);
//
//        //27
//        CompanyRegisterRequest Crequest27 = new CompanyRegisterRequest(
//                "C_acc_26", "C_pwd_26",
//                "노고산 주점",
//                "010-7777-0026",
//                "likelion13thC26@gmail.com",
//                AddressDto.of("서울특별시", "마포구", "도화동 도화길 6"),
//                "분위기 좋은 저녁 한 잔.",
//                "likelion13thC26.com",
//                "한종윤",
//                Category.FOOD
//        );
//        savedAndEmbedCompany(Crequest27);
//
//        //28
//        CompanyRegisterRequest Crequest28 = new CompanyRegisterRequest(
//                "C_acc_27", "C_pwd_27",
//                "토정로 생과일주스",
//                "010-7777-0027",
//                "likelion13thC27@gmail.com",
//                AddressDto.of("서울특별시", "마포구", "용강동 토정로37길 32"),
//                "착즙으로 살아있는 신선함.",
//                "likelion13thC27.com",
//                "한지윤",
//                Category.FOOD
//        );
//        savedAndEmbedCompany(Crequest28);
//
//        //29
//        CompanyRegisterRequest Crequest29 = new CompanyRegisterRequest(
//                "C_acc_28", "C_pwd_28",
//                "대흥로 소금집",
//                "010-7777-0028",
//                "likelion13thC28@gmail.com",
//                AddressDto.of("서울특별시", "마포구", "대흥동 대흥로20길 28"),
//                "국에 넣어도, 음식에 뿌려도 맛있는 달디단 소금 팝니다.",
//                "likelion13thC28.com",
//                "한예나",
//                Category.FOOD
//        );
//        savedAndEmbedCompany(Crequest29);
//
//        //30
//        CompanyRegisterRequest Crequest30 = new CompanyRegisterRequest(
//                "C_acc_29", "C_pwd_29",
//                "성미산 분식집",
//                "010-7777-0029",
//                "likelion13thC29@gmail.com",
//                AddressDto.of("서울특별시", "마포구", "서교동 와우산로 94"),
//                "따끈한 분식으로 힘내세요!",
//                "likelion13thC29.com",
//                "한서정",
//                Category.FOOD
//        );
//        savedAndEmbedCompany(Crequest30);
//
//        //31
//        CompanyRegisterRequest Crequest31 = new CompanyRegisterRequest(
//                "C_acc_30", "C_pwd_30",
//                "NEON STEP 커버댄스",
//                "010-9898-0030",
//                "likelion13thC30@gmail.com",
//                AddressDto.of("서울특별시", "마포구", "서교동 연습실 A동 3F"),
//                "K-POP 커버 중심 동아리. 군무/포메이션 맞춰 무대 지원합니다.",
//                "neonstep-team.com",
//                "김민준",
//                Category.ART
//        );
//        savedAndEmbedCompany(Crequest31);
//
//        //32
//        CompanyRegisterRequest Crequest32 = new CompanyRegisterRequest(
//                "C_acc_31", "C_pwd_31",
//                "RHYTHM KIDZ 힙합 크루",
//                "010-8787-0031",
//                "likelion13thC31@gmail.com",
//                AddressDto.of("서울특별시", "마포구", "연남동 연습실 B1"),
//                "힙합/스트릿 기반 프리스타일 & 쇼케이스 지원.",
//                "rhythmkidz.kr",
//                "박서연",
//                Category.ART
//        );
//        savedAndEmbedCompany(Crequest32);
//
//        //33
//        CompanyRegisterRequest Crequest33 = new CompanyRegisterRequest(
//                "C_acc_32", "C_pwd_32",
//                "홍익대학교 버스킹 밴드 H-Note",
//                "010-9696-0032",
//                "likelion13thC32@gmail.com",
//                AddressDto.of("서울특별시", "마포구", "서교동 홍대입구역 인근 스튜디오"),
//                "어쿠스틱/록 중심 버스킹 & 축소 밴드 세트 가능.",
//                "h-note.live",
//                "이지후",
//                Category.ART
//        );
//        savedAndEmbedCompany(Crequest33);
//
//        //34
//        CompanyRegisterRequest Crequest34 = new CompanyRegisterRequest(
//                "C_acc_33", "C_pwd_33",
//                "서강대학교 보이스 앙상블",
//                "010-7979-0033",
//                "likelion13thC33@gmail.com",
//                AddressDto.of("서울특별시", "마포구", "신수동 캠퍼스 근처 연습실"),
//                "보컬 합창/하모니 팀. 마이크 세팅형 무대 지원.",
//                "sogang-voice.club",
//                "최도윤",
//                Category.ART
//        );
//        savedAndEmbedCompany(Crequest34);
//
//        //35
//        CompanyRegisterRequest Crequest35 = new CompanyRegisterRequest(
//                "C_acc_34", "C_pwd_34",
//                "WAKKERS 왁킹 팀",
//                "010-9090-0034",
//                "likelion13thC34@gmail.com",
//                AddressDto.of("서울특별시", "마포구", "합정동 연습실 2F"),
//                "왁킹 포즈/암무브 중심. 3~5곡 쇼케이스 가능.",
//                "wakkers.art",
//                "정하린",
//                Category.ART
//        );
//        savedAndEmbedCompany(Crequest35);
//    }
//
//    private void savedAndEmbedCompany(CompanyRegisterRequest request) {
//        Long saved = userCommandService.companyJoin(request);
//        embeddingService.embedCompany(saved, request.getIntroduction());
//    }
//
//    private void saveAndEmbedFestival(FestivalFormRequest request) {
//        Long saved = festivalCommandService.save(1L, request);
//        embeddingService.embedFestival(saved, request.getIntroduction());
//    }
//}
