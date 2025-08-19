package com.festival.everyday.core.favorite.service;

import com.festival.everyday.core.favorite.domain.Favorite;
import com.festival.everyday.core.favorite.dto.request.FavoriteRequest;
import com.festival.everyday.core.favorite.repository.FavoriteRepository;
import com.festival.everyday.core.user.domain.User;
import com.festival.everyday.core.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteCommandService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;

    public Long favor(Long userId, FavoriteRequest request)
    {
        // 기존 좋아요가 존재하면 아무 작업도 수행하지 않습니다.
        Optional<Favorite> existingFavorite = favoriteRepository.findBySenderIdAndReceiverIdAndReceiverType(userId, request.getReceiverId(), request.getReceiverType());
        if (existingFavorite.isPresent()) {
            return existingFavorite.get().getId();
        }

        // 사용자를 조회합니다.
        User sender = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("유저를 찾을 수 없습니다."));

        // 좋아요를 생성합니다.
        Favorite favorite = Favorite.create(sender, request.getReceiverType(), request.getReceiverId());

        return favoriteRepository.save(favorite).getId();
    }

    public void unFavor(Long userId, FavoriteRequest request) {

        // 기존 좋아요가 존재하지 않으면 아무것도 수행하지 않습니다.
        Optional<Favorite> existingFavorite = favoriteRepository.findBySenderIdAndReceiverIdAndReceiverType(userId, request.getReceiverId(), request.getReceiverType());
        if (existingFavorite.isEmpty()) { return; }

        favoriteRepository.delete(existingFavorite.get());
    }
}
