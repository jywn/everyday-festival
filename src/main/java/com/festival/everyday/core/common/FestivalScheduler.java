package com.festival.everyday.core.common;

import com.festival.everyday.core.common.domain.Period;
import com.festival.everyday.core.common.dto.ReceiverType;
import com.festival.everyday.core.company.domain.Company;
import com.festival.everyday.core.favorite.domain.Favorite;
import com.festival.everyday.core.favorite.repository.FavoriteRepository;
import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.festival.repository.FestivalRepository;
import com.festival.everyday.core.notice.service.NoticeService;
import com.festival.everyday.core.recruit.domain.CompanyRecruit;
import com.festival.everyday.core.recruit.domain.LaborRecruit;
import com.festival.everyday.core.recruit.dto.RecruitType;
import com.festival.everyday.core.user.domain.Labor;
import com.festival.everyday.core.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FestivalScheduler {

    private final FestivalRepository festivalRepository;
    private final NoticeService noticeService;
    private FavoriteRepository favoriteRepository;

    @Scheduled(cron="0 0 1 * * *") //매일 새벽 1시에 자동으로 싹 검사
    public void notifyRecruitmentEnded() {

        LocalDateTime yesterdayStart= LocalDate.now().minusDays(1).atStartOfDay(); //어제의 시작
        LocalDateTime yesterdayEnd= LocalDate.now().minusDays(1).atTime(23, 59, 59); //어제의 끝

        //어제 마감된 모든 축제 찾기
        List<Festival> endedFestivals=festivalRepository.findFestivalsWithEndingRecruitment(yesterdayStart, yesterdayEnd);

        //각 축제의 업체 모집이 마감된건지, 근로자 모집이 마감된건지
        for(Festival festival : endedFestivals) {

            CompanyRecruit companyRecruit=festival.getCompanyRecruit();
            if(companyRecruit!=null && isEndedYesterDay(companyRecruit.getPeriod(),yesterdayStart, yesterdayEnd)) {
                noticeService.createFestivalDeadNotice(festival, RecruitType.COMPANY);
            }

            LaborRecruit laborRecruit=festival.getLaborRecruit();
            if(laborRecruit!=null&& isEndedYesterDay(laborRecruit.getPeriod(),yesterdayStart,yesterdayEnd)) {
                noticeService.createFestivalDeadNotice(festival, RecruitType.LABOR);
            }
        }
    }

    //업체와 근로자 모집기간을 period 로 받아서 그 마감일이 어제였는지 확인하기
    private boolean isEndedYesterDay(Period period, LocalDateTime yesterdayStart, LocalDateTime yesterdayEnd) {
        LocalDateTime endDate=period.getEnd();
        return !endDate.isBefore(yesterdayStart) && !endDate.isAfter(yesterdayEnd);
    }

    @Scheduled(cron="0 0 1 * * *")
    public void notifyFestivalDueSoon() {
        LocalDateTime threeDaysLaterStart = LocalDate.now().plusDays(3).atStartOfDay();
        LocalDateTime threeDaysLaterEnd = LocalDate.now().plusDays(3).atTime(23, 59, 59);

        //마감 3일 전인 모든 축제 찾기
        List<Festival> dueFestivals = festivalRepository.findFestivalsWithEndingRecruitment(threeDaysLaterStart, threeDaysLaterEnd);

        //그 축제를 찜한 모든 사용자들 찾기
        for (Festival festival : dueFestivals) {
            List<Favorite> favorites = favoriteRepository.findByReceiverIdAndReceiverType(festival.getId(), ReceiverType.FESTIVAL);

            for (Favorite favorite : favorites) {
                User user = favorite.getSender();

                if (user instanceof Company) {
                    CompanyRecruit companyRecruit = festival.getCompanyRecruit();
                    //찜한 사용자가 업체이고, 업체 모집 마감 3일 전이 맞다면 알림 보내기
                    if (companyRecruit != null && isDueInThreeDays(companyRecruit.getPeriod(), threeDaysLaterStart, threeDaysLaterEnd)) {
                        noticeService.createFestivalDueNotice(user, festival);
                    }
                } else if (user instanceof Labor) {
                    LaborRecruit laborRecruit = festival.getLaborRecruit();
                    //찜한 사용자가 근로자이고, 근로자 모집 마감 3일 전이 맞다면 알림 보내기
                    if (laborRecruit != null && isDueInThreeDays(laborRecruit.getPeriod(), threeDaysLaterStart, threeDaysLaterEnd)) {
                        noticeService.createFestivalDueNotice(user, festival);
                    }
                }
            }
        }
    }

    //업체와 근로자 모집기간을 period 로 받아서 그 마감일이 3일 뒤인지 확인하기
    private boolean isDueInThreeDays(Period period, LocalDateTime start, LocalDateTime end) {
        LocalDateTime endDate=period.getEnd();
        return !endDate.isBefore(start) && !endDate.isAfter(end);
    }
}
