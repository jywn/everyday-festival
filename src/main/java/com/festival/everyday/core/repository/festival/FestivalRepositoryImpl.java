package com.festival.everyday.core.repository.festival;

import com.festival.everyday.core.domain.Festival;
import com.festival.everyday.core.domain.QFestival;
import com.festival.everyday.core.domain.interaction.Favorite;
import com.festival.everyday.core.domain.interaction.QFavorite;
import com.festival.everyday.core.domain.interaction.ReceiverType;
import com.festival.everyday.core.domain.user.QHolder;
import com.festival.everyday.core.dto.AddressDto;
import com.festival.everyday.core.dto.FavorStatus;
import com.festival.everyday.core.dto.FestivalSearchDto;
import com.festival.everyday.core.dto.PeriodDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.festival.everyday.core.domain.QFestival.*;
import static com.festival.everyday.core.domain.interaction.QFavorite.*;
import static com.festival.everyday.core.domain.user.QHolder.*;

@RequiredArgsConstructor
public class FestivalRepositoryImpl implements FestivalRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<FestivalSearchDto> dynamicSearch(Long userId, String keyword, Pageable pageable) {
        String[] tokens = keyword.trim().split("\\s+");

        BooleanExpression andCondition = null;
        for (String token : tokens) {
            BooleanExpression tokenExpr = festival.name.containsIgnoreCase(token);
            andCondition = (andCondition == null) ? tokenExpr : andCondition.and(tokenExpr);
        }

        List<FestivalSearchDto> listAndCondition = queryFactory
                .select(Projections.constructor(FestivalSearchDto.class,
                        festival.id, festival.name,
                        Projections.constructor(AddressDto.class,
                                festival.address.city, festival.address.district, festival.address.detail),
                        Projections.constructor(PeriodDto.class,
                                festival.period.begin, festival.period.end),
                        Expressions.cases().when(favorite.id.isNotNull()).then(FavorStatus.FAVORED)
                                .otherwise(FavorStatus.NOT_FAVORED)
                ))
                .from(festival)
                .leftJoin(favorite).on(
                        favorite.sender.id.eq(userId)
                                .and(favorite.receiverType.eq(ReceiverType.FESTIVAL)
                                        .and(favorite.receiverId.eq(festival.id)))
                )
                .where(andCondition)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        BooleanExpression finalAndCondition = andCondition;

        return PageableExecutionUtils.getPage(
                listAndCondition,
                pageable,
                () -> Optional.ofNullable(queryFactory
                        .select(festival.count())
                        .from(festival)
                        .leftJoin(favorite).on(
                                favorite.sender.id.eq(userId)
                                        .and(favorite.receiverType.eq(ReceiverType.FESTIVAL)
                                                .and(favorite.receiverId.eq(festival.id)))
                        )
                        .where(finalAndCondition)
                        .fetchOne()
                ).orElse(0L));
    }
}
