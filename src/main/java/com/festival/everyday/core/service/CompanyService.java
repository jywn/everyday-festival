package com.festival.everyday.core.service;

import com.festival.everyday.core.domain.user.Company;
import com.festival.everyday.core.dto.CompanySearchDto;
import com.festival.everyday.core.dto.FestivalSearchDto;
import com.festival.everyday.core.dto.response.PageResponse;
import com.festival.everyday.core.repository.company.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService {

    private final CompanyRepository companyRepository;

    public PageResponse<CompanySearchDto> searchByKeyword(String keyword, PageRequest pageRequest) {
        Long userId = 1L; // 수정 필요
        return PageResponse.from(companyRepository.dynamicSearch(userId, keyword, pageRequest));
    }

    public Company findById(Long companyId){
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new IllegalArgumentException("해당 업체를 찾을 수 없습니다. ID = " + companyId));
    }
}
