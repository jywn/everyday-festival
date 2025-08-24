//package com.festival.everyday.core.common.dummy;
//
//import com.festival.everyday.core.interest.service.InterestCommandService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//@Order(4)
//@Component
//@Slf4j
//@RequiredArgsConstructor
//public class DummyInterestRunner implements CommandLineRunner {
//
//    private final InterestCommandService interestCommandService;
//
//    /**
//     * holderId(userId)가 61이면 festivalId 1,31 만듦
//     * interest를 festivalId를 for문으로 1~60을 돌리면 같은 holder가 주최한 두 개의 festival이 같은 company들을 관심 보내기 때문에 조절해줍니다.
//     */
//    @Override
//    public void run(String... args) throws Exception {
//        for (int festivalId = 1; festivalId <= 60; festivalId++) {
//            int[] companyIds;
//
//            /**
//             * festivalId (1~30)
//             * userId 5 -> companyId(userId) 5, 15, 25 관심 보내기
//             * userId 10 -> companyId(userId) 10, 20, 30 관심 보내기
//             */
//            if (festivalId <= 30) {
//                companyIds = new int[]{
//                        wrapCompany(festivalId),
//                        wrapCompany(festivalId + 10),
//                        wrapCompany(festivalId + 20)
//                };
//            }
//            /**
//             * festivalId (31~60)
//             * userId 31 -> companyId(userId) 10, 20, 30 관심 보내기
//             * userId 32 -> companyId(userId) 9, 19, 29 관심 보내기
//             * userId 50 -> companyId(userId) 1, 11, 21 관심 보내기
//             */
//            else {
//                int base = 61 - festivalId;
//                companyIds = new int[]{
//                        wrapCompany(base),
//                        wrapCompany(base - 20),
//                        wrapCompany(base - 10)
//                };
//            }
//
//            for (int companyId : companyIds) {
//                log.info("festival {} -> company {}", festivalId, companyId);
//                interestCommandService.createInterest(
//                        (long) festivalId,
//                        (long) companyId
//                );
//            }
//        }
//    }
//
//    private int wrapCompany(int id) {
//        if (id <= 0) return id + 30;
//        if (id > 30) return id - 30;
//        return id;
//    }
//}
