//package com.festival.everyday.core.common.dummy;
//
//import com.festival.everyday.core.common.domain.Period;
//import com.festival.everyday.core.common.dto.command.AddressDto;
//import com.festival.everyday.core.common.dto.command.PeriodDto;
//import com.festival.everyday.core.festival.dto.request.FestivalFormRequest;
//import com.festival.everyday.core.festival.service.FestivalCommandService;
//import com.festival.everyday.core.image.service.ImageCommandService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//
//@Order(2)
//@Component
//@RequiredArgsConstructor
//public class DummyFestivalRunner implements CommandLineRunner {
//
//    private final FestivalCommandService festivalCommandService;
//    private final ImageCommandService imageCommandService;
//
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        LocalDateTime beginPast = LocalDateTime.of(2025, 7, 10, 14, 30);
//        LocalDateTime endPast = LocalDateTime.of(2025, 7, 15, 15, 30);
//
//        LocalDateTime beginFuture = LocalDateTime.of(2025, 10, 1, 14, 30);
//        LocalDateTime endFuture = LocalDateTime.of(2025, 10, 5, 15, 30);
//
//        // 더미 축제를 저장한다
//        /**
//         * 1 ~ 45: 진행
//         * 46 ~ 60: 종료
//         * id = 60 (acc 59)는 종료된 행사에 지원자가 존재한다.
//         */
//        for (int i = 0; i < 60; i++) {
//            FestivalFormRequest request = new FestivalFormRequest(
//                    "F_name_" + i,
//                    i < 45 ? PeriodDto.of(beginFuture, endFuture) : PeriodDto.of(beginPast, endPast),
//                    AddressDto.of("F_city_" + i, "F_district_" + i, "F_detail_" + i),
//                    "F_fee_" + i,
//                    "F_time_" + i,
//                    "F_intro_" + i,
//                    "F_link_" + i,
//                    "F_tel_" + i
//            );
//
//            // 1인 2축제
//            festivalCommandService.save((long) (61 + (i % 30)), request);
//        }
//
//        for (int i = 0; i < 10; i++) {
//            FestivalFormRequest request = new FestivalFormRequest(
//                    "Fx_name_" + i,
//                    PeriodDto.of(beginFuture, endFuture),
//                    AddressDto.of("Fx_city_" + i, "Fx_district_" + i, "Fx_detail_" + i),
//                    "Fx_fee_" + i,
//                    "Fx_time_" + i,
//                    "Fx_intro_" + i,
//                    "Fx_link_" + i,
//                    "Fx_tel_" + i
//            );
//
//            festivalCommandService.save(61L, request);
//        }
//
//    }
//}
