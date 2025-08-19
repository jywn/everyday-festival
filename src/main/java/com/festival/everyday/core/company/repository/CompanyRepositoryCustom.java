package com.festival.everyday.core.company.repository;

import com.festival.everyday.core.company.dto.command.CompanyDetailDto;
import com.festival.everyday.core.company.dto.command.CompanySearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CompanyRepositoryCustom {
    Page<CompanySearchDto> searchByKeyword(Long userId, String keyword, Pageable pageable);

    List<CompanySearchDto> findSimpleCompanyList(Long userId, List<Long> companyIds);

    CompanyDetailDto findCompanyDetailById(Long userId, Long companyId);
}
