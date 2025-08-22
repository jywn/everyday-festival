package com.festival.everyday.core.festival.repository;

import com.festival.everyday.core.application.dto.ApplyStatus;
import com.festival.everyday.core.common.dto.ReceiverType;
import com.festival.everyday.core.company.dto.command.CompanySearchDto;
import com.festival.everyday.core.favorite.dto.FavorStatus;
import com.festival.everyday.core.festival.dto.command.FestivalDetailDto;
import com.festival.everyday.core.festival.dto.command.FestivalSearchDto;
import com.festival.everyday.core.festival.dto.command.MyFestivalDto;
import com.festival.everyday.core.image.domain.OwnerType;
import com.festival.everyday.core.recruit.domain.QCompanyRecruit;
import com.festival.everyday.core.recruit.dto.command.CategoryDto;
import com.festival.everyday.core.user.domain.Category;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.Optional;

import static com.festival.everyday.core.application.domain.QApplication.*;
import static com.festival.everyday.core.common.Tokenizer.*;
import static com.festival.everyday.core.company.domain.QCompany.company;
import static com.festival.everyday.core.favorite.domain.QFavorite.*;
import static com.festival.everyday.core.festival.domain.QFestival.*;
import static com.festival.everyday.core.image.domain.QImage.*;
import static com.festival.everyday.core.recruit.domain.QCompanyRecruit.*;
import static com.festival.everyday.core.recruit.domain.QLaborRecruit.*;
import static com.festival.everyday.core.user.domain.QHolder.holder;

@RequiredArgsConstructor
public class FestivalRepositoryImpl implements FestivalRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<FestivalSearchDto> searchByKeyword(Long userId, String keyword, Pageable pageable) {

        // 입력 키워드를 토큰으로 분리합니다.
        String[] tokens = getTokens(keyword);

        // 입력 키워드 토큰을 바탕으로 AND 검색 조건을 생성합니다.
        BooleanExpression andConditions = getAndConditions(tokens);

        // 쿼리를 실행하고, 결과를 DTO 로 변환합니다.
        List<FestivalSearchDto> queryResult = queryFactory
                .select(Projections.constructor(FestivalSearchDto.class,
                        festival.id, festival.name, festival.holder.name,
                        festival.address.city, festival.address.district, festival.address.detail,
                        festival.period.begin, festival.period.end,
                        favorStatus(),
                        image.url
                ))
                .from(festival)
                .leftJoin(favorite).on(favorite.sender.id.eq(userId)
                        .and(favorite.receiverType.eq(ReceiverType.FESTIVAL)
                                .and(favorite.receiverId.eq(festival.id))))
                .leftJoin(image).on(image.ownerType.eq(OwnerType.FESTIVAL).and(image.ownerId.eq(festival.id)))
                .join(festival.holder, holder)
                .where(andConditions)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        JPAQuery<Long> countQuery = queryFactory
                .select(festival.count())
                .from(festival)
                .where(andConditions);

        return PageableExecutionUtils.getPage(queryResult, pageable, countQuery::fetchOne);
    }

    // 페이징
    @Override
    public Page<MyFestivalDto> findFestivalsByHolderIdWithUrl(Long holderId, Pageable pageable) {

        List<MyFestivalDto> queryResult = queryFactory
                .select(Projections.constructor(MyFestivalDto.class,
                        festival.id, festival.name, festival.address.city, festival.address.district, festival.address.detail,
                        festival.period.begin, festival.period.end, image.url))
                .from(festival)
                .join(festival.holder, holder)
                .leftJoin(image).on(image.ownerType.eq(OwnerType.FESTIVAL).and(image.ownerId.eq(festival.id)))
                .where(holder.id.eq(holderId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(festival.count())
                .from(festival)
                .join(festival.holder, holder)
                .where(holder.id.eq(holderId));

        return PageableExecutionUtils.getPage(queryResult, pageable, countQuery::fetchOne);
    }

    @Override
    public FestivalDetailDto findFestivalDetail(Long festivalId, Long userId) {
        return queryFactory
                .select(Projections.constructor(FestivalDetailDto.class,
                        festival.name, festival.fee, festival.time, festival.introduction, festival.tel, festival.link,
                        festival.holder.name,
                        festival.period.begin, festival.period.end,
                        festival.address.city, festival.address.district, festival.address.detail,
                        favorStatus(),
                        image.url,
                        companyRecruit.id, companyRecruit.period.begin, companyRecruit.period.end, companyRecruit.notice, companyRecruit.preferred,
                        laborRecruit.id, laborRecruit.period.begin, laborRecruit.period.end, laborRecruit.notice,laborRecruit.job, laborRecruit.wage, laborRecruit.remark,
                        applyStatus()))
                .from(festival)
                .leftJoin(image).on(image.ownerType.eq(OwnerType.FESTIVAL).and(image.ownerId.eq(festival.id)))
                .leftJoin(favorite).on(favorite.receiverType.eq(ReceiverType.FESTIVAL).and(favorite.receiverId.eq(festival.id)).and(favorite.sender.id.eq(userId)))
                .leftJoin(application).on(application.festival.id.eq(festival.id).and(application.user.id.eq(userId)))
                .leftJoin(festival.companyRecruit, companyRecruit)
                .leftJoin(festival.laborRecruit, laborRecruit)
                .join(festival.holder, holder)
                .where(festival.id.eq(festivalId))
                .fetchOne();
    }

    @Override
    public List<Category> findCompanyRecruitCategories(Long festivalId) {

        EnumPath<Category> category = Expressions.enumPath(Category.class, "category");

        return queryFactory
                .select(category)
                .from(festival)
                .join(festival.companyRecruit, companyRecruit)
                .join(companyRecruit.categories, category)
                .where(festival.id.eq(festivalId))
                .fetch();
    }

    @Override
    public List<FestivalSearchDto> findSimpleFestivalList(Long userId, List<Long> festivalIds) {
        return List.of();
    }

//    @Override
//    public List<FestivalSearchDto> findSimpleFestivalList(Long userId, List<Long> festivalIds) {
//
//        return queryFactory
//                .select(Projections.constructor(CompanySearchDto.class,
//                        company.id, company.name, company.category,
//                        company.address.city, company.address.district, company.address.detail,
//                        favorStatus(), image.url))
//                .from(company)
//                .leftJoin(favorite).on(favorite.sender.id.eq(userId)
//                        .and(favorite.receiverType.eq(ReceiverType.COMPANY)
//                                .and(favorite.receiverId.eq(company.id))))
//                .leftJoin(image).on(image.ownerType.eq(OwnerType.COMPANY).and(image.ownerId.eq(company.id)))
//                .where(company.id.in(companyIds))
//                .fetch();
//    }

    private static SimpleExpression<String> favorStatus() {
        return new CaseBuilder()
                .when(favorite.id.isNotNull()).then(Expressions.constant(FavorStatus.FAVORED.name()))
                .otherwise(Expressions.constant(FavorStatus.NOT_FAVORED.name()));
    }

    private static SimpleExpression<String> applyStatus() {
        return new CaseBuilder()
                .when(application.id.isNotNull()).then(Expressions.constant(ApplyStatus.APPLIED.name()))
                .otherwise(Expressions.constant(ApplyStatus.NOT_APPLIED.name()));
    }

    public static BooleanExpression getAndConditions(String[] tokens) {
        BooleanExpression andCondition = null;
        for (String token : tokens) {
            BooleanExpression tokenExpr = festival.name.containsIgnoreCase(token);
            andCondition = (andCondition == null) ? tokenExpr : andCondition.and(tokenExpr);
        }
        return andCondition;
    }
}
