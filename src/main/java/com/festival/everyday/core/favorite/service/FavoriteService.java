package com.festival.everyday.core.favorite.service;

import com.festival.everyday.core.common.dto.ReceiverType;
import com.festival.everyday.core.company.domain.Company;
import com.festival.everyday.core.company.dto.command.CompanyDetailDto;
import com.festival.everyday.core.favorite.domain.Favorite;
import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.festival.dto.command.FestivalDetailDto;
import com.festival.everyday.core.user.domain.User;
import com.festival.everyday.core.favorite.dto.request.FavoriteRequest;
import com.festival.everyday.core.favorite.dto.response.FavoriteResponse;
import com.festival.everyday.core.favorite.repository.FavoriteRepository;
import com.festival.everyday.core.user.dto.response.CompanyFavoriteResponse;
import com.festival.everyday.core.user.repository.UserRepository;
import com.festival.everyday.core.company.repository.CompanyRepository;
import com.festival.everyday.core.festival.repository.FestivalRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<CompanyDetailDto> getCompanyFavorites(Long userId) {
        // repository 조회
        List<Company> companies = favoriteRepository.findFavoredCompaniesByUserId(userId, ReceiverType.COMPANY);

        return companies.stream()
                .map(CompanyDetailDto::from)
                .toList();
    }

    public List<FestivalDetailDto> getFestivalFavorites(Long userId) {
        // repository 조회
        List<Festival> festivals = favoriteRepository.findFavoredFestivalsByUserId(userId, ReceiverType.FESTIVAL);

        return festivals.stream()
                .map(FestivalDetailDto::from)
                .toList();
    }

}
