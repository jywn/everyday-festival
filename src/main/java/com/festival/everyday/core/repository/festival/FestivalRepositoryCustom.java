package com.festival.everyday.core.repository.festival;

import com.festival.everyday.core.domain.Festival;
import com.festival.everyday.core.dto.FestivalSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FestivalRepositoryCustom {
    Page<FestivalSearchDto> dynamicSearch(Long userId, String keyword, Pageable pageable);
}
