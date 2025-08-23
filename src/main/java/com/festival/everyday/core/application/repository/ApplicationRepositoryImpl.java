package com.festival.everyday.core.application.repository;

import com.festival.everyday.core.application.domain.Application;
import com.festival.everyday.core.application.domain.SELECTED;
import com.festival.everyday.core.application.dto.command.CompanyApplicationSimpleDto;
import com.festival.everyday.core.application.dto.command.LaborApplicationSimpleDto;
import com.festival.everyday.core.application.dto.command.MyApplicationSimpleDto;
import com.festival.everyday.core.company.domain.QCompany;
import com.festival.everyday.core.recruit.domain.QCompanyRecruit;
import com.festival.everyday.core.recruit.domain.QLaborRecruit;
import com.festival.everyday.core.recruit.domain.QRecruit;
import com.festival.everyday.core.user.domain.QLabor;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

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

    // 페이징
    public Page<CompanyApplicationSimpleDto> findCompanyApplicationList(Long festivalId, Pageable pageable, SELECTED status) {

        List<CompanyApplicationSimpleDto> queryResult = queryFactory
                .select(Projections.constructor(CompanyApplicationSimpleDto.class,
                        application.id, application.selected,
                        company.id, company.name, company.category,
                        company.address.city, company.address.district, company.address.detail, image.url))
                .from(application)
                .join(application.festival, festival)
                .join(company).on(company.id.eq(application.user.id))
                .leftJoin(image).on(image.ownerType.eq(COMPANY).and(image.ownerId.eq(company.id)))
                .where(application.festival.id.eq(festivalId).and(selectedEq(status)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(application.createdAt.desc())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory.select(application.count())
                .from(application)
                .join(application.festival, festival)
                .where(application.festival.id.eq(festivalId));

        return PageableExecutionUtils.getPage(queryResult, pageable, countQuery::fetchOne);
    }

    // 페이징
    @Override
    public Page<LaborApplicationSimpleDto> findLaborApplicationList(Long festivalId, Pageable pageable, SELECTED status) {
        List<LaborApplicationSimpleDto> queryResult = queryFactory
                .select(Projections.constructor(LaborApplicationSimpleDto.class,
                        application.id, application.selected, labor.name, image.url))
                .from(application)
                .join(application.festival, festival)
                .join(labor).on(labor.id.eq(application.user.id))
                .leftJoin(image).on(image.ownerType.eq(LABOR).and(image.ownerId.eq(labor.id)))
                .where(application.festival.id.eq(festivalId).and(selectedEq(status)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(application.createdAt.desc())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory.select(application.count())
                .from(application)
                .join(application.festival, festival)
                .where(application.festival.id.eq(festivalId));

        return PageableExecutionUtils.getPage(queryResult, pageable, countQuery::fetchOne);
    }

    // 페이징
    @Override
    public Page<MyApplicationSimpleDto> findMyApplicationList(Long userId, Pageable pageable, SELECTED status) {
        List<MyApplicationSimpleDto> queryResult = queryFactory
                .select(Projections.constructor(MyApplicationSimpleDto.class,
                        application.id,
                        application.festival.id, application.festival.name, application.festival.holder.name,
                        application.festival.period.begin, application.festival.period.end,
                        application.selected, image.url))
                .from(application)
                .join(application.festival, festival)
                .join(festival.holder, holder)
                .leftJoin(image).on(image.ownerType.eq(FESTIVAL).and(image.ownerId.eq(festival.id)))
                .where(application.user.id.eq(userId).and(selectedEq(status)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(application.createdAt.desc())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(application.count())
                .from(application)
                .join(application.festival, festival)
                .where(application.user.id.eq(userId));

        return PageableExecutionUtils.getPage(queryResult, pageable, countQuery::fetchOne);
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

    private BooleanExpression selectedEq(SELECTED status) {
        return status != null ? application.selected.eq(status) : null;
    }
}
