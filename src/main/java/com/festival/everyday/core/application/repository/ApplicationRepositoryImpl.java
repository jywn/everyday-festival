package com.festival.everyday.core.application.repository;

import com.festival.everyday.core.application.domain.Application;
import com.festival.everyday.core.application.dto.command.CompanyApplicationSimpleDto;
import com.festival.everyday.core.application.dto.command.LaborApplicationSimpleDto;
import com.festival.everyday.core.application.dto.command.MyApplicationSimpleDto;
import com.festival.everyday.core.company.domain.QCompany;
import com.festival.everyday.core.recruit.domain.QCompanyRecruit;
import com.festival.everyday.core.recruit.domain.QLaborRecruit;
import com.festival.everyday.core.recruit.domain.QRecruit;
import com.festival.everyday.core.user.domain.QLabor;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.festival.everyday.core.application.domain.QApplication.*;
import static com.festival.everyday.core.company.domain.QCompany.*;
import static com.festival.everyday.core.festival.domain.QFestival.*;
import static com.festival.everyday.core.image.domain.OwnerType.*;
import static com.festival.everyday.core.image.domain.QImage.*;
import static com.festival.everyday.core.recruit.domain.QCompanyRecruit.*;
import static com.festival.everyday.core.recruit.domain.QLaborRecruit.*;
import static com.festival.everyday.core.recruit.domain.QRecruit.*;
import static com.festival.everyday.core.user.domain.QHolder.holder;
import static com.festival.everyday.core.user.domain.QLabor.*;

@RequiredArgsConstructor
public class ApplicationRepositoryImpl implements ApplicationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public List<CompanyApplicationSimpleDto> findCompanyApplicationList(Long festivalId) {
        return queryFactory
                .select(Projections.constructor(CompanyApplicationSimpleDto.class,
                        application.id, application.selected,
                        company.id, company.name, company.category,
                        company.address.city, company.address.district, company.address.detail, image.url))
                .from(application)
                .leftJoin(company).on(company.id.eq(application.user.id))
                .leftJoin(image).on(image.ownerType.eq(COMPANY).and(image.ownerId.eq(company.id)))
                .where(application.festival.id.eq(festivalId))
                .fetch();
    }

    @Override
    public List<LaborApplicationSimpleDto> findLaborApplicationList(Long festivalId) {
        return queryFactory
                .select(Projections.constructor(LaborApplicationSimpleDto.class,
                        application.id, application.selected, labor.name, image.url))
                .from(application)
                .leftJoin(labor).on(labor.id.eq(application.user.id))
                .leftJoin(image).on(image.ownerType.eq(LABOR).and(image.ownerId.eq(labor.id)))
                .where(application.festival.id.eq(festivalId))
                .fetch();
    }

    @Override
    public List<MyApplicationSimpleDto> findMyApplicationList(Long userId) {
        return queryFactory
                .select(Projections.constructor(MyApplicationSimpleDto.class,
                        application.festival.id, application.festival.name, application.festival.holder.name,
                        application.festival.period.begin, application.festival.period.end,
                        application.selected, image.url))
                .from(application)
                .leftJoin(festival).on(festival.id.eq(application.festival.id))
                .leftJoin(holder).on(holder.id.eq(festival.holder.id))
                .leftJoin(image).on(image.ownerType.eq(FESTIVAL).and(image.ownerId.eq(festival.id)))
                .where(application.user.id.eq(userId))
                .fetch();
    }

    // 엔티티 직접 조회
    // 배치 사이즈
    // DTO Projection 고려
    @Override
    public Application findApplicationDetail(Long applicationId) {
        return queryFactory
                .selectFrom(application)
                .join(application.recruit, recruit).fetchJoin()
                .where(application.id.eq(applicationId))
                .distinct()
                .fetchOne();
    }
}
