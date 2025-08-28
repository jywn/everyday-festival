package com.festival.everyday.core.common;

import com.festival.everyday.core.common.domain.Period;
import com.festival.everyday.core.common.domain.ReceiverType;
import com.festival.everyday.core.company.domain.Company;
import com.festival.everyday.core.favorite.domain.Favorite;
import com.festival.everyday.core.favorite.repository.FavoriteRepository;
import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.festival.repository.FestivalRepository;
import com.festival.everyday.core.notice.handler.event.CompanyDueEvent;
import com.festival.everyday.core.notice.handler.event.CompanyRecruitDeadEvent;
import com.festival.everyday.core.notice.handler.event.LaborDueEvent;
import com.festival.everyday.core.notice.handler.event.LaborRecruitDeadEvent;
import com.festival.everyday.core.recruit.domain.CompanyRecruit;
import com.festival.everyday.core.recruit.domain.LaborRecruit;
import com.festival.everyday.core.user.domain.Labor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FestivalScheduler {

    private final FestivalRepository festivalRepository;
    private final FavoriteRepository favoriteRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Scheduled(cron="0 0 1 * * *") //매일 새벽 1시에 자동으로 싹 검사
    public void notifyRecruitmentEnded() {

        LocalDateTime yesterdayStart = LocalDate.now().minusDays(1).atStartOfDay(); //어제의 시작
        LocalDateTime yesterdayEnd = LocalDate.now().minusDays(1).atTime(23, 59, 59); //어제의 끝

        //어제 마감된 모든 축제 찾기 + recruit/holder fetchJoin
        List<Festival> endedFestivals = festivalRepository.findFestivalsWithEndingRecruitment(yesterdayStart, yesterdayEnd);

        //각 축제의 업체 모집이 마감된건지, 근로자 모집이 마감된건지
        for(Festival festival : endedFestivals) {

            CompanyRecruit companyRecruit = festival.getCompanyRecruit();
            if(companyRecruit != null && isEndedYesterday(companyRecruit.getPeriod(), yesterdayStart, yesterdayEnd)) {
                eventPublisher.publishEvent(CompanyRecruitDeadEvent.of(festival.getId(), festival.getName(), festival.getHolder().getId()));
            }

            LaborRecruit laborRecruit = festival.getLaborRecruit();
            if(laborRecruit != null && isEndedYesterday(laborRecruit.getPeriod(), yesterdayStart, yesterdayEnd)) {
                eventPublisher.publishEvent(LaborRecruitDeadEvent.of(festival.getId(), festival.getName(), festival.getHolder().getId()));
            }
        }
    }

    //업체와 근로자 모집기간을 period 로 받아서 그 마감일이 어제였는지 확인하기
    private boolean isEndedYesterday(Period period, LocalDateTime yesterdayStart, LocalDateTime yesterdayEnd) {
        LocalDateTime endDate = period.getEnd();
        return !endDate.isBefore(yesterdayStart) && !endDate.isAfter(yesterdayEnd);
    }

    @Scheduled(cron="0 0 1 * * *")
    public void notifyFestivalDueSoon() {

        LocalDateTime threeDaysLaterStart = LocalDate.now().plusDays(3).atStartOfDay();
        LocalDateTime threeDaysLaterEnd = LocalDate.now().plusDays(3).atTime(23, 59, 59);

        //마감 3일 전인 모든 축제 찾기 + recruit join
        List<Festival> dueFestivals = festivalRepository.findFestivalsWithEndingRecruitment(threeDaysLaterStart, threeDaysLaterEnd);

        //그 축제를 찜한 모든 사용자들 찾기 + sender join
        for (Festival festival : dueFestivals) {
            List<Favorite> favorites = favoriteRepository.findByReceiverIdAndReceiverType(festival.getId(), ReceiverType.FESTIVAL);

            List<Company> companyUsers = favorites.stream()
                    .map(Favorite::getSender)
                    .filter(user -> user instanceof Company)
                    .map(user -> (Company) user)
                    .toList();

            List<Labor> laborUsers = favorites.stream()
                    .map(Favorite::getSender)
                    .filter(user -> user instanceof Labor)
                    .map(user -> (Labor) user)
                    .toList();



            CompanyRecruit companyRecruit = festival.getCompanyRecruit();
            if (companyRecruit != null && isDueInThreeDays(companyRecruit.getPeriod(), threeDaysLaterStart, threeDaysLaterEnd)) {
                companyUsers.forEach(company -> eventPublisher.publishEvent(CompanyDueEvent.from(festival.getId(), festival.getName(), company.getId())));
            }

            LaborRecruit laborRecruit = festival.getLaborRecruit();
            if (laborRecruit != null && isDueInThreeDays(laborRecruit.getPeriod(), threeDaysLaterStart, threeDaysLaterEnd)) {
                laborUsers.forEach(labor -> eventPublisher.publishEvent(LaborDueEvent.from(festival.getId(), festival.getName(), labor.getId())));
            }
        }
    }

    //업체와 근로자 모집기간을 period 로 받아서 그 마감일이 3일 뒤인지 확인하기
    private boolean isDueInThreeDays(Period period, LocalDateTime start, LocalDateTime end) {
        LocalDateTime endDate = period.getEnd();
        return !endDate.isBefore(start) && !endDate.isAfter(end);
    }
}
