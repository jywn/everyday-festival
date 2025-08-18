package com.festival.everyday.core.review.repository;

import com.festival.everyday.core.common.dto.ReceiverType;
import com.festival.everyday.core.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByReceiverIdAndReceiverType(Long receiverId, ReceiverType receiverType);
}
