package com.festival.everyday.core.common.dummy;

import com.festival.everyday.core.common.dto.command.AddressDto;
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

@Order(1)
@Component
@RequiredArgsConstructor
public class DummyUserRunner implements CommandLineRunner {

    private final UserCommandService userCommandService;

    @Override
    public void run(String... args) throws Exception {
        // 업체 더미 데이터 저장
        for (int i = 0; i < 100; i++) {
            CompanyRegisterRequest request = new CompanyRegisterRequest(
                    "C_acc_" + i,
                    "C_pwd_" + i,
                    "C_name_" + i,
                    "C_tel_" + i,
                    "C_mail_" + i,
                    AddressDto.of("C_city_" + i, "C_district_" + i, "C_detail_" + i),
                    "C_intro_" + i,
                    "C_link_" + i,
                    "C_ceo_" + i,
                    i % 2 == 0 ? Category.FOOD : Category.ART
            );
            userCommandService.companyJoin(request);
        }

        // 근로자 더미 데이터 저장
        for (int i = 0; i < 100; i++) {
            LaborRegisterRequest request = new LaborRegisterRequest(
                    "L_acc_" + i,
                    "L_pwd_" + i,
                    "L_name_" + i,
                    "L_tel_" + i,
                    "L_mail_" + i,
                    AddressDto.of("L_city_" + i, "L_district_" + i, "L_detail_" + i),
                    i,
                    i % 2 == 0 ? Gender.MALE : Gender.FEMALE
            );
            userCommandService.laborJoin(request);
        }

        // 기획자 더미 데이터 저장
        for (int i = 0; i < 100; i++) {
            HolderRegisterRequest request = new HolderRegisterRequest(
                    "H_acc_" + i,
                    "H_pwd_" + i,
                    "H_name_" + i,
                    "H_tel_" + i,
                    "H_mail_" + i,
                    AddressDto.of("H_city_" + i, "H_district_" + i, "H_detail_" + i)
            );
            userCommandService.holderJoin(request);
        }
    }
}
