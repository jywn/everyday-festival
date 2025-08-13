package com.festival.everyday.core.repository.company;

import com.festival.everyday.core.dto.CompanySearchDto;
import com.festival.everyday.core.dto.FestivalSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyRepositoryCustom {
    Page<CompanySearchDto> dynamicSearch(Long userId, String keyword, Pageable pageable);
}
