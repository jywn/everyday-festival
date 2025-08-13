package com.festival.everyday.core.repository.company;

import com.festival.everyday.core.domain.interaction.ReceiverType;
import com.festival.everyday.core.domain.user.QCompany;
import com.festival.everyday.core.dto.*;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.Optional;

import static com.festival.everyday.core.domain.QFestival.festival;
import static com.festival.everyday.core.domain.interaction.QFavorite.favorite;
import static com.festival.everyday.core.domain.user.QCompany.*;

@RequiredArgsConstructor
public class CompanyRepositoryImpl implements CompanyRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<CompanySearchDto> dynamicSearch(Long userId, String keyword, Pageable pageable) {
        String[] tokens = keyword.trim().split("\\s+");

        BooleanExpression andCondition = null;
        for (String token : tokens) {
            BooleanExpression tokenExpr = company.name.containsIgnoreCase(token);
            andCondition = (andCondition == null) ? tokenExpr : andCondition.and(tokenExpr);
        }

        List<CompanySearchDto> listAndCondition = queryFactory
                .select(Projections.constructor(CompanySearchDto.class,
                        company.id, company.name,
                        company.category,
                        Projections.constructor(AddressDto.class,
                                company.address.city, company.address.district, company.address.detail),
                        Expressions.cases().when(favorite.id.isNotNull()).then(FavorStatus.FAVORED)
                                .otherwise(FavorStatus.NOT_FAVORED)
                ))
                .from(company)
                .leftJoin(company).on(
                        favorite.sender.id.eq(userId)
                                .and(favorite.receiverType.eq(ReceiverType.COMPANY)
                                        .and(favorite.receiverId.eq(company.id)))
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
                        .select(company.count())
                        .from(company)
                        .leftJoin(favorite).on(
                                favorite.sender.id.eq(userId)
                                        .and(favorite.receiverType.eq(ReceiverType.COMPANY)
                                                .and(favorite.receiverId.eq(company.id)))
                        )
                        .where(finalAndCondition)
                        .fetchOne()
                ).orElse(0L));
    }
}
