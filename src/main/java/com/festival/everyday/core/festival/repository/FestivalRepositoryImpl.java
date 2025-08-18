package com.festival.everyday.core.festival.repository;

import com.festival.everyday.core.common.dto.ReceiverType;
import com.festival.everyday.core.common.dto.command.AddressDto;
import com.festival.everyday.core.favorite.dto.FavorStatus;
import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.festival.dto.command.FestivalSearchDto;
import com.festival.everyday.core.common.dto.command.PeriodDto;
import com.festival.everyday.core.common.TokenToCond;
import com.festival.everyday.core.festival.dto.command.FestivalSimpleDto;
import com.festival.everyday.core.festival.dto.command.MyFestivalDto;
import com.festival.everyday.core.image.domain.OwnerType;
import com.festival.everyday.core.image.domain.QImage;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EnumExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.Optional;

import static com.festival.everyday.core.common.Tokenizer.*;
import static com.festival.everyday.core.favorite.domain.QFavorite.*;
import static com.festival.everyday.core.festival.domain.QFestival.*;
import static com.festival.everyday.core.image.domain.QImage.*;

@RequiredArgsConstructor
public class FestivalRepositoryImpl implements FestivalRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<FestivalSearchDto> dynamicSearch(Long userId, String keyword, Pageable pageable) {

        // 입력 키워드를 토큰으로 분리합니다.
        String[] tokens = getTokens(keyword);

        // 입력 키워드 토큰을 바탕으로 AND 검색 조건을 생성합니다.
        BooleanExpression andConditions = TokenToCond.getAndConditions(tokens);

        // 쿼리를 실행하고, 결과를 DTO 로 변환합니다.
        List<FestivalSearchDto> listAndCondition = queryFactory
                .select(Projections.constructor(FestivalSearchDto.class,
                        festival.id, festival.name,
                        Projections.constructor(AddressDto.class,
                                festival.address.city, festival.address.district, festival.address.detail),
                        Projections.constructor(PeriodDto.class,
                                festival.period.begin, festival.period.end),
                        favorStatusField()
                ))
                .from(festival)
                .leftJoin(favorite).on(
                        favoredFestival(userId)
                )
                .where(andConditions)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        return getPageByList(userId, pageable, listAndCondition, andConditions);
    }

    @Override
    public List<MyFestivalDto> findFestivalsByHolderIdWithUrl(Long holderId) {

        return queryFactory
                .select(Projections.constructor(MyFestivalDto.class,
                        festival.id, festival.name, festival.address.city, festival.address.district, festival.address.detail,
                        festival.period.begin, festival.period.end, image.url))
                .from(festival)
                .leftJoin(image).on(image.ownerType.eq(OwnerType.FESTIVAL).and(image.ownerId.eq(festival.id)))
                .where(festival.holder.id.eq(holderId))
                .fetch();
    }

    private Page<FestivalSearchDto> getPageByList(Long userId, Pageable pageable, List<FestivalSearchDto> listAndCondition, BooleanExpression andConditions) {
        return PageableExecutionUtils.getPage(
                listAndCondition,
                pageable,
                () -> Optional.ofNullable(queryFactory
                        .select(festival.count())
                        .from(festival)
                        .leftJoin(favorite).on(
                                favoredFestival(userId)
                        )
                        .where(andConditions)
                        .fetchOne()
                ).orElse(0L));
    }

    private static EnumExpression<FavorStatus> favorStatusField() {
        return Expressions.cases().when(favorite.id.isNotNull()).then(FavorStatus.FAVORED)
                .otherwise(FavorStatus.NOT_FAVORED);
    }

    private static BooleanExpression favoredFestival(Long userId) {
        return favorite.sender.id.eq(userId)
                .and(favorite.receiverType.eq(ReceiverType.FESTIVAL)
                        .and(favorite.receiverId.eq(festival.id)));
    }
}
