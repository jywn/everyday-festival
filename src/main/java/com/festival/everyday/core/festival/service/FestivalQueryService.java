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
import com.festival.everyday.core.festival.dto.command.MyFestivalDto;
import com.festival.everyday.core.festival.dto.response.FestivalDetailResponse;
import com.festival.everyday.core.festival.repository.FestivalRepository;
import com.festival.everyday.core.image.domain.Image;
import com.festival.everyday.core.image.domain.OwnerType;
import com.festival.everyday.core.image.repository.ImageRepository;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public FestivalDetailResponse findById(Long userId, Long festivalId) {
        // 축제를 찾습니다.
        FestivalDetailDto festivalDetail = festivalRepository.findFestivalDetail(festivalId, userId);
        return FestivalDetailResponse.from(festivalDetail);
    }

    // 기획자 id 를 이용해 축제 목록을 조회합니다.
    public Page<MyFestivalDto> findListByHolderId(Long holderId, Pageable pageable) {

        // 조회한 축제 목록을 찜 여부와 함께 DTO 로 변환합니다.
        return festivalRepository.findFestivalsByHolderIdWithUrl(holderId, pageable);
    }

    // 사용자 ID 를 통해 찜 여부와 함께 축제 목록 페이지를 검색합니다.
    public Page<FestivalSearchDto> searchByKeyword(Long userId, String keyword, Pageable pageable) {
        return festivalRepository.searchByKeyword(userId, keyword, pageable);
    }
}
