package com.festival.everyday.core.festival.repository;

import com.festival.everyday.core.festival.dto.command.FestivalDetailDto;
import com.festival.everyday.core.festival.dto.command.FestivalSearchDto;
import com.festival.everyday.core.festival.dto.command.FestivalSimpleDto;
import com.festival.everyday.core.festival.dto.command.MyFestivalDto;
import com.festival.everyday.core.recruit.dto.command.CategoryDto;
import com.festival.everyday.core.user.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FestivalRepositoryCustom {
    Page<FestivalSearchDto> searchByKeyword(Long userId, String keyword, Pageable pageable);

    Page<MyFestivalDto> findFestivalsByHolderIdWithUrl(Long holderId, Pageable pageable);

    FestivalDetailDto findFestivalDetail(Long festivalId, Long userId);

    List<Category> findCompanyRecruitCategories(Long festivalId);
}
