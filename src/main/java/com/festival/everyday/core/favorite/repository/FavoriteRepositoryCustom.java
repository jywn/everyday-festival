package com.festival.everyday.core.favorite.repository;

import com.festival.everyday.core.application.dto.Progress;
import com.festival.everyday.core.company.dto.command.CompanySearchDto;
import com.festival.everyday.core.festival.dto.command.FestivalSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface FavoriteRepositoryCustom {

    Page<CompanySearchDto> findFavoriteCompaniesOfUser(Long userId, Pageable pageable);
    Page<FestivalSearchDto> findFavoredFestivalsByUserId(Long userId, LocalDateTime now, Pageable pageable, Progress progress);
}
