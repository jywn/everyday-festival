package com.festival.everyday.core.notice.handler;

import com.festival.everyday.core.notice.dto.command.*;
import com.festival.everyday.core.notice.handler.event.*;
import com.festival.everyday.core.notice.service.NoticeCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoticeEventHandler {

    private final NoticeCommandService noticeCommandService;

    @EventListener
    public void handleCompanyApplicationCreated(CompanyApplicationCreatedEvent event) {
        noticeCommandService.createCompanyAppliedNotice(CompanyApplicationCreatedDto.from(event));
    }

    @EventListener
    public void handleLaborApplicationCreated(LaborApplicationCreatedEvent event) {
        noticeCommandService.createLaborAppliedNotice(LaborApplicationCreatedDto.from(event));
    }

    @EventListener
    public void handleInterestCreated(InterestCreatedEvent event) {
        noticeCommandService.createInterestNotice(InterestCreatedDto.from(event));
    }

    @EventListener
    public void handleApplyAccepted(ApplyAcceptedEvent event) {
        noticeCommandService.createApplyAcceptedNotice(ApplyAcceptedDto.from(event));
    }

    @EventListener
    public void handleApplyDenied(ApplyDeniedEvent event) {
        noticeCommandService.createApplyDeniedNotice(ApplyDeniedDto.from(event));
    }

    @EventListener
    public void handleCompanyRecruitDead(CompanyRecruitDeadEvent event) {
        noticeCommandService.createCompanyRecruitDeadNotice(CompanyRecruitDeadDto.from(event));
    }

    @EventListener
    public void handleLaborRecruitDead(LaborRecruitDeadEvent event) {
        noticeCommandService.createLaborRecruitDeadNotice(LaborRecruitDeadDto.from(event));
    }

    @EventListener
    public void handleCompanyRecruitDue(CompanyDueEvent event) {
        noticeCommandService.createCompanyDueNotice(CompanyDueDto.from(event));
    }

    @EventListener
    public void handleLaborDue(LaborDueEvent event) {
        noticeCommandService.createLaborDueNotice(LaborDueDto.from(event));
    }
}
