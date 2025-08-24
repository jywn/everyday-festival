package com.festival.everyday.core.notice.repository;

import com.festival.everyday.core.notice.domain.Notice;
import com.festival.everyday.core.notice.domain.QNotice;
import com.festival.everyday.core.notice.dto.command.NoticeDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.festival.everyday.core.notice.domain.QNotice.*;

@RequiredArgsConstructor
public class NoticeRepositoryImpl implements NoticeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<NoticeDto> findNoticeList(Long receiverId, Pageable pageable) {
        List<NoticeDto> queryResult = queryFactory
                .select(Projections.constructor(NoticeDto.class,
                        notice.senderId, notice.senderName, notice.type, notice.payload, notice.createdAt))
                .from(notice)
                .where(notice.receiverId.eq(receiverId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(notice.count())
                .from(notice)
                .where(notice.receiverId.eq(receiverId));

        return PageableExecutionUtils.getPage(queryResult, pageable, countQuery::fetchOne);
    }
}
