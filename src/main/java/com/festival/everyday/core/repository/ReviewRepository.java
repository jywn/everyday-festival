package com.festival.everyday.core.repository;

import com.festival.everyday.core.domain.interaction.ReceiverType;
import com.festival.everyday.core.domain.interaction.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByReceiverIdAndReceiverType(Long receiverId, ReceiverType receiverType);
}
