package com.festival.everyday.core.repository.company;

import com.festival.everyday.core.domain.interaction.ReceiverType;
import com.festival.everyday.core.domain.user.QCompany;
import com.festival.everyday.core.dto.*;
import com.festival.everyday.core.repository.util.TokenToCond;
import com.festival.everyday.core.repository.util.Tokenizer;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EnumExpression;
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
        String[] tokens = Tokenizer.getTokens(keyword);

        BooleanExpression andCondition = TokenToCond.getAndConditions(tokens);

        List<CompanySearchDto> listAndCondition = queryFactory
                .select(Projections.constructor(CompanySearchDto.class,
                        company.id, company.name,
                        company.category,
                        Projections.constructor(AddressDto.class,
                                company.address.city, company.address.district, company.address.detail),
                        favorStatusField()
                ))
                .from(company)
                .leftJoin(company).on(favoredCompany(userId))
                .where(andCondition)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        return getPageByList(userId, pageable, listAndCondition, andCondition);
    }

    private Page<CompanySearchDto> getPageByList(Long userId, Pageable pageable, List<CompanySearchDto> listAndCondition, BooleanExpression finalAndCondition) {
        return PageableExecutionUtils.getPage(
                listAndCondition,
                pageable,
                () -> Optional.ofNullable(queryFactory
                        .select(company.count())
                        .from(company)
                        .leftJoin(favorite).on(
                                favoredCompany(userId)
                        )
                        .where(finalAndCondition)
                        .fetchOne()
                ).orElse(0L));
    }

    private static EnumExpression<FavorStatus> favorStatusField() {
        return Expressions.cases().when(favorite.id.isNotNull()).then(FavorStatus.FAVORED)
                .otherwise(FavorStatus.NOT_FAVORED);
    }

    private static BooleanExpression favoredCompany(Long userId) {
        return favorite.sender.id.eq(userId)
                .and(favorite.receiverType.eq(ReceiverType.COMPANY)
                        .and(favorite.receiverId.eq(company.id)));
    }
}
