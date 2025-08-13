package com.festival.everyday.core.service;

import com.festival.everyday.core.domain.Festival;
import com.festival.everyday.core.domain.application.Application;
import com.festival.everyday.core.dto.response.*;
import com.festival.everyday.core.repository.ApplicationRepository;
import com.festival.everyday.core.repository.FestivalRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final FestivalRepository festivalRepository;

    //업체->축제 지원 목록 조회
    public CompanyApplicationListResponse getCompanyApplications(Long festivalId) {
        Festival festival = festivalRepository.findById(festivalId)
                .orElseThrow(() -> new EntityNotFoundException("해당 축제를 찾을 수 없습니다."));
        List<Application> applications = applicationRepository.findCompanyApplicationsByFestivalId(festivalId);

        List<CompanyApplicationResponse> companyList = applications.stream()
                .map(application -> CompanyApplicationResponse.from(application))
                .toList();

        return CompanyApplicationListResponse.of(festival, companyList);
    }
    //근로자->축제 지원 목록 조회
    public LaborApplicationListResponse getLaborApplications(Long festivalId) {
        Festival festival = festivalRepository.findById(festivalId)
                .orElseThrow(() -> new EntityNotFoundException("해당 축제를 찾을 수 없습니다."));

        List<Application> applications = applicationRepository.findLaborApplicationsByFestivalId(festivalId);

        List<LaborApplicationResponse> laborList = applications.stream()
                .map(application -> LaborApplicationResponse.from(application))
                .toList();

        return LaborApplicationListResponse.of(festival, laborList);
    }

    //업체->축제 제출한 지원서 하나 조회
    public CompanyApplicationDetailResponse getCompanyApplicationDetail(Long applicationId, Long companyId) {

        Application application=applicationRepository.findByIdAndUserId(applicationId, companyId)
                .orElseThrow(()->new EntityNotFoundException("업체가 작성한 지원서를 조회할 수 없습니다."));

        return CompanyApplicationDetailResponse.of(application);
    }

    //근로자->축제 제출한 지원서 하나 조회
    public LaborApplicationDetailResponse getLaborApplicationDetail(Long applicationId, Long laborId) {

        Application application=applicationRepository.findByIdAndUserId(applicationId, laborId)
                .orElseThrow(()->new EntityNotFoundException("근로자가 작성한 지원서를 조회할 수 없습니다."));

        return LaborApplicationDetailResponse.of(application);
    }

    public List<CompanySentApplicationResponse> getSentApplicationsForCompany(Long companyId) {

        List<Application> applications=applicationRepository.findApplicationByApplicationIdWithDetails(companyId);

        return applications.stream()
                .map(application->CompanySentApplicationResponse.of(application))
                .toList();
    }

    public List<LaborSentApplicationResponse> getSentApplicationsForLabor(Long laborId) {

        List<Application> applications=applicationRepository.findApplicationByApplicationIdWithDetails(laborId);

        return applications.stream()
                .map(application->LaborSentApplicationResponse.of(application))
                .toList();
    }

}
