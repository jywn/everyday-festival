package com.festival.everyday.core.service;

import com.festival.everyday.core.domain.Festival;
import com.festival.everyday.core.domain.application.Application;
import com.festival.everyday.core.domain.recruit.CompanyRecruit;
import com.festival.everyday.core.domain.recruit.LaborRecruit;
import com.festival.everyday.core.domain.user.Holder;
import com.festival.everyday.core.domain.user.User;
import com.festival.everyday.core.domain.user.UserType;
import com.festival.everyday.core.dto.*;
import com.festival.everyday.core.dto.mapper.FestivalMapper;
import com.festival.everyday.core.dto.request.FestivalFormRequest;
import com.festival.everyday.core.dto.response.FestivalDetailResponse;
import com.festival.everyday.core.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.festival.everyday.core.dto.ApplyStatus.*;
import static com.festival.everyday.core.dto.RecruitStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
public class FestivalService {

    private final FestivalRepository festivalRepository;
    private final HolderRepository holderRepository;
    private final CompanyRecruitRepository companyRecruitRepository;
    private final LaborRecruitRepository laborRecruitRepository;
    private final ApplicationRepository applicationRepository;
    private final FestivalMapper festivalMapper;

    public FestivalDetailResponse findById(Long userId, Long festivalId) {
        // 축제를 찾아 DTO 로 변환합니다.
        Festival festival = festivalRepository.findById(festivalId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 축제입니다."));
        FestivalDetailDto festivalDetailDto = FestivalDetailDto.from(festival);

        // 업체 모집 공고를 조회하여 모집 여부를 설정합니다.
        RecruitStatus companyRecruitStatus = NOT_RECRUITING;
        Optional<Long> companyRecruitId = festivalRepository.findCompanyRecruitIdById(festivalId);
        if (companyRecruitId.isPresent()) {
            companyRecruitStatus = RECRUITING;
        }

        // 근로자 모집 공고를 조회합니다 모집 여부를 설정합니다.
        RecruitStatus laborRecruitStatus = NOT_RECRUITING;
        Optional<Long> laborRecruitId = festivalRepository.findLaborRecruitIdById(festivalId);
        if (laborRecruitId.isPresent()) {
            laborRecruitStatus = RECRUITING;
        }

        // 업체 모집 공고가 존재하면, 업체 모집 공고를 찾아 DTO 로 변환합니다.
        CompanyRecruitDto companyRecruitDto = null;
        if(companyRecruitStatus.equals(RECRUITING)) {
            Optional<CompanyRecruit> findCompanyRecruit = companyRecruitRepository.findById(companyRecruitId.get());

            Optional<Application> companyApplied = applicationRepository.findApplicationByUserIdAndRecruitId(userId, companyRecruitId.get());
            // application 전부 가져오는건 낭비. 수정 필요
            ApplyStatus applyStatus = companyApplied.isPresent() ? APPLIED : NOT_APPLIED;
            companyRecruitDto = CompanyRecruitDto.from(findCompanyRecruit.get(), applyStatus);

        }

        // 근로자 모집 공고가 존재하면, 근로자 모집 공고를 찾아 DTO 로 변환합니다.
        LaborRecruitDto laborRecruitDto = null;
        if(laborRecruitStatus.equals(RECRUITING)) {
            Optional<LaborRecruit> findLaborRecruit = laborRecruitRepository.findById(laborRecruitId.get());

            Optional<Application> laborApplied = applicationRepository.findApplicationByUserIdAndRecruitId(userId, laborRecruitId.get());
            ApplyStatus applyStatus = laborApplied.isPresent() ? APPLIED : NOT_APPLIED;
            laborRecruitDto = LaborRecruitDto.from(findLaborRecruit.get(), applyStatus);
        }

        return FestivalDetailResponse.of(festivalDetailDto, companyRecruitStatus, companyRecruitDto, laborRecruitStatus, laborRecruitDto);
    }

    public Long save(Long holderId, FestivalFormRequest festivalFormRequest) {
        Holder holder = holderRepository.findById(holderId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 기획자입니다."));
        Festival festival = festivalRepository.save(festivalMapper.toEntity(holder, festivalFormRequest));
        return festival.getId();
    }
}
