package com.festival.everyday.core.favorite.repository;

import com.festival.everyday.core.common.dto.ReceiverType;
import com.festival.everyday.core.company.domain.Company;
import com.festival.everyday.core.company.dto.command.CompanySearchDto;
import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.festival.dto.command.FestivalSearchDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface FavoriteRepositoryCustom {

    List<CompanySearchDto> findFavoredCompaniesByUserId(Long userId);

    List<FestivalSearchDto> findFavoredFestivalsByUserIdOngoing(Long userId, LocalDateTime now);
    List<FestivalSearchDto> findFavoredFestivalsByUserIdEnded(Long userId, LocalDateTime now);
}
