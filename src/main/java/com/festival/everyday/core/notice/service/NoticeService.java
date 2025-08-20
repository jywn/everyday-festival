//package com.festival.everyday.core.notice.service;
//
//import com.festival.everyday.core.application.domain.Application;
//import com.festival.everyday.core.company.domain.Company;
//import com.festival.everyday.core.festival.domain.Festival;
//import com.festival.everyday.core.interest.domain.Interest;
//import com.festival.everyday.core.interest.repository.InterestRepository;
//import com.festival.everyday.core.notice.domain.*;
//import com.festival.everyday.core.notice.repository.NoticeRepository;
//import com.festival.everyday.core.recruit.domain.Recruit;
//import com.festival.everyday.core.recruit.dto.RecruitType;
//import com.festival.everyday.core.user.domain.Holder;
//import com.festival.everyday.core.user.domain.Labor;
//import com.festival.everyday.core.user.domain.User;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class NoticeService {
//    private final NoticeRepository noticeRepository;
//    private final InterestRepository interestRepository;
//
//    @Transactional
//    public List<Notice> getNotices(Long userId) {
//        return noticeRepository.findByReceiverIdOrderByCreatedAtDesc(userId);
//    }
//
//    public void createFestivalInterestNotice(Interest interest) {
//        Festival festival=interest.getFestival();
//        Company company = (Company) interest.getCompany();
//
//        FestivalInterestPayload payload = new FestivalInterestPayload(festival.getId(), festival.getName());
//
//        Notice notice = Notice.create(
//                festival.getId(),
//                festival.getName(),
//                company.getId(),
//                NoticeType.FESTIVAL_INTEREST,
//                payload
//        );
//        noticeRepository.save(notice);
//    }
//
//    public void createCompanyAppliedNotice(Application application) {
//        Company company = (Company) application.getUser();
//        Festival festival = application.getFestival();
//        Holder receiver = festival.getHolder();
//
//        Interested interested= Interested.INTERESTED;
//        boolean isInterested = interestRepository.existsByFestivalAndCompany(festival, company);
//        if(!isInterested){
//            interested = Interested.NOT_INTERESTED;
//        }
//        CompanyAppliedPayload payload = new CompanyAppliedPayload(company.getId(), festival.getId(), festival.getName(), interested);
//
//        Notice notice = Notice.create(
//                company.getId(),
//                company.getName(),
//                receiver.getId(),
//                NoticeType.COMPANY_APPLIED,
//                payload
//        );
//        noticeRepository.save(notice);
//    }
//
//    public void createLaborAppliedNotice(Application application) {
//        Labor labor = (Labor) application.getUser();
//        Festival festival = application.getFestival();
//        Holder receiver = festival.getHolder();
//
//        LaborAppliedPayload payload = new LaborAppliedPayload(labor.getId(), festival.getId(), festival.getName());
//
//        Notice notice = Notice.create(
//                labor.getId(),
//                labor.getName(),
//                receiver.getId(),
//                NoticeType.LABOR_APPLIED,
//                payload
//        );
//        noticeRepository.save(notice);
//    }
//
//    public void createFestivalDeadNotice(Festival festival, RecruitType recruitType) {
//        Holder receiver=festival.getHolder();
//
//        FestivalDeadPayload payload=new FestivalDeadPayload(festival.getId(), festival.getName(),recruitType);
//
//        Notice notice = Notice.create(
//                festival.getId(),
//                festival.getName(),
//                receiver.getId(),
//                NoticeType.FESTIVAL_DEAD,
//                payload
//        );
//
//        noticeRepository.save(notice);
//    }
//
//    public void createFestivalDueNotice(User receiver, Festival festival) {
//
//        Recruit recruit = festival.getCompanyRecruit();
//        if(recruit == null) {
//            recruit = festival.getLaborRecruit();
//        }
//        if(recruit == null) return; //업체 모집 공고, 근로자 모집 공고가 모두 없으면 알림 생성 X
//
//        FestivalDuePayload payload = new FestivalDuePayload(festival.getId(), festival.getName());
//
//        Notice notice = Notice.create(
//                festival.getId(),
//                festival.getName(),
//                receiver.getId(),
//                NoticeType.FESTIVAL_DUE,
//                payload
//        );
//
//        noticeRepository.save(notice);
//    }
//}
