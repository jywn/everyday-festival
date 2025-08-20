package com.festival.everyday.core.application.service;

import com.festival.everyday.core.application.domain.Application;
import com.festival.everyday.core.application.dto.command.*;
import com.festival.everyday.core.application.dto.response.*;
import com.festival.everyday.core.application.repository.ApplicationRepository;
import com.festival.everyday.core.festival.repository.FestivalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.festival.everyday.core.application.dto.command.ApplicationDetailDto.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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

    public List<MyApplicationSimpleDto> getMyApplications(Long userId) {
        return applicationRepository.findMyApplicationList(userId);
    }

    public ApplicationDetailDto getApplicationDetail(Long applicationId) {
        return ApplicationDetailDto.from(applicationRepository.findApplicationDetail(applicationId));
    }

}
