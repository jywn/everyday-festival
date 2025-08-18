package com.festival.everyday.core.festival.service;

import com.festival.everyday.core.application.dto.ApplyStatus;
import com.festival.everyday.core.application.repository.ApplicationRepository;
import com.festival.everyday.core.common.dto.ReceiverType;
import com.festival.everyday.core.common.dto.response.PageResponse;
import com.festival.everyday.core.favorite.dto.FavorStatus;
import com.festival.everyday.core.favorite.repository.FavoriteRepository;
import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.festival.dto.command.FestivalDetailDto;
import com.festival.everyday.core.festival.dto.command.FestivalSearchDto;
import com.festival.everyday.core.festival.dto.command.FestivalSimpleDto;
import com.festival.everyday.core.festival.dto.response.FestivalDetailResponse;
import com.festival.everyday.core.festival.repository.FestivalRepository;
import com.festival.everyday.core.recruit.domain.CompanyRecruit;
import com.festival.everyday.core.recruit.domain.LaborRecruit;
import com.festival.everyday.core.recruit.dto.RecruitStatus;
import com.festival.everyday.core.recruit.dto.command.CompanyRecruitWithApplyDto;
import com.festival.everyday.core.recruit.dto.command.LaborRecruitWithApplyDto;
import com.festival.everyday.core.recruit.repository.CompanyRecruitRepository;
import com.festival.everyday.core.recruit.repository.LaborRecruitRepository;
import com.festival.everyday.core.recruit.repository.RecruitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.festival.everyday.core.application.dto.ApplyStatus.APPLIED;
import static com.festival.everyday.core.application.dto.ApplyStatus.NOT_APPLIED;
import static com.festival.everyday.core.favorite.dto.FavorStatus.FAVORED;
import static com.festival.everyday.core.favorite.dto.FavorStatus.NOT_FAVORED;
import static com.festival.everyday.core.recruit.dto.RecruitStatus.NOT_RECRUITING;
import static com.festival.everyday.core.recruit.dto.RecruitStatus.RECRUITING;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FestivalQueryService {

    private final FestivalRepository festivalRepository;
    private final ApplicationRepository applicationRepository;
    private final CompanyRecruitRepository companyRecruitRepository;
    private final LaborRecruitRepository laborRecruitRepository;
    private final FavoriteRepository favoriteRepository;

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


    // 모집 여부를 확인합니다.
    private RecruitStatus checkRecruitExists(Optional<Long> id) {
        return id.isPresent() ? RECRUITING : NOT_RECRUITING;
    }

    // 지원 여부를 확인합니다.
    private ApplyStatus checkApplyStatus(Long userId, Long recruitId) {

        // 사용자 ID 와 모집 공고 ID 를 사용해 지원서 존재를 확인합니다.
        return applicationRepository.existsByUserIdAndRecruitId(userId, recruitId) ? APPLIED : NOT_APPLIED;
    }

    // 업체 모집 공고가 존재하면 지원 여부를 찾아 DTO 로 변환합니다.
    private CompanyRecruitWithApplyDto ifCompRecruitExistsToDto(Long userId, RecruitStatus recruitStatus, Long companyRecruitId) {
        if (recruitStatus.equals(NOT_RECRUITING)) { return null; }

        // 모집 공고를 조회합니다.
        CompanyRecruit companyRecruit = companyRecruitRepository.findById(companyRecruitId).orElseThrow(() -> new EntityNotFoundException("업체 모집 공고 조회에 실패하였습니다."));

        // 모집 공고를 지원 여부와 함께 DTO 로 변환하여 반환합니다.
        return CompanyRecruitWithApplyDto.from(companyRecruit, checkApplyStatus(userId, companyRecruitId));
    }

    // 근로자 모집 공고가 존재하면 지원 여부를 찾아 DTO 로 변환합니다.
    private LaborRecruitWithApplyDto ifLaborRecruitExistsToDto(Long userId, RecruitStatus recruitStatus, Long laborRecruitId) {
        if (recruitStatus.equals(NOT_RECRUITING)) { return null; }

        // 모집 공고를 조회합니다.
        LaborRecruit laborRecruit = laborRecruitRepository.findById(laborRecruitId).orElseThrow(() -> new EntityNotFoundException("근로자 모집 공고 조회에 실패하였습니다."));

        // 모집 공고를 지원 여부와 함께 DTO 로 변환하여 반환합니다.
        return LaborRecruitWithApplyDto.from(laborRecruit, checkApplyStatus(userId, laborRecruitId));
    }

    // 기획자 id 를 이용해 축제 목록을 조회합니다.
    public List<FestivalSimpleDto> findListByHolderId(Long holderId) {

        // 조회한 축제 목록을 찜 여부와 함께 DTO 로 변환합니다.
        return  festivalRepository.findFestivalsByHolderId(holderId)
                .stream()
                .map(festival -> {
                    FavorStatus favorStatus = isFavoredFestival(holderId, festival.getId()) ? FAVORED : NOT_FAVORED;
                    return FestivalSimpleDto.of(festival, favorStatus);
                }).toList();
    }

    // 축제의 찜 여부를 확인합니다.
    private boolean isFavoredFestival(Long userId, Long festivalId) {
        return favoriteRepository.existsBySenderIdAndReceiverIdAndReceiverType(userId, festivalId, ReceiverType.FESTIVAL);
    }

    // 사용자 ID 를 통해 찜 여부와 함께 축제 목록 페이지를 검색합니다.
    public PageResponse<FestivalSearchDto> searchByKeyword(String keyword, PageRequest pageRequest) {
        Long userId = 1L; // 수정 필요
        return PageResponse.from(festivalRepository.dynamicSearch(userId, keyword, pageRequest));
    }
}
