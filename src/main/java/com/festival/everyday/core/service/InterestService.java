package com.festival.everyday.core.service;

import com.festival.everyday.core.domain.Festival;
import com.festival.everyday.core.domain.interaction.Interest;
import com.festival.everyday.core.domain.user.Company;
import com.festival.everyday.core.dto.request.InterestRequest;
import com.festival.everyday.core.dto.response.InterestResponse;
import com.festival.everyday.core.repository.CompanyRepository;
import com.festival.everyday.core.repository.FestivalRepository;
import com.festival.everyday.core.repository.InterestRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class InterestService
{
    private final InterestRepository interestRepository;
    private final FestivalRepository festivalRepository;
    private final CompanyRepository companyRepository;

    //holderID는 토큰정보로 알 수 있고
    public Interest createInterest(Long holderId, Long companyId, InterestRequest request)
    {
        Company company=companyRepository.findById(companyId)
                .orElseThrow(()->new EntityNotFoundException("업체를 찾을 수 없습니다."));
        //소유권 확인
        Festival festival=festivalRepository.findByIdAndHolderId(request.getFestivalId(),holderId)
                .orElseThrow(()->new AccessDeniedException("해당 축제에 대한 권한이 없습니다."));

        Interest newInterest=request.toEntity(company, festival);

        return interestRepository.save(newInterest);
    }

}
