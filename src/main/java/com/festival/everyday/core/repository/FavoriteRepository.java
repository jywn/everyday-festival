package com.festival.everyday.core.repository;

import com.festival.everyday.core.domain.interaction.Favorite;
import com.festival.everyday.core.domain.interaction.ReceiverType;
import com.festival.everyday.core.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    boolean existsBySenderIdAndReceiverIdAndReceiverType(Long senderId, Long receiverId, ReceiverType receiverType);

    Optional<Favorite> findBySenderAndReceiverIdAndReceiverType(User sender, Long receiverId, ReceiverType receiverType);
}
