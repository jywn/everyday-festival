package com.festival.everyday.core.favorite.repository;

import com.festival.everyday.core.favorite.domain.Favorite;
import com.festival.everyday.core.common.domain.ReceiverType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long>, FavoriteRepositoryCustom {
    Optional<Favorite> findBySenderIdAndReceiverIdAndReceiverType(Long senderId, Long receiverId, ReceiverType receiverType);
    List<Favorite> findByReceiverIdAndReceiverType(Long receiverId, ReceiverType receiverType);
}
