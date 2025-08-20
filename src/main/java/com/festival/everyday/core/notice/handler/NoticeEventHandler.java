package com.festival.everyday.core.notice.handler;

import com.festival.everyday.core.notice.domain.Notice;
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
    private final SseNotificationSender sseNotificationSender;

    @EventListener
    public void handleCompanyApplicationCreated(CompanyApplicationCreatedEvent event) {
        NotificationDto notification = NotificationDto.from(noticeCommandService.createCompanyAppliedNotice(CompanyApplicationCreatedDto.from(event)));
        sseNotificationSender.send(notification);
    }

    @EventListener
    public void handleLaborApplicationCreated(LaborApplicationCreatedEvent event) {
        NotificationDto notification = NotificationDto.from(noticeCommandService.createLaborAppliedNotice(LaborApplicationCreatedDto.from(event)));
        sseNotificationSender.send(notification);
    }

    @EventListener
    public void handleInterestCreated(InterestCreatedEvent event) {
        NotificationDto notification = NotificationDto.from(noticeCommandService.createInterestNotice(InterestCreatedDto.from(event)));
        sseNotificationSender.send(notification);
    }

    @EventListener
    public void handleApplyAccepted(ApplyAcceptedEvent event) {
        NotificationDto notification = NotificationDto.from(noticeCommandService.createApplyAcceptedNotice(ApplyAcceptedDto.from(event)));
        sseNotificationSender.send(notification);
    }

    @EventListener
    public void handleApplyDenied(ApplyDeniedEvent event) {
        NotificationDto notificationDto = NotificationDto.from(noticeCommandService.createApplyDeniedNotice(ApplyDeniedDto.from(event)));
        sseNotificationSender.send(notificationDto);
    }

    @EventListener
    public void handleCompanyRecruitDead(CompanyRecruitDeadEvent event) {
        NotificationDto notification = NotificationDto.from(noticeCommandService.createCompanyRecruitDeadNotice(CompanyRecruitDeadDto.from(event)));
        sseNotificationSender.send(notification);
    }

    @EventListener
    public void handleLaborRecruitDead(LaborRecruitDeadEvent event) {
        NotificationDto notification = NotificationDto.from(noticeCommandService.createLaborRecruitDeadNotice(LaborRecruitDeadDto.from(event)));
        sseNotificationSender.send(notification);
    }

    @EventListener
    public void handleCompanyRecruitDue(CompanyDueEvent event) {
        NotificationDto notification = NotificationDto.from(noticeCommandService.createCompanyDueNotice(CompanyDueDto.from(event)));
        sseNotificationSender.send(notification);
    }

    @EventListener
    public void handleLaborDue(LaborDueEvent event) {
        NotificationDto notification = NotificationDto.from(noticeCommandService.createLaborDueNotice(LaborDueDto.from(event)));
        sseNotificationSender.send(notification);
    }
}
