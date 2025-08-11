package com.festival.everyday.core.service;

import com.festival.everyday.core.domain.Festival;
import com.festival.everyday.core.domain.Interest;
import com.festival.everyday.core.domain.user.Company;
import com.festival.everyday.core.domain.user.Holder;
import com.festival.everyday.core.dto.request.InterestRequest;
import com.festival.everyday.core.dto.response.InterestResponse;
import com.festival.everyday.core.repository.CompanyRepository;
import com.festival.everyday.core.repository.FestivalRepository;
import com.festival.everyday.core.repository.HolderRepository;
import com.festival.everyday.core.repository.InterestRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class InterestServiceTest {

    @Autowired
    private InterestService interestService;

    // ... 테스트 데이터 생성을 위한 Repository 주입 ...
    @Autowired
    private InterestRepository interestRepository;
    @Autowired
    private HolderRepository holderRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private FestivalRepository festivalRepository;

    @Test
    @DisplayName("기획자(Holder)가 업체(Company)에게 관심 보내기를 성공한다")
    void createInterest() {
        // given (상황 설정)
        // 1. 저장할 객체를 만들고, save()가 반환한 '저장된 객체'를 새로운 변수에 받습니다.
        Holder savedHolder = holderRepository.save(Holder.builder().name("testHolder").build());
        Company savedCompany = companyRepository.save(Company.builder().name("testCompany").build());

        // 2. '저장된' holder 객체를 사용하여 festival을 만듭니다.
        Festival savedFestival = festivalRepository.save(
                Festival.builder().name("Test Festival").holder(savedHolder).build()
        );

        // 3. '저장된' festival 객체의 ID로 요청 DTO를 만듭니다.
        InterestRequest request = new InterestRequest(savedFestival.getId());


        // when (테스트 실행)
        // 4. '저장된' 객체들의 ID를 서비스에 전달합니다.
        InterestResponse response = interestService.createInterest(
                savedHolder.getId(),
                savedCompany.getId(),
                request
        );


        // then (결과 검증)
        assertThat(response).isNotNull();

        // InterestResponse의 필드명이 signalId라면 response.getSignalId()로 호출해야 합니다.
        Interest savedInterest = interestRepository.findById(response.getInterestId()).get();
        assertThat(savedInterest.getFestival().getId()).isEqualTo(savedFestival.getId());
        assertThat(savedInterest.getCompany().getId()).isEqualTo(savedCompany.getId());
    }
}