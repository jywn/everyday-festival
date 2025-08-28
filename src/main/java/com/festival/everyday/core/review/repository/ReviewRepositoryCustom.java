package com.festival.everyday.core.review.repository;

import com.festival.everyday.core.common.domain.ReceiverType;
import com.festival.everyday.core.review.dto.command.ReviewAndSenderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {
    public Page<ReviewAndSenderDto> findReviewsByCompanies(Long receiverId, ReceiverType receiverType, Pageable pageable);
    public Page<ReviewAndSenderDto> findReviewsByFestivals(Long receiverId, ReceiverType receiverType, Pageable pageable);
    public Page<ReviewAndSenderDto> findReviewsByLabors(Long receiverId, ReceiverType receiverType, Pageable pageable);
}
