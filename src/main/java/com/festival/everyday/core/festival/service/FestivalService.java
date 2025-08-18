package com.festival.everyday.core.festival.service;

import com.festival.everyday.core.ai.repository.EmbeddingRepository;
import com.festival.everyday.core.application.dto.ApplyStatus;
import com.festival.everyday.core.application.repository.ApplicationRepository;
import com.festival.everyday.core.favorite.dto.FavorStatus;
import com.festival.everyday.core.favorite.repository.FavoriteRepository;
import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.common.dto.ReceiverType;
import com.festival.everyday.core.recruit.domain.CompanyRecruit;
import com.festival.everyday.core.recruit.domain.LaborRecruit;
import com.festival.everyday.core.user.domain.Holder;
import com.festival.everyday.core.festival.dto.command.FestivalDetailDto;
import com.festival.everyday.core.festival.dto.command.FestivalSearchDto;
import com.festival.everyday.core.festival.dto.command.FestivalSimpleDto;
import com.festival.everyday.core.dto.mapper.FestivalMapper;
import com.festival.everyday.core.festival.dto.request.FestivalFormRequest;
import com.festival.everyday.core.festival.dto.response.FestivalDetailResponse;
import com.festival.everyday.core.recruit.dto.RecruitStatus;
import com.festival.everyday.core.recruit.dto.command.CompanyRecruitWithApplyDto;
import com.festival.everyday.core.recruit.dto.command.LaborRecruitWithApplyDto;
import com.festival.everyday.core.common.dto.response.PageResponse;
import com.festival.everyday.core.company.repository.CompanyRepository;
import com.festival.everyday.core.festival.repository.FestivalRepository;
import com.festival.everyday.core.recruit.repository.CompanyRecruitRepository;
import com.festival.everyday.core.recruit.repository.LaborRecruitRepository;
import com.festival.everyday.core.user.repository.HolderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.festival.everyday.core.application.dto.ApplyStatus.*;
import static com.festival.everyday.core.favorite.dto.FavorStatus.*;
import static com.festival.everyday.core.recruit.dto.RecruitStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
public class FestivalService {

    private final FestivalRepository festivalRepository;
    private final CompanyRepository companyRepository;
    private final HolderRepository holderRepository;
    private final CompanyRecruitRepository companyRecruitRepository;
    private final LaborRecruitRepository laborRecruitRepository;
    private final ApplicationRepository applicationRepository;
    private final FavoriteRepository favoriteRepository;
    private final EmbeddingRepository embeddingRepository;
    private final EmbeddingModel embeddingModel;
    private final FestivalMapper festivalMapper;

    public FestivalDetailResponse findById(Long userId, Long festivalId) {
        // 축제를 찾습니다.
        Festival festival = festivalRepository.findById(festivalId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 축제입니다."));

        // 축제를 DTO 로 변환합니다.
        FestivalDetailDto festivalDetailDto = FestivalDetailDto.from(festival);

        // 업체 모집 공고를 조회하여 모집 여부를 설정합니다.
        Optional<Long> companyRecruitId = festivalRepository.findCompanyRecruitIdById(festivalId);
        RecruitStatus companyRecruitStatus = checkRecruitExists(companyRecruitId);

        // 근로자 모집 공고를 조회하여 모집 여부를 설정합니다.
        Optional<Long> laborRecruitId = festivalRepository.findLaborRecruitIdById(festivalId);
        RecruitStatus laborRecruitStatus = checkRecruitExists(laborRecruitId);

        // 업체 모집 공고가 존재하면, 업체 모집 공고를 찾아 DTO 로 변환합니다.
        CompanyRecruitWithApplyDto companyRecruitWithApplyDto = ifCompRecruitExistsToDto(userId, companyRecruitStatus, companyRecruitId.get());

        // 근로자 모집 공고가 존재하면, 근로자 모집 공고를 찾아 DTO 로 변환합니다.
        LaborRecruitWithApplyDto laborRecruitWithApplyDto = ifLaborRecruitExistsToDto(userId, laborRecruitStatus, laborRecruitId.get());

        return FestivalDetailResponse.of(festivalDetailDto, companyRecruitStatus, companyRecruitWithApplyDto, laborRecruitStatus, laborRecruitWithApplyDto);
    }

    public Long save(Long holderId, FestivalFormRequest festivalFormRequest) {
        Holder holder = holderRepository.findById(holderId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 기획자입니다."));
        Festival festival = festivalRepository.save(festivalMapper.toEntity(holder, festivalFormRequest));
        return festival.getId();
    }

    public List<FestivalSimpleDto> findListByHolderId(Long holderId) {
        return  festivalRepository.findFestivalsByHolderId(holderId)
                .stream()
                .map(festival -> {
                    FavorStatus favorStatus = isFavoredFestival(holderId, festival.getId()) ? FAVORED : NOT_FAVORED;
                    return FestivalSimpleDto.of(festival, favorStatus);
                }).toList();
    }

    private boolean isFavoredFestival(Long userId, Long festivalId) {
        return favoriteRepository.existsBySenderIdAndReceiverIdAndReceiverType(userId, festivalId, ReceiverType.FESTIVAL);
    }

    public PageResponse<FestivalSearchDto> searchByKeyword(String keyword, PageRequest pageRequest) {
        Long userId = 1L; // 수정 필요
        return PageResponse.from(festivalRepository.dynamicSearch(userId, keyword, pageRequest));
    }

    private RecruitStatus checkRecruitExists(Optional<Long> id) {
        return id.isPresent() ? RECRUITING : NOT_RECRUITING;
    }

    private ApplyStatus checkApplyStatus(Long userId, Long recruitId) {
        return applicationRepository.existsByUserIdAndRecruitId(userId, recruitId) ? APPLIED : NOT_APPLIED;
    }

    private CompanyRecruitWithApplyDto ifCompRecruitExistsToDto(Long userId, RecruitStatus recruitStatus, Long companyRecruitId) {
        if (recruitStatus.equals(NOT_RECRUITING)) { return null; }

        Optional<CompanyRecruit> findCompanyRecruit = companyRecruitRepository.findById(companyRecruitId);
        return CompanyRecruitWithApplyDto.from(findCompanyRecruit.get(), checkApplyStatus(userId, companyRecruitId));
    }

    private LaborRecruitWithApplyDto ifLaborRecruitExistsToDto(Long userId, RecruitStatus recruitStatus, Long laborRecruitId) {
        if (recruitStatus.equals(NOT_RECRUITING)) { return null; }

        Optional<LaborRecruit> findLaborRecruit = laborRecruitRepository.findById(laborRecruitId);
        return LaborRecruitWithApplyDto.from(findLaborRecruit.get(), checkApplyStatus(userId, laborRecruitId));
    }
}

