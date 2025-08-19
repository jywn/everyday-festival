package com.festival.everyday.core.favorite.service;

import com.festival.everyday.core.company.dto.command.CompanySearchDto;
import com.festival.everyday.core.favorite.repository.FavoriteRepository;
import com.festival.everyday.core.festival.dto.command.FestivalSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteQueryService {

    private final FavoriteRepository favoriteRepository;

    public List<CompanySearchDto> getFavoriteCompanyList(Long userId) {
        // repository 조회
        return favoriteRepository.findFavoriteCompaniesOfUser(userId);
    }

    public List<FestivalSearchDto> getOnGoingFestivalList(Long userId) {
        return favoriteRepository.findFavoredFestivalsByUserIdOngoing(userId, LocalDateTime.now());
    }

    public List<FestivalSearchDto> getEndedFestivalList(Long userId) {
        return favoriteRepository.findFavoredFestivalsByUserIdEnded(userId, LocalDateTime.now());
    }
}
