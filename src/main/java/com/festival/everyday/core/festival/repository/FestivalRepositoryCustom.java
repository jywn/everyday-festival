package com.festival.everyday.core.festival.repository;

import com.festival.everyday.core.festival.dto.command.FestivalSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FestivalRepositoryCustom {
    Page<FestivalSearchDto> dynamicSearch(Long userId, String keyword, Pageable pageable);
}
