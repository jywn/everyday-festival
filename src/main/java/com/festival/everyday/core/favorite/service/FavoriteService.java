package com.festival.everyday.core.favorite.service;

import com.festival.everyday.core.favorite.domain.Favorite;
import com.festival.everyday.core.user.domain.User;
import com.festival.everyday.core.favorite.dto.request.FavoriteRequest;
import com.festival.everyday.core.favorite.dto.response.FavoriteResponse;
import com.festival.everyday.core.favorite.repository.FavoriteRepository;
import com.festival.everyday.core.user.repository.UserRepository;
import com.festival.everyday.core.company.repository.CompanyRepository;
import com.festival.everyday.core.festival.repository.FestivalRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.festival.everyday.core.favorite.dto.FavorStatus.*;

@Service
@Transactional
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final FestivalRepository festivalRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    public FavoriteResponse createFavorite(Long userId, FavoriteRequest request)
    {
        User sender=userRepository.findById(userId)
                .orElseThrow(()->new EntityNotFoundException("유저를 찾을 수 없습니다."));

        Optional<Favorite> existingFavorite=favoriteRepository.findBySenderAndReceiverIdAndReceiverType(sender, request.getReceiverId(),request.getReceiverType());
        Favorite newFavorite=request.toEntity(sender, request.getReceiverType(), request.getReceiverId());

        if(existingFavorite.isPresent()) //찜한 상태였으면 취소하도록
        {
            favoriteRepository.delete(existingFavorite.get());
            return FavoriteResponse.of(existingFavorite.get(),NOT_FAVORED);
        }
        else
        {
            favoriteRepository.save(newFavorite); //처음 찜하는 거면 찜 등록
            return FavoriteResponse.of(newFavorite,FAVORED);
        }
    }
}
