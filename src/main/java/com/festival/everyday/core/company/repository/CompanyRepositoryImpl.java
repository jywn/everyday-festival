package com.festival.everyday.core.company.repository;

import com.festival.everyday.core.common.dto.ReceiverType;
import com.festival.everyday.core.common.dto.command.AddressDto;
import com.festival.everyday.core.company.domain.QCompany;
import com.festival.everyday.core.company.dto.command.CompanySearchDto;
import com.festival.everyday.core.favorite.domain.QFavorite;
import com.festival.everyday.core.favorite.dto.FavorStatus;
import com.festival.everyday.core.common.TokenToCond;
import com.festival.everyday.core.common.Tokenizer;
import com.festival.everyday.core.festival.domain.QFestival;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EnumExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.Optional;

import static com.festival.everyday.core.company.domain.QCompany.*;
import static com.festival.everyday.core.favorite.domain.QFavorite.*;
import static com.festival.everyday.core.festival.domain.QFestival.*;

@RequiredArgsConstructor
public class CompanyRepositoryImpl implements CompanyRepositoryCustom {


    private final JPAQueryFactory queryFactory;

    @Override
    public Page<CompanySearchDto> dynamicSearch(Long userId, String keyword, Pageable pageable) {

        // 입력 문장을 토큰으로 분할합니다.
        String[] tokens = Tokenizer.getTokens(keyword);

        // 토큰마다 AND 조건문을 연결합니다.
        BooleanExpression andCondition = TokenToCond.getAndConditions(tokens);

        List<CompanySearchDto> listAndCondition = queryFactory
                // DTO 를 생성하여 반환합니다.
                .select(Projections.constructor(CompanySearchDto.class,
                        // 업체 식별자, 업체 이름, 업체 분야
                        company.id, company.name, company.category,

                        // 주소를 DTO 로 받습니다.
                        Projections.constructor(AddressDto.class, company.address.city, company.address.district, company.address.detail),

                        // 찜 여부를 확인합니다.
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

    // 카운트 쿼리를 날려 페이지 객체를 생성합니다.
    private Page<CompanySearchDto> getPageByList(Long userId, Pageable pageable, List<CompanySearchDto> listAndCondition, BooleanExpression finalAndCondition) {
        return PageableExecutionUtils.getPage(
                listAndCondition,
                pageable,
                // 카운트 쿼리
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

    // 찜 여부를 확인합니다.
    private static EnumExpression<FavorStatus> favorStatusField() {
        return Expressions.cases().when(favorite.id.isNotNull()).then(FavorStatus.FAVORED)
                .otherwise(FavorStatus.NOT_FAVORED);
    }

    // 찜을 누른 업체인지 확인합니다. (조인)
    private static BooleanExpression favoredCompany(Long userId) {
        return favorite.sender.id.eq(userId)
                .and(favorite.receiverType.eq(ReceiverType.COMPANY)
                        .and(favorite.receiverId.eq(company.id)));
    }
}
