//package com.festival.everyday.core.recruit.service;
//
//import com.festival.everyday.core.recruit.domain.CompanyRecruit;
//import com.festival.everyday.core.recruit.domain.LaborRecruit;
//import com.festival.everyday.core.recruit.dto.command.CompanyRecruitDto;
//import com.festival.everyday.core.recruit.dto.command.LaborRecruitDto;
//import com.festival.everyday.core.recruit.repository.CompanyRecruitRepository;
//import com.festival.everyday.core.recruit.repository.LaborRecruitRepository;
//import com.festival.everyday.core.recruit.repository.RecruitRepository;
//import jakarta.persistence.EntityNotFoundException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class RecruitQueryService {
//
//    private final CompanyRecruitRepository companyRecruitRepository;
//    private final LaborRecruitRepository laborRecruitRepository;
//
//    public CompanyRecruitDto findCompanyRecruit(Long festivalId) {
//        CompanyRecruit companyRecruit = companyRecruitRepository.findByFestivalId(festivalId);
//        return CompanyRecruitDto.from(companyRecruit);
//    }
//
//    public LaborRecruitDto findLaborRecruit(Long festivalId) {
//        LaborRecruit laborRecruit = laborRecruitRepository.findByFestivalId;
//        return LaborRecruitDto.from(laborRecruit);
//    }
//}
