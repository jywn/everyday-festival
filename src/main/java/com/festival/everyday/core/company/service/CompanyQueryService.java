package com.festival.everyday.core.company.service;

import com.festival.everyday.core.company.domain.Company;
import com.festival.everyday.core.company.dto.command.CompanyDetailDto;
import com.festival.everyday.core.company.dto.command.CompanySearchDto;
import com.festival.everyday.core.common.dto.response.PageResponse;
import com.festival.everyday.core.company.exception.CompanyNotFoundException;
import com.festival.everyday.core.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyQueryService {

    private final CompanyRepository companyRepository;

    public PageResponse<CompanySearchDto> searchByKeyword(Long userId, String keyword, PageRequest pageRequest) {

        // 찜 여부와 함께 검색 결과를 반환합니다.
        return PageResponse.from(companyRepository.searchByKeyword(userId, keyword, pageRequest));
    }

    public CompanyDetailDto findById(Long userId, Long companyId){
        CompanyDetailDto companyDetailById = companyRepository.findCompanyDetailById(userId, companyId);
        if (companyDetailById == null) {
            throw new CompanyNotFoundException();
        }
        return companyDetailById;
    }
}
