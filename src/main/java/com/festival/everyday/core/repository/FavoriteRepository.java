package com.festival.everyday.core.repository;

import com.festival.everyday.core.domain.interaction.Favorite;
import com.festival.everyday.core.domain.interaction.ReceiverType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    boolean existBySenderIdAndReceiverIdAndReceiverType(Long senderId, Long receiverId, ReceiverType receiverType);
}
