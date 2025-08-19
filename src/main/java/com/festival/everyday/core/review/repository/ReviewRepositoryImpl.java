package com.festival.everyday.core.review.repository;

import com.festival.everyday.core.common.dto.ReceiverType;
import com.festival.everyday.core.common.dto.SenderType;
import com.festival.everyday.core.company.domain.QCompany;
import com.festival.everyday.core.festival.domain.QFestival;
import com.festival.everyday.core.review.domain.QReview;
import com.festival.everyday.core.review.dto.command.ReviewAndSenderDto;
import com.festival.everyday.core.user.domain.QLabor;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.festival.everyday.core.common.dto.SenderType.*;
import static com.festival.everyday.core.company.domain.QCompany.*;
import static com.festival.everyday.core.festival.domain.QFestival.*;
import static com.festival.everyday.core.review.domain.QReview.*;
import static com.festival.everyday.core.user.domain.QLabor.*;

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReviewAndSenderDto> findReviewsByCompanies(Long receiverId, ReceiverType receiverType) {
        return queryFactory
                .select(Projections.constructor(ReviewAndSenderDto.class,
                        review.content, company.name))
                .from(review)
                .leftJoin(company).on(company.id.eq(review.senderId).and(review.senderType.eq(COMPANY)))
                .where(review.receiverId.eq(receiverId).and(review.receiverType.eq(receiverType)))
                .fetch();
    }

    @Override
    public List<ReviewAndSenderDto> findReviewsByFestivals(Long receiverId, ReceiverType receiverType) {
        return queryFactory
                .select(Projections.constructor(ReviewAndSenderDto.class,
                        review.content, festival.name))
                .from(review)
                .leftJoin(festival).on(festival.id.eq(review.senderId).and(review.senderType.eq(FESTIVAL)))
                .where(review.receiverId.eq(receiverId).and(review.receiverType.eq(receiverType)))
                .fetch();
    }

    @Override
    public List<ReviewAndSenderDto> findReviewsByLabors(Long receiverId, ReceiverType receiverType) {
        return queryFactory
                .select(Projections.constructor(ReviewAndSenderDto.class,
                        review.content, labor.name))
                .from(review)
                .leftJoin(labor).on(labor.id.eq(review.senderId).and(review.senderType.eq(LABOR)))
                .where(review.receiverId.eq(receiverId).and(review.receiverType.eq(receiverType)))
                .fetch();
    }
}
