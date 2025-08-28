package com.festival.everyday.core.festival.service;

import com.festival.everyday.core.application.dto.Progress;
import com.festival.everyday.core.company.dto.command.RecommendCompanyDto;
import com.festival.everyday.core.company.repository.CompanyRepository;
import com.festival.everyday.core.festival.dto.command.*;
import com.festival.everyday.core.festival.dto.response.FestivalDetailResponse;
import com.festival.everyday.core.festival.exception.FestivalNotFoundException;
import com.festival.everyday.core.festival.repository.FestivalRepository;
import com.festival.everyday.core.recruit.repository.CompanyRecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FestivalQueryService {

    private final FestivalRepository festivalRepository;
    private final CompanyRepository companyRepository;
    private final CompanyRecruitRepository companyRecruitRepository;

    public FestivalDetailResponse findById(Long userId, Long festivalId) {
        // 축제를 찾습니다.
        FestivalDetailDto festivalDetail = festivalRepository.findFestivalDetail(festivalId, userId);
        if (festivalDetail == null) {
            throw new FestivalNotFoundException();
        }

        festivalDetail.addCategories(festivalRepository.findCompanyRecruitCategories(festivalId));

        return FestivalDetailResponse.from(festivalDetail);
    }

    public Page<MyFestivalDto> findFestivalsByHolder(Long holderId, Pageable pageable, Progress progress) {

        // 조회한 축제 목록을 찜 여부와 함께 DTO 로 변환합니다.
        return festivalRepository.findFestivalsByHolderIdWithUrl(holderId, pageable, progress);
    }

    // 사용자 ID 를 통해 찜 여부와 함께 축제 목록 페이지를 검색합니다.
    public Page<FestivalSearchDto> searchByKeyword(Long userId, String keyword, Pageable pageable) {
        return festivalRepository.searchByKeyword(userId, keyword, pageable);
    }

    public List<RecommendCompanyDto> getRecommendedCompanies(List<Long> idList) {
        return companyRepository.findRecommendCompanies(idList);
    }
}
