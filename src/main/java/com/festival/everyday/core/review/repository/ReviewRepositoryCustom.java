package com.festival.everyday.core.review.repository;

import com.festival.everyday.core.common.dto.ReceiverType;
import com.festival.everyday.core.review.dto.command.ReviewAndSenderDto;

import java.util.List;

public interface ReviewRepositoryCustom {
    public List<ReviewAndSenderDto> findReviewsByCompanies(Long receiverId, ReceiverType receiverType);
    public List<ReviewAndSenderDto> findReviewsByFestivals(Long receiverId, ReceiverType receiverType);
    public List<ReviewAndSenderDto> findReviewsByLabors(Long receiverId, ReceiverType receiverType);

}
