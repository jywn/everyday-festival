package com.festival.everyday.core.favorite.repository;

import com.festival.everyday.core.application.dto.Progress;
import com.festival.everyday.core.common.domain.ReceiverType;
import com.festival.everyday.core.company.dto.command.CompanySearchDto;
import com.festival.everyday.core.favorite.dto.FavorStatus;
import com.festival.everyday.core.festival.dto.command.FestivalSearchDto;
import com.festival.everyday.core.image.domain.OwnerType;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDateTime;
import java.util.List;

import static com.festival.everyday.core.application.domain.QApplication.application;
import static com.festival.everyday.core.company.domain.QCompany.*;
import static com.festival.everyday.core.favorite.domain.QFavorite.favorite;
import static com.festival.everyday.core.festival.domain.QFestival.*;
import static com.festival.everyday.core.image.domain.QImage.*;
import static com.festival.everyday.core.user.domain.QHolder.holder;

@RequiredArgsConstructor
public class FavoriteRepositoryImpl implements FavoriteRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    // 페이징
    @Override
    public Page<CompanySearchDto> findFavoriteCompaniesOfUser(Long userId, Pageable pageable) {

        List<CompanySearchDto> queryResult = queryFactory
                .select(Projections.constructor(CompanySearchDto.class,
                        company.id, company.name, company.category,
                        company.address.city, company.address.district, company.address.detail,
                        favorStatus(), image.url))
                .from(company)
                .join(favorite).on(favorite.sender.id.eq(userId)
                        .and(favorite.receiverType.eq(ReceiverType.COMPANY)
                                .and(favorite.receiverId.eq(company.id))))
                .leftJoin(image).on(image.ownerType.eq(OwnerType.COMPANY).and(image.ownerId.eq(company.id)))
                .orderBy(company.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(company.count())
                .from(company)
                .join(favorite).on(favorite.sender.id.eq(userId)
                        .and(favorite.receiverType.eq(ReceiverType.COMPANY)
                                .and(favorite.receiverId.eq(company.id))));

        return PageableExecutionUtils.getPage(queryResult, pageable, countQuery::fetchOne);
    }

    // 페이징
    @Override
    public Page<FestivalSearchDto> findFavoredFestivalsByUserId(Long userId, LocalDateTime now, Pageable pageable, Progress progress) {

        List<FestivalSearchDto> queryResult = queryFactory
                .select(Projections.constructor(FestivalSearchDto.class,
                        festival.id, festival.name,
                        festival.holder.name,
                        festival.address.city, festival.address.district, festival.address.detail,
                        festival.period.begin, festival.period.end,
                        favorStatus(), image.url))
                .from(festival)
                .leftJoin(festival.holder, holder)
                .join(favorite).on(favorite.sender.id.eq(userId)
                        .and(favorite.receiverType.eq(ReceiverType.FESTIVAL)
                                .and(favorite.receiverId.eq(festival.id))))
                .leftJoin(image).on(image.ownerType.eq(OwnerType.FESTIVAL).and(image.ownerId.eq(festival.id)))
                .where(isOngoing(progress))
                .orderBy(festival.period.end.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory.select(festival.count())
                .from(festival)
                .join(favorite).on(favorite.sender.id.eq(userId)
                        .and(favorite.receiverType.eq(ReceiverType.FESTIVAL)
                                .and(favorite.receiverId.eq(festival.id))))
                .where(isOngoing(progress));

        return PageableExecutionUtils.getPage(queryResult, pageable, countQuery::fetchOne);
    }

    private static SimpleExpression<String> favorStatus() {
        return new CaseBuilder()
                .when(favorite.id.isNotNull()).then(Expressions.constant(FavorStatus.FAVORED.name()))
                .otherwise(Expressions.constant(FavorStatus.NOT_FAVORED.name()));
    }

    private BooleanExpression isOngoing(Progress progress) {
        return progress.equals(Progress.ONGOING) ? festival.period.end.after(LocalDateTime.now()) : festival.period.end.before(LocalDateTime.now());
    }
}
