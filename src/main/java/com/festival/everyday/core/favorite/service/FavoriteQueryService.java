package com.festival.everyday.core.favorite.service;

import com.festival.everyday.core.application.dto.Progress;
import com.festival.everyday.core.common.dto.response.PageResponse;
import com.festival.everyday.core.company.dto.command.CompanySearchDto;
import com.festival.everyday.core.favorite.repository.FavoriteRepository;
import com.festival.everyday.core.festival.dto.command.FestivalSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FavoriteQueryService {

    private final FavoriteRepository favoriteRepository;

    public Page<CompanySearchDto> getFavoriteCompanyList(Long userId, Pageable pageable) {
        // repository 조회
        return favoriteRepository.findFavoriteCompaniesOfUser(userId, pageable);
    }

    public Page<FestivalSearchDto> getFavoriteFestivalList(Long userId, Pageable pageable, Progress progress) {
        return favoriteRepository.findFavoredFestivalsByUserId(userId, LocalDateTime.now(), pageable, progress);
    }
}
