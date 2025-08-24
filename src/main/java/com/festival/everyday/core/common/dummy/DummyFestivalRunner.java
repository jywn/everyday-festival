package com.festival.everyday.core.common.dummy;

import com.festival.everyday.core.ai.service.EmbeddingService;
import com.festival.everyday.core.common.domain.Period;
import com.festival.everyday.core.common.dto.command.AddressDto;
import com.festival.everyday.core.common.dto.command.PeriodDto;
import com.festival.everyday.core.festival.dto.request.FestivalFormRequest;
import com.festival.everyday.core.festival.service.FestivalCommandService;
import com.festival.everyday.core.image.service.ImageCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Order(2)
@Component
@RequiredArgsConstructor
public class DummyFestivalRunner implements CommandLineRunner {

    private final EmbeddingService embeddingService;
    private final FestivalCommandService festivalCommandService;
    private final ImageCommandService imageCommandService;


    @Override
    public void run(String... args) throws Exception {

        LocalDateTime beginPast = LocalDateTime.of(2025, 7, 10, 14, 30);
        LocalDateTime endPast = LocalDateTime.of(2025, 7, 15, 15, 30);

        LocalDateTime beginFuture = LocalDateTime.of(2025, 10, 1, 14, 30);
        LocalDateTime endFuture = LocalDateTime.of(2025, 10, 5, 15, 30);

        // 더미 축제를 저장한다
        /**
         * 1 ~ 45: 진행
         * 46 ~ 60: 종료
         */
        //1
        FestivalFormRequest request1 = new FestivalFormRequest(
                "멋쟁이사자처럼 13기 전국연합 해커톤 대회",
                PeriodDto.of(
                        LocalDateTime.of(2025, 8, 25, 12, 0),
                        LocalDateTime.of(2025,8,26,6,0)),
                AddressDto.of("서울특별시", "마포구", "연세로 19 앞 광장"),
                "참가비 무료",
                "25일 점심 12시부터 26일 아침 6시까지 진행",
                "국내 최대 규모의 IT 창s업 동아리 네트워크가 모여, 무박 2일 동안 아이디어를 ‘실제 서비스’로 구현하는 전국 연합 해커톤입니다.\n"
                        + "팀원과 함께 한계를 넘고, 현장에서 성장과 네트워킹을 경험하세요.\n"
                        + "대회장에 오시면 다들 심심하지 않게 여러 오락시설들과 푸드 트럭도 있습니다.\n",
                "https://likelion.net/",
                "010-9411-2750"
        );
        saveAndEmbedFestival(91L, request1);


        //2
        FestivalFormRequest request2 = new FestivalFormRequest(
                "멋쟁이사자처럼 12기 전국연합 해커톤 대회",
                PeriodDto.of(
                        LocalDateTime.of(2024, 8, 6, 12, 0),
                        LocalDateTime.of(2024,8,7,6,0)),
                AddressDto.of("서울특별시", "마포구", "백범로 35"),
                "참가비 무료",
                "6일 점심 12시부터 7일 아침 6시까지 진행",
                "전공·학교를 넘어 모인 동료들과 팀을 꾸려 문제를 정의하고 해결합니다.\n"
                +"밤새 협업하며 프로토타입을 완성하고, 다음 스텝으로 이어질 파트너와 멘토를 만나보세요.\n"
                +"하루가 지루하지 않도록 여러 학생 밴드와 춤 동아리를 섭외할 예정입니다!",
                "https://likelion.net/",
                "010-5656-3434"
        );

        saveAndEmbedFestival(91L, request2);

        //3
        FestivalFormRequest request3 = new FestivalFormRequest(
                "멋쟁이사자처럼 13기 신촌 멋쟁이사자처럼 대학 연합 데모데이",
                PeriodDto.of(
                        LocalDateTime.of(2025, 11, 18, 12, 0),
                        LocalDateTime.of(2025,11,19,18,0)),
                AddressDto.of("서울특별시", "마포구", "백범로 35"),
                "참가비 무료",
                "18일 : 오후 12시 ~ 오후 21시\n"+"19일 : 오전 10시 ~ 오후 18시",
                "멋쟁이사자처럼에서 열심히 가꿔온 실력을 마음껏 표출하세요!\n"
                +"마지막 프로젝트인만큼 힘내시라고 맛있는 음식들이 즐비할 것입니다!",
                "https://sglikelion.com",
                "010-5959-3399"
        );

        saveAndEmbedFestival(91L, request3);

        //4
        FestivalFormRequest request4 = new FestivalFormRequest(
                "2025 염리동 소금 축제",
                PeriodDto.of(
                        LocalDateTime.of(2025, 10, 9, 10, 0),
                        LocalDateTime.of(2025,10,9,16,0)),
                AddressDto.of("서울특별시", "마포구", "대흥로 20길 28 마포아트센터 광장 앞"),
                "참가비 1000원",
                "10월 9일 오전 10시 ~ 오후 4시",
                "염리동의 이야기와 소금의 매력을 한자리에서! 푸드트럭,체험,라이브 공연으로 하루를 꽉 채우는 로컬 페스티벌",
                "yeomrisaltfestival.com",
                "010-5111-8181"
        );
        saveAndEmbedFestival(92L, request4);

        //5
        FestivalFormRequest request5 = new FestivalFormRequest(
                "2026 염리동 눈꽃 축제",
                PeriodDto.of(
                        LocalDateTime.of(2026, 1, 20, 0, 0),
                        LocalDateTime.of(2026,1,29,18,0)),
                AddressDto.of("서울특별시", "마포구", "백범로 35 서강대학교 후문 거리"),
                "참가비 무료",
                "1월 26,27일 : 오전 10시 ~ 오후 6시\n"+
                "그 외 : 오전 10시 ~ 오후 9시",
                "염리동에도 새하얀 눈의 축제가 찾아왔어요! 새하얀 눈과 따뜻하고 다양한 먹거리를 체험하세요",
                "snowfestival.com",
                "010-1313-5678"
        );
        saveAndEmbedFestival(92L, request5);
        //6
        FestivalFormRequest request6 = new FestivalFormRequest(
                "2023 염리동 소금 축제",
                PeriodDto.of(
                        LocalDateTime.of(2023, 9, 23, 10, 30),
                        LocalDateTime.of(2023,9,23,16,0)),
                AddressDto.of("서울특별시", "마포구", "대흥로 20길 28 마포아트센터 광장 앞"),
                "참가비 1000원",
                "10월 9일 오전 10시 ~ 오후 4시",
                "한 줌의 소금에서 시작되는 특별한 체험, 공연, 포토존까지 염리동을 가득 담았습니다.",
                "yeomrisaltfestival.com",
                "010-5111-8181"
        );
        saveAndEmbedFestival(92L, request6);

        //7
        FestivalFormRequest request7 = new FestivalFormRequest(
                "제1회 용강동 마을축제 '토정아 놀자!'",
                PeriodDto.of(
                        LocalDateTime.of(2023, 11, 1, 10, 0),
                        LocalDateTime.of(2023, 11, 1, 18, 0)
                ),
                AddressDto.of("서울특별시", "마포구", "도화소어린이공원"),
                "참가비 무료",
                "2024년 11월 1일 : 오전 10시 ~ 오후 18시",
                "용강동 주민자치위원회가 주관하는 마을 축제 '토정아 놀자!' 🎉\n" +
                        "아이부터 어르신까지 주민 모두가 함께 즐기는 화합의 장입니다.\n" +
                        "다양한 공연, 먹거리, 토정이랑 술래잡기 체험이 준비되어 있으며,\n" +
                        "이웃과 함께 즐겁게 어울릴 수 있는 따뜻한 마을 축제입니다.",
                "https://yonggang.letsgo.kr",
                "010-3535-7788"
        );
        saveAndEmbedFestival(93L, request7);

        //8
        FestivalFormRequest request8 = new FestivalFormRequest(
                "2024 용강동 가면무도화 축제",
                PeriodDto.of(
                        LocalDateTime.of(2024, 9, 27, 10, 0),
                        LocalDateTime.of(2024, 10, 6, 18, 0)
                ),
                AddressDto.of("서울특별시", "마포구", "용강관무용공원 일대"),
                "참가비 무료",
                "9월 27일(금) ~ 10월 6일(일)\n" +
                        "매일 오전 10시 ~ 오후 18시",
                "용강동 주민자치위원회가 주관하는 화려한 가면무도화 축제!\n" +
                        "다양한 전통 공연, 가면 퍼레이드, 문화 체험과 함께\n" +
                        "주민과 방문객 모두가 즐길 수 있는 특별한 가을 축제입니다.\n" +
                        "여러 캠페인과 체험 부스 환영입니다!",
                "https://yonggang.letsgo.kr",
                "010-3535-7788"
        );
        saveAndEmbedFestival(93L, request8);

        //9
        FestivalFormRequest request9 = new FestivalFormRequest(
                "2025 제2회 옹장 불꽃축제",
                PeriodDto.of(
                        LocalDateTime.of(2025, 10, 11, 10, 0),
                        LocalDateTime.of(2025, 10, 11, 21, 0)
                ),
                AddressDto.of("서울특별시", "마포구", "용강해수욕장 일원"),
                "참가비 무료",
                "2025년 10월 11일(금) : 오전 10시 ~ 오후 9시",
                "용강동 주민자치위원회가 주관하는 화려한 불꽃축제 !\n" +
                        "한강 위를 수놓는 불꽃과 함께 다양한 공연, 폭죽 터트리기, 별가루 솜사탕부스,\n" +
                        "주민과 방문객이 모두 어울리는 특별한 가을 밤 축제입니다.",
                "https://yonggang.letsgo.kr",
                "010-3535-7788"
        );
        saveAndEmbedFestival(93L, request9);

        //10
        FestivalFormRequest request10 = new FestivalFormRequest(
                "서강대학교 대동제",
                PeriodDto.of(
                        LocalDateTime.of(2025, 5, 12, 0, 0),
                        LocalDateTime.of(2025, 5, 16, 23, 0)
                ),
                AddressDto.of("서울특별시", "마포구", "백범로 35"),
                "참가비 무료",
                "5월 12,13,14일 : 12시 ~ 18시\n" +
                        "5월 15,16일 : 12시 ~ 23시",
                "서강대학교 2025 대동제 ‘RED:volution’\n" +
                        "함께하는 모든 순간이 하나의 축제가 됩니다.\n" +
                        "음악, 먹거리, 다양한 부스가 가득한 캠퍼스 축제에서\n" +
                        "서강인의 열정을 느껴보세요!",
                "https://www.sogang.ac.kr",
                "02-6674-7817"
        );
        saveAndEmbedFestival(94L, request10);

        //11
        FestivalFormRequest request11 = new FestivalFormRequest(
                "S.H.O.C.K 가을배틀",
                PeriodDto.of(
                        LocalDateTime.of(2025, 9, 25, 12, 0),
                        LocalDateTime.of(2025, 9, 26, 18, 0)
                ),
                AddressDto.of("서울특별시", "마포구", "신촌 빨간잠망경 앞 광장"),
                "참가비 5000원",
                "9월 25,26일 : 오후 12시 ~ 오후 6시",
                "안녕하세요! 서강대학교 중앙스트릿댄스동아리 S.H.O.C.K에서 배틀을 개최하게 되었습니다!\n" +
                        "FREESTYLE HIPHOP/LOCKING/OPEN STYLE \n" +
                        "JUDGES \n" +
                        "YENA @_yena.lee\n" +
                        "YoungJae @genghisyoon\n" +
                        "JINGYU @_williamjang\n" +
                        "GUESTSHOW(추후 공개)\n" +
                        "자유롭게 오셔서 춤 배틀의 매력에 흠뻑 빠져보세요!",
                "www.shock.ac.kr",
                "010-1838-5238"
        );
        saveAndEmbedFestival(95L, request11);

        //12
        FestivalFormRequest request12 = new FestivalFormRequest(
                "S.H.O.C.K 봄배틀",
                PeriodDto.of(
                        LocalDateTime.of(2025, 5, 30, 19, 0),
                        LocalDateTime.of(2025, 5, 30, 23, 00)
                ),
                AddressDto.of("서울특별시", "마포구", "백범로 35 서강대학교 스티브김아트홀"),
                "참가비 5000원",
                "5월 30일 : 오후 7시 ~ 오후 11시",
                "안녕하세요! 서강대학교 중앙스트릿댄스동아리 S.H.O.C.K에서 배틀을 개최하게 되었습니다!\n" +
                        "FREESTYLE HIPHOP/LOCKING/OPEN STYLE \n" +
                        "JUDGES \n" +
                        "YENA @_yena.lee\n" +
                        "YoungJae @genghisyoon\n" +
                        "JINGYU @_williamjang\n" +
                        "GUESTSHOW(추후 공개)\n" +
                        "자유롭게 오셔서 춤 배틀의 매력에 흠뻑 빠져보세요!",
                "www.shock.ac.kr",
                "010-9338-5438"
        );
        saveAndEmbedFestival(95L, request12);

        //13
        FestivalFormRequest request13 = new FestivalFormRequest(
                "SHOCKCASE",
                PeriodDto.of(
                        LocalDateTime.of(2025, 11, 2, 19, 0),
                        LocalDateTime.of(2025, 11, 2, 23, 00)
                ),
                AddressDto.of("서울특별시", "마포구", "연세로 빨간잠망경 광장 앞"),
                "참가비 무료",
                "11월 2일 : 오후 7시 ~ 오후 11시",
                "오랜만입니다. 춤,노래 좀 한다 싶으면 다 들어와\n" +
                        "FREESTYLE HIPHOP/LOCKING/OPEN STYLE \n" +
                        "JUDGES \n" +
                        "YENA @_yena.lee\n" +
                        "YoungJae @genghisyoon\n" +
                        "JINGYU @_williamjang\n" +
                        "GUESTSHOW(추후 공개)\n" +
                        "자유롭게 오셔서 춤,노래를 불러보세요!",
                "www.shock.ac.kr",
                "010-1341-5523"
        );
        saveAndEmbedFestival(95L, request13);

        //14
        FestivalFormRequest request14 = new FestivalFormRequest(
                "XSTARC 동아리거리제",
                PeriodDto.of(
                        LocalDateTime.of(2025, 11, 2, 19, 0),
                        LocalDateTime.of(2025, 11, 5, 23, 0)
                ),
                AddressDto.of("서울특별시", "마포구", "월드컵로 17"),
                "참가비 무료",
                "11월 2,3,4,5일 : 오후 7시 ~ 오후 11시",
                "XSTARC에서 주관하는 댄스/밴드/춤 동아리를 개최하게 되었습니다.\n" +
                "여러 오락 시설들과 댄스/밴드/춤 동아리 지원 부탁드립니다!!\n" +
                "여러 캠페인과 체험 부스 환영입니다!",
                "www.xstarc.ac.kr",
                "010-4238-1648"
        );
        saveAndEmbedFestival(95L, request14);

        //15
        FestivalFormRequest request15 = new FestivalFormRequest(
                "날벼락",
                PeriodDto.of(
                        LocalDateTime.of(2024, 12, 5, 19, 0),
                        LocalDateTime.of(2024, 12, 5, 23, 59)
                ),
                AddressDto.of("서울특별시", "마포구", "와우산로 156(서교동) 지하"),
                "참가비 3000원",
                "12월 5일 : 오후 7시 ~ 오전 12시",
                "안녕하세요! 서강대학교 락밴드 축제 개최자 XstarC입니다.\n" +
                        "다같이 락밴드의 흥에 빠져보아요! 많은 락밴드 및 노래 그룹 참여 부탁드립니다!",
                "www.xstarc.ac.kr",
                "010-4211-1115"
        );
        saveAndEmbedFestival(96L, request15);

        //16
        FestivalFormRequest request16 = new FestivalFormRequest(
                "XSTARC 48TH SELIST",
                PeriodDto.of(
                        LocalDateTime.of(2025, 11, 7, 21, 0),
                        LocalDateTime.of(2025, 11, 7, 23, 59)
                ),
                AddressDto.of("서울특별시", "마포구", "백범로 35 우정관 지하"),
                "참가비 무료",
                "11월 7일 : 오후 9시 ~ 오전 12시",
                "안녕하세요! 서강대학교 락밴드 축제 개최자 XstarC입니다.\n" +
                        "다같이 락밴드의 흥에 빠져보아요! 많은 음식 및 먹거리도 같이 진행될 예정입니다!\n" +
                "다같이 맛있는 음식들을 먹으며 락밴드 음악을 들어볼까요? 렛츠고~",
                "www.xstarc.ac.kr",
                "010-4211-1115"
        );
        saveAndEmbedFestival(96L, request16);

        //17
        FestivalFormRequest request17 = new FestivalFormRequest(
                "2025 대흥이네 마을축제",
                PeriodDto.of(
                        LocalDateTime.of(2024, 9, 28, 10, 0),
                        LocalDateTime.of(2024, 9, 28, 18, 0)
                ),
                AddressDto.of("서울특별시", "마포구", "대흥동 주민센터 광장"),
                "참가비 무료",
                "2024년 제6회 대흥이네 마을축제\n" +
                        "일시: 9월 28일(토) 오전 10시 ~ 오후 6시\n" +
                        "장소: 마포구 대흥동주민센터 광장, 우리마포복지관",
                "대흥동 주민자치위원회가 주관하는 마을 축제입니다.\n" +
                        "매년 가을 열리며 주민과 지역 대학생들이 함께하는 화합의 장입니다.\n" +
                        "다채로운 공연, 먹거리, 체험 부스가 준비되어 있으며\n" +
                        "주차장 관리 단기 근로자도 같이 모집합니다.",
                "https://daeheungtownfestival.com",
                "02-7415-9874"
        );
        saveAndEmbedFestival(97L, request17);

        //18
        FestivalFormRequest request18 = new FestivalFormRequest(
                "2024 대흥마을 수박축제",
                PeriodDto.of(
                        LocalDateTime.of(2024, 6, 15, 10, 0),
                        LocalDateTime.of(2024, 6, 16, 18, 0)
                ),
                AddressDto.of("서울특별시", "마포구", "대흥동 주민센터 광장"),
                "참가비 무료",
                "15일 : 오전 10시 ~ 오후 18시\n" +
                        "16일 : 오전 10시 ~ 오후 18시",
                "대흥동 주민자치위원회 주관으로 열리는 대흥마을 수박축제!\n" +
                        "주민과 지역 대학생들이 함께 즐기는 화합의 장입니다.\n" +
                        "다채로운 공연, 수박 빨리 먹기, 수박 페이스 페인팅 부스가 준비되어 있으며\n" +
                        "시원한 수박과 함께 여름을 만끽하세요!",
                "www.watermelondaeheung.com",
                "010-5764-1231"
        );
        saveAndEmbedFestival(97L, request18);

        //19
        FestivalFormRequest request19 = new FestivalFormRequest(
                "2025 대흥마을 머드축제",
                PeriodDto.of(
                        LocalDateTime.of(2025, 10, 9, 10, 0),
                        LocalDateTime.of(2025, 10, 27, 18, 0)
                ),
                AddressDto.of("서울특별시", "마포구", "대흥동 주민센터 광장"),
                "참가비 무료",
                "10월 9일 ~ 10월 27일\n" +
                        "매일 오전 10시 ~ 오후 18시",
                "대흥동 주민자치위원회가 주관하는 머드축제!\n" +
                        "머드 체험, 신나는 공연, 머드로 도자기 공예 부스가 준비되어 있습니다.\n" +
                        "온 가족과 함께 즐기는 특별한 가을 축제를 놓치지 마세요!",
                "www.daeheungtownfestival.com",
                "010-2242-1667"
        );
        saveAndEmbedFestival(97L, request19);

        //20
        FestivalFormRequest request20 = new FestivalFormRequest(
                "2024 대흥대추축제",
                PeriodDto.of(
                        LocalDateTime.of(2024, 10, 11, 10, 0),
                        LocalDateTime.of(2024, 10, 20, 18, 0)
                ),
                AddressDto.of("서울특별시", "마포구", "대흥동 주민센터 광장"),
                "참가비 무료",
                "10월 11일 ~ 10월 20일\n" +
                        "매일 오전 10시 ~ 오후 18시",
                "대흥동 주민자치위원회가 주관하는 가을 대추축제!\n" +
                        "싱싱한 대추와 함께 다양한 먹거리, 대추 주스 만들기 체험, 공연이 준비되어 있습니다.\n" +
                        "지역 주민과 방문객이 함께 즐기는 화합의 장, 대흥대추축제에서 가을의 맛과 즐거움을 느껴보세요!",
                "www.daeheungtownfestival.com",
                "010-1423-4576"
        );
        saveAndEmbedFestival(97L, request20);

        //21
        FestivalFormRequest request21 = new FestivalFormRequest(
                "2025 잔다리축제 (Zandari Festa)",
                PeriodDto.of(
                        LocalDateTime.of(2025, 11, 17, 12, 0),
                        LocalDateTime.of(2025, 11, 19, 21, 0)
                ),
                AddressDto.of("서울특별시", "마포구", "잔다리 공원"),
                "참가비 무료",
                "11월 17일(금) ~ 11월 19일(일)\n" +
                        "매일 오후 12시 ~ 오후 21시",
                "잔다리가 주관하는 지역문화축제, ‘잔다리축제 (Zandari Festa)’!\n" +
                        "다양한 인디 공연, 문화체험, 푸드부스, 커뮤니티 프로그램이 가득한 축제입니다.\n" +
                        "주민과 방문객이 함께 즐기는 홍대 거리 문화의 향연으로 여러분을 초대합니다.",
                "https://zandari.go.kr",
                "010-2424-8945"
        );
        saveAndEmbedFestival(98L, request21);

        //22
        FestivalFormRequest request22 = new FestivalFormRequest(
                "2025 잔다리페스타 (Zandari Festa)",
                PeriodDto.of(
                        LocalDateTime.of(2025, 9, 1, 12, 0),
                        LocalDateTime.of(2025, 9, 3, 21, 0)
                ),
                AddressDto.of("서울특별시", "마포구", "홍대 일대"),
                "참가비 무료",
                "2025년 9월 1일(월) ~ 9월 3일(수)\n" +
                        "매일 오후 12시 ~ 오후 9시",
                "잔다리 주최로 열리는 2025 잔다리페스타!\n" +
                        "홍대 일대에서 펼쳐지는 다양한 인디공연과 퍼포먼스,\n" +
                        "거리 예술, 먹거리 부스까지 함께 즐길 수 있는 대표적인 지역 축제입니다.\n" +
                        "음악과 문화, 그리고 사람들의 열정을 만날 수 있는 3일간의 축제에 여러분을 초대합니다.\n" +
                        "여러 캠페인과 체험 부스 환영입니다!",
                "https://zandari.go.kr",
                "010-2424-8945"
        );
        saveAndEmbedFestival(98L, request22);

        //23
        FestivalFormRequest request23 = new FestivalFormRequest(
                "2025 마포국제만화축제",
                PeriodDto.of(
                        LocalDateTime.of(2025, 10, 19, 10, 0),
                        LocalDateTime.of(2025, 10, 27, 21, 0)
                ),
                AddressDto.of("서울특별시", "마포구", "잔다리 박물관"),
                "참가비 무료",
                "2025년 10월 19일(일) ~ 10월 27일(월)\n" +
                        "매일 오전 10시 ~ 오후 9시",
                "마포구 잔다리가 주최하는 2025 마포국제만화축제!\n" +
                        "만화, 웹툰, 애니메이션 등 다양한 콘텐츠와 함께하는 글로벌 만화 축제입니다.\n" +
                        "작가와 팬이 직접 소통하는 프로그램, 네컷만화 그리기 체험부스, 먹거리까지!\n" +
                        "마포 홍대 일대에서 펼쳐지는 특별한 만화의 세계에 여러분을 초대합니다.",
                "https://zandari.go.kr",
                "010-2424-8945"
        );
        saveAndEmbedFestival(98L, request23);

        //24
        FestivalFormRequest request24 = new FestivalFormRequest(
                "연남위크 (Yeonnam Week)",
                PeriodDto.of(
                        LocalDateTime.of(2017, 7, 20, 10, 0),
                        LocalDateTime.of(2017, 7, 23, 22, 0)
                ),
                AddressDto.of("서울특별시", "마포구", "연남동 경의선 숲길 및 일대"),
                "참가비 무료",
                "2017년 7월 20일(목) ~ 7월 23일(일)\n" +
                        "매일 오전 10시 ~ 오후 10시",
                "연남동에서 열린 지역 문화 축제 '연남위크'!\n" +
                        "주민, 아티스트, 상인들이 함께 참여하여 다양한 문화 행사,\n" +
                        "프리마켓, 전시, 공연, 커뮤니티 프로그램이 진행됩니다.\n" +
                        "지역과 예술이 어우러지는 특별한 4일간의 축제입니다.",
                "https://yeonnamfestival.go.kr",
                "010-7865-9999"
        );
        saveAndEmbedFestival(99L, request24);

        //25
        FestivalFormRequest request25 = new FestivalFormRequest(
                "2025 연남 막걸리 축제",
                PeriodDto.of(
                        LocalDateTime.of(2025, 10, 6, 12, 0),
                        LocalDateTime.of(2025, 10, 6, 20, 0)
                ),
                AddressDto.of("서울특별시", "마포구", "서강연남포차"),
                "참가비 무료",
                "2025년 10월 6일(월)\n" +
                        "오후 12시 ~ 오후 8시",
                "연남동 주민자치위원회가 주관하는 '연남 막걸리 축제' \n" +
                        "주민과 방문객이 함께 모여 막걸리와 다양한 먹거리를 즐기는 축제입니다.\n" +
                        "지역 공동체의 화합을 위한 행사로, 다양한 먹거리, 막걸리 만들기 부스, 막걸리 페이스 페인팅 체험도 마련되어 있습니다.\n" +
                        "가을의 정취를 느끼며 막걸리와 함께 특별한 하루를 즐겨보세요!",
                "https://yeonnamfestival.go.kr",
                "010-7865-9999"
        );
        saveAndEmbedFestival(99L, request25);

        //26
        FestivalFormRequest request26 = new FestivalFormRequest(
                "연남동 주민화합대축제",
                PeriodDto.of(
                        LocalDateTime.of(2024, 10, 5, 10, 0),
                        LocalDateTime.of(2024, 10, 5, 18, 0)
                ),
                AddressDto.of("서울특별시", "마포구", "연남동 경의선 숲길공원"),
                "참가비 무료",
                "2024년 10월 5일(토)\n" +
                        "오전 10시 ~ 오후 6시",
                "연남동축제추진위원회가 주최하는 주민화합대축제 🎉\n" +
                        "지역 주민들의 화합과 소통을 위한 행사로,\n" +
                        "문화 체험, 프리마켓, 주민 참여 행사, 공연 등 다양한 프로그램이 마련됩니다.\n" +
                        "주민 주도로 운영되며, 지역 공동체의 행복과 소통을 함께 만들어가는 축제입니다.",
                "https://yeonnamfestival.go.kr",
                "010-7865-9999"
        );
        saveAndEmbedFestival(99L, request26);

        //27
        FestivalFormRequest request27 = new FestivalFormRequest(
                "2025 서울 연합동아리 페스티벌 - LEAP",
                PeriodDto.of(
                        LocalDateTime.of(2025, 2, 19, 12, 0),
                        LocalDateTime.of(2025, 3, 2, 20, 0)
                ),
                AddressDto.of("서울특별시", "마포구", "서강대 일대"),
                "참가비 무료",
                "2025년 2월 19일(금) ~ 3월 2일(일)\n" +
                        "오후 12시 ~ 오후 8시",
                "서울 연합 동아리들이 주최하는 'LEAP 페스티벌' 🎉\n" +
                        "대학생들의 열정과 창의력을 모아 기획, 제작, 공연, 전시까지!\n" +
                        "서울 전역 대학 동아리들이 모여 소통하고 협력하는 특별한 교류의 장입니다.\n" +
                        "마케팅, 광고, 음악, 예술 등 다양한 분야의 프로그램이 진행됩니다. 여러 캠페인과 체험 부스 환영입니다!",
                "https://seoul_dongari.co.kr",
                "010-3432-9877"
        );
        saveAndEmbedFestival(100L, request27);

        //28
        FestivalFormRequest request28 = new FestivalFormRequest(
                "2025 대학생 풍물패 축제",
                PeriodDto.of(
                        LocalDateTime.of(2025, 5, 10, 13, 0),
                        LocalDateTime.of(2025, 6, 10, 20, 0)
                ),
                AddressDto.of("서울특별시", "마포구", "서강대학교 청년광장 일대"),
                "참가비 무료",
                "2025년 5월 10일(토) ~ 6월 10일(일)\n" +
                        "매일 오후 1시 ~ 오후 8시",
                "대학생 풍물동아리들이 함께 모여 열리는 '풍물패 축제' 🎶\n" +
                        "전통 악기 연주, 신명나는 사물놀이, 마당놀이 공연 등이 펼쳐집니다.\n" +
                        "풍물을 잘 몰라도 누구나 참여 가능하며,\n" +
                        "흥과 함께 공동체 문화를 경험할 수 있는 자리입니다.\n" +
                        "많은 푸드트럭들과 캠페인 지원 부탁드립니다!",
                "https://seoul_dongari.co.kr",
                "010-3432-9877"
        );
        saveAndEmbedFestival(100L, request28);

        //29
        FestivalFormRequest request29 = new FestivalFormRequest(
                "2025 다시꿈꾸는 마포",
                PeriodDto.of(
                        LocalDateTime.of(2025, 11, 3, 10, 0),
                        LocalDateTime.of(2025, 11, 19, 18, 0)
                ),
                AddressDto.of("서울특별시", "마포구", "마포아트센터"),
                "참가비 5000원",
                "11월 7,8,14,15일 오전 10시 ~ 오후 6시 \n" +
                        "그 외 오전 10시 ~ 오후 10시",
                "마포 연합동아리가 주최하는 다시 꿈꾸는 마포!\n" +
                        "여러 동아리들과 소통을 위한 행사로,\n" +
                        "춤(댄스), 밴드, 노래 동아리와, 플리마켓, 주민 참여 행사, 공연 등 다양한 프로그램이 마련됩니다.\n" +
                        "지역 공동체의 화합을 위한 행사로, 다양한 먹거리, 페이스 페인팅 체험도 마련할 예정입니다.",
                "https://yeonnamfestival.go.kr",
                "010-7865-9999"
        );
        saveAndEmbedFestival(100L, request29);

    }

    private void saveAndEmbedFestival(Long holderId, FestivalFormRequest request) {
        Long saved = festivalCommandService.save(holderId, request);
        embeddingService.embedFestival(saved, request.getIntroduction());
    }
}
