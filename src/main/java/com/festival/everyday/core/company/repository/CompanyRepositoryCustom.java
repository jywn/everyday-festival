package com.festival.everyday.core.company.repository;

import com.festival.everyday.core.company.dto.command.CompanySearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyRepositoryCustom {
    Page<CompanySearchDto> dynamicSearch(Long userId, String keyword, Pageable pageable);
}
