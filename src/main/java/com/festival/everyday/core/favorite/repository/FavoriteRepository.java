package com.festival.everyday.core.favorite.repository;

import com.festival.everyday.core.favorite.domain.Favorite;
import com.festival.everyday.core.common.dto.ReceiverType;
import com.festival.everyday.core.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    boolean existsBySenderIdAndReceiverIdAndReceiverType(Long senderId, Long receiverId, ReceiverType receiverType);

    Optional<Favorite> findBySenderAndReceiverIdAndReceiverType(User sender, Long receiverId, ReceiverType receiverType);
}
