package com.festival.everyday.core.service;

import com.festival.everyday.core.domain.Festival;
import com.festival.everyday.core.domain.application.Application;
import com.festival.everyday.core.dto.response.*;
import com.festival.everyday.core.repository.ApplicationRepository;
import com.festival.everyday.core.repository.festival.FestivalRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final FestivalRepository festivalRepository;

    //업체->축제 지원 목록 조회
    public CompanyApplicationListResponse getCompanyApplications(Long festivalId, Long holderId, String userType) {
        if (!"HOLDER".equals(userType)) {
            throw new AccessDeniedException("축제에 대한 권한이 없습니다.");
        }
        Festival festival = festivalRepository.findByIdAndHolderId(festivalId, holderId)
                .orElseThrow(() -> new EntityNotFoundException("해당 축제에 대한 권한이 없습니다."));

        List<Application> applications = applicationRepository.findCompanyApplicationsByFestivalId(festivalId);

        List<CompanyApplicationResponse> companyList = applications.stream()
                .map(application -> CompanyApplicationResponse.from(application))
                .toList();

        return CompanyApplicationListResponse.of(festival, companyList);
    }
    //근로자->축제 지원 목록 조회
    public LaborApplicationListResponse getLaborApplications(Long festivalId, Long holderId, String userType) {
        if (!"HOLDER".equals(userType)) {
            throw new AccessDeniedException("축제에 대한 권한이 없습니다.");
        }
        Festival festival = festivalRepository.findByIdAndHolderId(festivalId, holderId)
                .orElseThrow(() -> new EntityNotFoundException("해당 축제에 대한 권한이 없습니다."));

        List<Application> applications = applicationRepository.findLaborApplicationsByFestivalId(festivalId);

        List<LaborApplicationResponse> laborList = applications.stream()
                .map(application -> LaborApplicationResponse.from(application))
                .toList();

        return LaborApplicationListResponse.of(festival, laborList);
    }

    //업체->축제 제출한 지원서 하나 조회
    public CompanyApplicationDetailResponse getCompanyApplicationDetail(Long applicationId, Long festivalId, Long userId, String userType) {

        Application application=applicationRepository.findById(applicationId)
                .orElseThrow(()->new EntityNotFoundException("업체가 작성한 지원서를 찾을 수 없습니다."));

        if (!application.getFestival().getId().equals(festivalId)) {
            throw new AccessDeniedException("지원한 축제와 일치하지 않는 지원서입니다.");
        }
        //이 지원서를 조회할 수 있는 건 지원을 한 업체와 지원을 받은 축제의 주최자 뿐
        boolean permission=false;
        if("COMPANY".equals(userType)){
            if((application.getUser().getId().equals(userId))) {
                permission=true;
            }
        }
        else if("HOLDER".equals(userType)){
            if(application.getFestival().getHolder().getId().equals(userId)) {
                permission=true;
            }
        }
        if(!permission) {
            throw new AccessDeniedException("지원서를 조회할 권한이 없습니다.");
        }

        return CompanyApplicationDetailResponse.of(application);
    }

    //근로자->축제 제출한 지원서 하나 조회
    public LaborApplicationDetailResponse getLaborApplicationDetail(Long applicationId, Long festivalId, Long userId, String userType) {

        Application application=applicationRepository.findById(applicationId)
                .orElseThrow(()->new EntityNotFoundException("근로자가 작성한 지원서를 찾을 수 없습니다."));

        if (!application.getFestival().getId().equals(festivalId)) {
            throw new AccessDeniedException("지원한 축제와 일치하지 않는 지원서입니다.");
        }
        //이 지원서를 조회할 수 있는 건 지원을 한 근로자와 지원을 받은 축제의 주최자 뿐
        boolean permission=false;
        if("LABOR".equals(userType)){
            if((application.getUser().getId().equals(userId))) {
                permission=true;
            }
        }
        else if("HOLDER".equals(userType)){
            if(application.getFestival().getHolder().getId().equals(userId)) {
                permission=true;
            }
        }
        if(!permission) {
            throw new AccessDeniedException("지원서를 조회할 권한이 없습니다.");
        }

        return LaborApplicationDetailResponse.of(application);
    }

    //내가 지원한 축제 목록 조회(업체입장)
    public List<CompanySentApplicationResponse> getSentApplicationsForCompany(Long companyId) {

        List<Application> applications=applicationRepository.findByUserId(companyId);

        return applications.stream()
                .map(application->CompanySentApplicationResponse.of(application))
                .toList();
    }

    //내가 지원한 축제 목록 조회(근로자입장)
    public List<LaborSentApplicationResponse> getSentApplicationsForLabor(Long laborId) {

        List<Application> applications=applicationRepository.findByUserId(laborId);

        return applications.stream()
                .map(application->LaborSentApplicationResponse.of(application))
                .toList();
    }

}
