package com.festival.everyday.core.company.repository;

import com.festival.everyday.core.common.dto.ReceiverType;
import com.festival.everyday.core.common.dto.command.AddressDto;
import com.festival.everyday.core.company.domain.QCompany;
import com.festival.everyday.core.company.dto.command.CompanyDetailDto;
import com.festival.everyday.core.company.dto.command.CompanySearchDto;
import com.festival.everyday.core.favorite.domain.QFavorite;
import com.festival.everyday.core.favorite.dto.FavorStatus;
import com.festival.everyday.core.common.TokenToCond;
import com.festival.everyday.core.common.Tokenizer;
import com.festival.everyday.core.festival.domain.QFestival;
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
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.Optional;

import static com.festival.everyday.core.company.domain.QCompany.*;
import static com.festival.everyday.core.favorite.domain.QFavorite.*;
import static com.festival.everyday.core.festival.domain.QFestival.*;
import static com.festival.everyday.core.image.domain.QImage.*;

@RequiredArgsConstructor
public class CompanyRepositoryImpl implements CompanyRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<CompanySearchDto> searchByKeyword(Long userId, String keyword, Pageable pageable) {

        // 입력 문장을 토큰으로 분할합니다.
        String[] tokens = Tokenizer.getTokens(keyword);

        // 토큰마다 AND 조건문을 연결합니다.
        BooleanExpression andCondition = getAndConditions(tokens);

        List<CompanySearchDto> listAndCondition = queryFactory
                // DTO 를 생성하여 반환합니다.
                .select(Projections.constructor(CompanySearchDto.class,
                        company.id, company.name, company.category,
                        company.address.city, company.address.district, company.address.detail,
                        favorStatus(), image.url
                ))
                .from(company)
                .leftJoin(favorite).on(favorite.sender.id.eq(userId)
                        .and(favorite.receiverType.eq(ReceiverType.COMPANY)
                                .and(favorite.receiverId.eq(company.id))))
                .leftJoin(image).on(image.ownerType.eq(OwnerType.COMPANY).and(image.ownerId.eq(company.id)))
                .where(andCondition)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        return getPageByList(pageable, listAndCondition, andCondition);
    }

    @Override
    public List<CompanySearchDto> findSimpleCompanyList(Long userId, List<Long> companyIds) {

        return queryFactory
                .select(Projections.constructor(CompanySearchDto.class,
                        company.id, company.name, company.category,
                        company.address.city, company.address.district, company.address.detail,
                        favorStatus(), image.url))
                .from(company)
                .leftJoin(favorite).on(favorite.sender.id.eq(userId)
                        .and(favorite.receiverType.eq(ReceiverType.COMPANY)
                                .and(favorite.receiverId.eq(company.id))))
                .leftJoin(image).on(image.ownerType.eq(OwnerType.COMPANY).and(image.ownerId.eq(company.id)))
                .where(company.id.in(companyIds))
                .fetch();
    }

    @Override
    public CompanyDetailDto findCompanyDetailById(Long userId, Long companyId) {
        return queryFactory
                .select(Projections.constructor(CompanyDetailDto.class,
                        company.name, company.category, company.introduction, company.ceoName, company.tel, company.email, company.link,
                        company.address.city, company.address.district, company.address.detail,
                        favorStatus(), image.url))
                .from(company)
                .leftJoin(favorite).on(favorite.sender.id.eq(userId)
                        .and(favorite.receiverType.eq(ReceiverType.COMPANY)
                                .and(favorite.receiverId.eq(company.id))))
                .leftJoin(image).on(image.ownerType.eq(OwnerType.COMPANY).and(image.ownerId.eq(company.id)))
                .where(company.id.eq(companyId))
                .fetchOne();
    }

    // 카운트 쿼리를 날려 페이지 객체를 생성합니다.
    private Page<CompanySearchDto> getPageByList(Pageable pageable, List<CompanySearchDto> listAndCondition, BooleanExpression finalAndCondition) {
        return PageableExecutionUtils.getPage(
                listAndCondition,
                pageable,
                // 카운트 쿼리
                () -> Optional.ofNullable(queryFactory
                        .select(company.count())
                        .from(company)
                        .where(finalAndCondition)
                        .fetchOne()
                ).orElse(0L));
    }

    // 찜 여부를 확인합니다.
    private static EnumExpression<FavorStatus> favorStatus() {
        return Expressions.cases().when(favorite.id.isNotNull()).then(FavorStatus.FAVORED)
                .otherwise(FavorStatus.NOT_FAVORED);
    }

    public static BooleanExpression getAndConditions(String[] tokens) {
        BooleanExpression andCondition = null;
        for (String token : tokens) {
            BooleanExpression tokenExpr = company.name.containsIgnoreCase(token);
            andCondition = (andCondition == null) ? tokenExpr : andCondition.and(tokenExpr);
        }
        return andCondition;
    }
}
