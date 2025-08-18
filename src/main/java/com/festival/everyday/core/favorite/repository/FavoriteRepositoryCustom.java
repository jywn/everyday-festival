package com.festival.everyday.core.favorite.repository;

import com.festival.everyday.core.common.dto.ReceiverType;
import com.festival.everyday.core.company.domain.Company;
import com.festival.everyday.core.company.dto.command.CompanySearchDto;
import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.festival.dto.command.FestivalSearchDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoriteRepositoryCustom {

    List<CompanySearchDto> findFavoredCompaniesByUserId(@Param("userId") Long userId, @Param("type") ReceiverType type);

    List<FestivalSearchDto> findFavoredFestivalsByUserId(@Param("userId") Long userId, @Param("type") ReceiverType type);
}
