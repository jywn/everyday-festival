package com.festival.everyday.core.review.service;

import com.festival.everyday.core.common.dto.ReceiverType;
import com.festival.everyday.core.common.dto.SenderType;
import com.festival.everyday.core.company.domain.Company;
import com.festival.everyday.core.company.exception.CompanyNotFoundException;
import com.festival.everyday.core.company.repository.CompanyRepository;
import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.festival.exception.FestivalNotFoundException;
import com.festival.everyday.core.festival.repository.FestivalRepository;
import com.festival.everyday.core.review.domain.Review;
import com.festival.everyday.core.review.dto.command.CompanyReviewFormDto;
import com.festival.everyday.core.review.dto.command.FestivalReviewFormDto;
import com.festival.everyday.core.review.dto.request.CompanyReviewRequest;
import com.festival.everyday.core.review.dto.request.FestivalReviewRequest;
import com.festival.everyday.core.review.exception.InvalidReviewerException;
import com.festival.everyday.core.review.repository.ReviewRepository;
import com.festival.everyday.core.user.domain.User;
import com.festival.everyday.core.user.exception.UserNotFoundException;
import com.festival.everyday.core.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.festival.everyday.core.common.dto.ReceiverType.*;
import static com.festival.everyday.core.common.dto.SenderType.*;
import static com.festival.everyday.core.common.dto.SenderType.COMPANY;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final CompanyRepository companyRepository;
    private final FestivalRepository festivalRepository;
    private final UserRepository userRepository;


    public Long createFestivalReview(Long festivalId, Long userId, String userType, FestivalReviewFormDto formDto) {

        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException();
        }

        if (!festivalRepository.existsById(festivalId)) {
            throw new FestivalNotFoundException();
        }

        SenderType senderType = switch (userType) {
            case "COMPANY" -> COMPANY;
            case "LABOR" -> LABOR;
            default -> throw new InvalidReviewerException();
        };

        // 리뷰 엔티티를 생성합니다.
        Review review = Review.create(userId, senderType, festivalId, ReceiverType.FESTIVAL, formDto.getContent());

        return reviewRepository.save(review).getId();
    }

    public Long createCompanyReview(Long companyId, CompanyReviewFormDto formDto) {

        if (!festivalRepository.existsById(formDto.getFestivalId())) {
            throw new FestivalNotFoundException();
        }

        if (!companyRepository.existsById(companyId)) {
            throw new CompanyNotFoundException();
        }

        // 리뷰 엔티티를 생성합니다.
        Review review = Review.create(formDto.getFestivalId(), SenderType.FESTIVAL, companyId, ReceiverType.COMPANY, formDto.getContent());

        return reviewRepository.save(review).getId();
    }
}
