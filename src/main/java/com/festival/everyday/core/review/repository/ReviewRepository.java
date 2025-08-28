package com.festival.everyday.core.review.repository;

import com.festival.everyday.core.common.domain.ReceiverType;
import com.festival.everyday.core.common.domain.SenderType;
import com.festival.everyday.core.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
    List<Review> findByReceiverIdAndReceiverType(Long receiverId, ReceiverType receiverType);
    Boolean existsBySenderIdAndSenderTypeAndReceiverIdAndReceiverType(Long senderId, SenderType senderType, Long receiverId, ReceiverType receiverType);
}
