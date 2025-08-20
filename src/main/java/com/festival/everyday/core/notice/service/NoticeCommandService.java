package com.festival.everyday.core.notice.service;

import com.festival.everyday.core.application.domain.Application;
import com.festival.everyday.core.company.domain.Company;
import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.interest.domain.Interest;
import com.festival.everyday.core.interest.repository.InterestRepository;
import com.festival.everyday.core.notice.domain.*;
import com.festival.everyday.core.notice.domain.payload.*;
import com.festival.everyday.core.notice.dto.command.*;
import com.festival.everyday.core.notice.repository.NoticeRepository;
import com.festival.everyday.core.recruit.domain.Recruit;
import com.festival.everyday.core.recruit.dto.RecruitType;
import com.festival.everyday.core.user.domain.Holder;
import com.festival.everyday.core.user.domain.Labor;
import com.festival.everyday.core.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.festival.everyday.core.notice.domain.Interested.*;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeCommandService {

    private final NoticeRepository noticeRepository;
    private final InterestRepository interestRepository;

    public Notice createInterestNotice(InterestCreatedDto dto) {

        FestivalInterestPayload payload = new FestivalInterestPayload(dto.getFestivalId(), dto.getFestivalName());

        Notice notice = Notice.create(
                dto.getFestivalId(),
                dto.getFestivalName(),
                dto.getCompanyId(),
                NoticeType.FESTIVAL_INTEREST,
                payload
        );
        return noticeRepository.save(notice);
    }

    public Notice createCompanyAppliedNotice(CompanyApplicationCreatedDto dto) {

        // 나의 관심 여부를 확인한다.
        Interested interested = interestRepository.existsByFestivalIdAndCompanyId(dto.getFestivalId(), dto.getCompanyId()) ? INTERESTED : NOT_INTERESTED;

        // 알림 정보를 생성한다.
        CompanyAppliedPayload payload = CompanyAppliedPayload.of(dto.getCompanyId(), dto.getCompanyName(),
                dto.getFestivalId(), dto.getFestivalName(), interested);

        // 알림 엔티티를 생성한다.
        Notice notice = Notice.create(
                dto.getCompanyId(),
                dto.getCompanyName(),
                dto.getHolderId(),
                NoticeType.COMPANY_APPLIED,
                payload
        );

        // 알림을 저장한다.
        return noticeRepository.save(notice);
    }

    public Notice createLaborAppliedNotice(LaborApplicationCreatedDto dto) {

        // 알림 정보를 생성한다.
        LaborAppliedPayload payload = LaborAppliedPayload.of(dto.getLaborId(), dto.getLaborName(), dto.getFestivalId(), dto.getFestivalName());

        // 알림을 생성한다.
        Notice notice = Notice.create(
                dto.getLaborId(),
                dto.getLaborName(),
                dto.getHolderId(),
                NoticeType.LABOR_APPLIED,
                payload
        );

        // 알림을 저장한다.
        return noticeRepository.save(notice);
    }

    public Notice createCompanyRecruitDeadNotice(CompanyRecruitDeadDto dto) {

        RecruitDeadPayload payload = RecruitDeadPayload.from(dto.getFestivalId(), dto.getFestivalName());

        Notice notice = Notice.create(
                dto.getFestivalId(),
                dto.getFestivalName(),
                dto.getHolderId(),
                NoticeType.COMPANY_RECRUIT_DEAD,
                payload
        );

        return noticeRepository.save(notice);
    }

    public Notice createLaborRecruitDeadNotice(LaborRecruitDeadDto dto) {

        RecruitDeadPayload payload = RecruitDeadPayload.from(dto.getFestivalId(), dto.getFestivalName());

        Notice notice = Notice.create(
                dto.getFestivalId(),
                dto.getFestivalName(),
                dto.getHolderId(),
                NoticeType.LABOR_RECRUIT_DEAD,
                payload
        );

        return noticeRepository.save(notice);
    }

    public Notice createCompanyDueNotice(CompanyDueDto dto) {

        FestivalDuePayload payload = new FestivalDuePayload(dto.getFestivalId(), dto.getFestivalName());

        Notice notice = Notice.create(
                dto.getFestivalId(),
                dto.getFestivalName(),
                dto.getCompanyId(),
                NoticeType.COMPANY_DUE,
                payload
        );

        return noticeRepository.save(notice);
    }

    public Notice createLaborDueNotice(LaborDueDto dto) {

        FestivalDuePayload payload = new FestivalDuePayload(dto.getFestivalId(), dto.getFestivalName());

        Notice notice = Notice.create(
                dto.getFestivalId(),
                dto.getFestivalName(),
                dto.getLaborId(),
                NoticeType.LABOR_DUE,
                payload
        );

        return noticeRepository.save(notice);
    }

    public Notice createApplyAcceptedNotice(ApplyAcceptedDto dto) {

        ApplyAcceptedPayload payload = ApplyAcceptedPayload.of(dto.getFestivalId(), dto.getFestivalName());

        Notice notice = Notice.create(
                dto.getFestivalId(),
                dto.getFestivalName(),
                dto.getUserId(),
                NoticeType.APPLY_ACCEPTED,
                payload
        );

        return noticeRepository.save(notice);
    }

    public Notice createApplyDeniedNotice(ApplyDeniedDto dto) {

        ApplyDeniedPayload payload = ApplyDeniedPayload.of(dto.getFestivalId(), dto.getFestivalName());

        Notice notice = Notice.create(
                dto.getFestivalId(),
                dto.getFestivalName(),
                dto.getUserId(),
                NoticeType.APPLY_DENIED,
                payload
        );

        return noticeRepository.save(notice);
    }

}
