package com.festival.everyday.core.festival.repository;

import com.festival.everyday.core.application.dto.Progress;
import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.festival.dto.command.*;
import com.festival.everyday.core.recruit.domain.Recruit;
import com.festival.everyday.core.recruit.dto.command.CategoryDto;
import com.festival.everyday.core.user.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface FestivalRepositoryCustom {
    Page<FestivalSearchDto> searchByKeyword(Long userId, String keyword, Pageable pageable);

    Page<MyFestivalDto> findFestivalsByHolderIdWithUrl(Long holderId, Pageable pageable, Progress progress);

    FestivalDetailDto findFestivalDetail(Long festivalId, Long userId);

    List<Category> findCompanyRecruitCategories(Long festivalId);

    List<FestivalSearchDto> findSimpleFestivalList(Long userId, List<Long> festivalIds);

    SimpleFestivalWithImageDto findSimpleFestivalWithImage(Long festivalId);

    public List<RecommendFestivalDto> findRecommendedFestivals(List<Long> idList);
}
