package com.festival.everyday.core.favorite.repository;

import com.festival.everyday.core.company.domain.Company;
import com.festival.everyday.core.favorite.domain.Favorite;
import com.festival.everyday.core.common.dto.ReceiverType;
import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long>, FavoriteRepositoryCustom {
    boolean existsBySenderIdAndReceiverIdAndReceiverType(Long senderId, Long receiverId, ReceiverType receiverType);

    Optional<Favorite> findBySenderAndReceiverIdAndReceiverType(User sender, Long receiverId, ReceiverType receiverType);

    Optional<Favorite> findBySenderIdAndReceiverIdAndReceiverType(Long senderId, Long receiverId, ReceiverType receiverType);
}
