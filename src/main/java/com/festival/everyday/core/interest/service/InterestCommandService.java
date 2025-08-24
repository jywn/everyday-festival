package com.festival.everyday.core.interest.service;

import com.festival.everyday.core.company.exception.CompanyNotFoundException;
import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.festival.exception.FestivalNotFoundException;
import com.festival.everyday.core.interest.RedundantInterestException;
import com.festival.everyday.core.interest.domain.Interest;
import com.festival.everyday.core.company.domain.Company;
import com.festival.everyday.core.company.repository.CompanyRepository;
import com.festival.everyday.core.festival.repository.FestivalRepository;
import com.festival.everyday.core.interest.repository.InterestRepository;
import com.festival.everyday.core.notice.handler.event.InterestCreatedEvent;
import com.sun.jdi.request.DuplicateRequestException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class InterestCommandService
{
    private final InterestRepository interestRepository;
    private final FestivalRepository festivalRepository;
    private final CompanyRepository companyRepository;
    private final ApplicationEventPublisher eventPublisher;

    public Long createInterest(Long festivalId, Long companyId) {

        // 이미 관심을 보냈으면 예외를 발생
        if (interestRepository.existsByFestivalIdAndCompanyId(festivalId, companyId)) {
            throw new RedundantInterestException();
        }

        // 관심을 주고 받는 축제와 업체를 조회
        Company company = companyRepository.findById(companyId).orElseThrow(CompanyNotFoundException::new);
        Festival festival = festivalRepository.findById(festivalId).orElseThrow(FestivalNotFoundException::new);

        // 관심 엔티티 생성 후 DB 저장
        Interest interest = Interest.create(company, festival);
        interestRepository.save(interest);

        // 알림을 발생시킨다.
        eventPublisher.publishEvent(InterestCreatedEvent.of(festival.getId(), festival.getName(), company.getId()));

        return interest.getId();
    }

}
