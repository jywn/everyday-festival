package com.festival.everyday.core.favorite.repository;

import com.festival.everyday.core.common.dto.ReceiverType;
import com.festival.everyday.core.company.domain.Company;
import com.festival.everyday.core.company.dto.command.CompanySearchDto;
import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.festival.dto.command.FestivalSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface FavoriteRepositoryCustom {

    Page<CompanySearchDto> findFavoriteCompaniesOfUser(Long userId, Pageable pageable);
    Page<FestivalSearchDto> findFavoredFestivalsByUserIdOngoing(Long userId, LocalDateTime now, Pageable pageable);
    Page<FestivalSearchDto> findFavoredFestivalsByUserIdEnded(Long userId, LocalDateTime now, Pageable pageable);
}
