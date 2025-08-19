package com.festival.everyday.core.application.service;

import com.festival.everyday.core.application.domain.Application;
import com.festival.everyday.core.application.dto.ApplicationDetailDto;
import com.festival.everyday.core.application.dto.command.CompanyApplicationSimpleDto;
import com.festival.everyday.core.application.dto.command.LaborApplicationSimpleDto;
import com.festival.everyday.core.application.dto.command.MyApplicationSimpleDto;
import com.festival.everyday.core.application.dto.response.*;
import com.festival.everyday.core.application.repository.ApplicationRepository;
import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.festival.repository.FestivalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationQueryService {

    private final ApplicationRepository applicationRepository;
    private final FestivalRepository festivalRepository;

    public List<CompanyApplicationSimpleDto> getCompanyApplications(Long festivalId, Long holderId, String userType) {
        // 기획자가 아니면 예외 발생
        if (!userType.equals("HOLDER")) {
            throw new AccessDeniedException("축제에 대한 권한이 없습니다.");
        }

        // 기획자가 등록한 축제가 존재하지 않음.
        if (!festivalRepository.existsByIdAndHolderId(festivalId, holderId)) {
            throw new EntityNotFoundException("해당 축제에 대한 권한이 존재하지 않습니다.");
        }

        // 업체 지원 목록 조회
        return applicationRepository.findCompanyApplicationList(festivalId);
    }

    //근로자->축제 지원 목록 조회
    public List<LaborApplicationSimpleDto> getLaborApplications(Long festivalId, Long holderId, String userType) {
        // 기획자가 아니면 예외 발생
        if (!userType.equals("HOLDER")) {
            throw new AccessDeniedException("축제에 대한 권한이 없습니다.");
        }

        // 기획자가 등록한 축제가 존재하지 않음.
        if (!festivalRepository.existsByIdAndHolderId(festivalId, holderId)) {
            throw new EntityNotFoundException("해당 축제에 대한 권한이 존재하지 않습니다.");
        }

        // 근로자 지원 목록 조회
        return applicationRepository.findLaborApplicationList(festivalId);
    }

    //업체->축제 제출한 지원서 하나 조회
    public CompanyApplicationDetailResponse getCompanyApplicationDetail(Long applicationId, Long festivalId, Long userId, String userType) {

        Application application = applicationRepository.findById(applicationId)
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

    public List<MyApplicationSimpleDto> getMyApplications(Long userId) {
        return applicationRepository.findMyApplicationList(userId);
    }

    public ApplicationDetailDto getApplicationDetail(Long applicationId, Long festivalId, Long userId) {
        applicationRepository
    }

}
