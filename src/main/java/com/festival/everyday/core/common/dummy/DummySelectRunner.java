//package com.festival.everyday.core.common.dummy;
//
//import com.festival.everyday.core.application.service.ApplicationCommandService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//@Order(8)
//@Component
//@RequiredArgsConstructor
//public class DummySelectRunner implements CommandLineRunner {
//
//    private final ApplicationCommandService applicationCommandService;
//
//    @Override
//    public void run(String... args) throws Exception {
//        //C_acc_0의 지원 4개를 수락처리한다. 지원 4개를 거절처리한다.
//        applicationCommandService.acceptApplication(1L);
//        applicationCommandService.acceptApplication(11L);
//        applicationCommandService.acceptApplication(21L);
//        applicationCommandService.acceptApplication(31L);
//        applicationCommandService.denyApplication(41L);
//        applicationCommandService.denyApplication(51L);
//        applicationCommandService.denyApplication(61L);
//        applicationCommandService.denyApplication(71L);
//
//        //L_acc_0의 지원 2개를 수락한다. 지원 2개를 거절한다.
//        applicationCommandService.denyApplication(226L);
//        applicationCommandService.denyApplication(231L);
//        applicationCommandService.acceptApplication(236L);
//        applicationCommandService.acceptApplication(241L);
//
//        //H_acc_0이 업체를 1개씩 수락, 거절. 근로자를 1개씩 수락, 거절.
//        applicationCommandService.acceptApplication(2L);
//        applicationCommandService.acceptApplication(6L);
//        applicationCommandService.denyApplication(3L);
//        applicationCommandService.denyApplication(7L);
//
//        //60번 행사는 종료됐으며, 복수의 지원자를 가진다.
//        applicationCommandService.acceptApplication(301L);
//        applicationCommandService.acceptApplication(302L);
//        applicationCommandService.denyApplication(303L);
//        applicationCommandService.denyApplication(304L);
//
//        applicationCommandService.acceptApplication(306L);
//        applicationCommandService.acceptApplication(307L);
//        applicationCommandService.denyApplication(308L);
//        applicationCommandService.denyApplication(309L);
//
//        //60번 행사는 종료됐으며, 복수의 지원자를 가지고 이들은 모두 선택된다.
//        applicationCommandService.acceptApplication(310L);
//        applicationCommandService.acceptApplication(311L);
//        applicationCommandService.acceptApplication(312L);
//        applicationCommandService.acceptApplication(313L);
//        applicationCommandService.acceptApplication(314L);
//        applicationCommandService.acceptApplication(315L);
//        applicationCommandService.acceptApplication(316L);
//
//        applicationCommandService.acceptApplication(317L);
//        applicationCommandService.acceptApplication(318L);
//        applicationCommandService.acceptApplication(319L);
//        applicationCommandService.acceptApplication(320L);
//        applicationCommandService.acceptApplication(321L);
//        applicationCommandService.acceptApplication(322L);
//        applicationCommandService.acceptApplication(323L);
//
//    }
//}
