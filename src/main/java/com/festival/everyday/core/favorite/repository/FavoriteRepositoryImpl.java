package com.festival.everyday.core.favorite.repository;

import com.festival.everyday.core.common.dto.ReceiverType;
import com.festival.everyday.core.company.domain.Company;
import com.festival.everyday.core.company.domain.QCompany;
import com.festival.everyday.core.company.dto.command.CompanySearchDto;
import com.festival.everyday.core.favorite.dto.FavorStatus;
import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.festival.domain.QFestival;
import com.festival.everyday.core.festival.dto.command.FestivalSearchDto;
import com.festival.everyday.core.image.domain.OwnerType;
import com.festival.everyday.core.image.domain.QImage;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.EnumExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.festival.everyday.core.company.domain.QCompany.*;
import static com.festival.everyday.core.favorite.domain.QFavorite.favorite;
import static com.festival.everyday.core.festival.domain.QFestival.*;
import static com.festival.everyday.core.image.domain.QImage.*;
import static com.festival.everyday.core.user.domain.QHolder.holder;

@RequiredArgsConstructor
public class FavoriteRepositoryImpl implements FavoriteRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CompanySearchDto> findFavoriteCompaniesOfUser(Long userId) {
            return queryFactory
                    .select(Projections.constructor(CompanySearchDto.class,
                            company.id, company.name, company.category,
                            company.address.city, company.address.district, company.address.detail,
                            favorStatus(), image.url))
                    .from(company)
                    .join(favorite).on(favorite.sender.id.eq(userId)
                            .and(favorite.receiverType.eq(ReceiverType.COMPANY)
                                    .and(favorite.receiverId.eq(company.id))))
                    .leftJoin(image).on(image.ownerType.eq(OwnerType.COMPANY).and(image.ownerId.eq(company.id)))
                    .fetch();
    }

    // status="ONGOING", status="ENDED"로 나눠야함
//    @Override
//    public List<FestivalSearchDto> findFavoredFestivalsByUserId(Long userId) {
//        return queryFactory
//                .select(Projections.constructor(FestivalSearchDto.class,
//                        festival.id, festival.name,
//                        festival.holder.name,
//                        festival.address.city, festival.address.district, festival.address.detail,
//                        festival.period.begin, festival.period.end,
//                        favorStatus(), image.url))
//                .from(festival)
//                .leftJoin(festival.holder, holder)
//                .join(favorite).on(favorite.sender.id.eq(userId)
//                        .and(favorite.receiverType.eq(ReceiverType.FESTIVAL)
//                                .and(favorite.receiverId.eq(festival.id))))
//                .leftJoin(image).on(image.ownerType.eq(OwnerType.FESTIVAL).and(image.ownerId.eq(festival.id)))
//                .fetch();
//    }

        @Override
        public List<FestivalSearchDto> findFavoredFestivalsByUserIdOngoing(Long userId, LocalDateTime now) {
            return queryFactory
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
                    .where(festival.period.end.after(now))
                .fetch();
    }

    @Override
    public List<FestivalSearchDto> findFavoredFestivalsByUserIdEnded(Long userId, LocalDateTime now) {
        return queryFactory
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
                .where(festival.period.end.before(now))
                .fetch();
    }

    private static EnumExpression<FavorStatus> favorStatus() {
        return Expressions.cases()
                .when(favorite.id.isNotNull()).then(FavorStatus.FAVORED)
                .otherwise(FavorStatus.NOT_FAVORED);
    }
}
