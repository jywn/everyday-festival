package com.festival.everyday.core.review.service;

import com.festival.everyday.core.common.dto.ReceiverType;
import com.festival.everyday.core.company.domain.Company;
import com.festival.everyday.core.company.repository.CompanyRepository;
import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.festival.repository.FestivalRepository;
import com.festival.everyday.core.review.domain.Review;
import com.festival.everyday.core.review.dto.command.ReviewAndSenderDto;
import com.festival.everyday.core.review.dto.response.*;
import com.festival.everyday.core.review.repository.ReviewRepository;
import com.festival.everyday.core.user.domain.Labor;
import com.festival.everyday.core.user.domain.User;
import com.festival.everyday.core.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.festival.everyday.core.common.dto.ReceiverType.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final FestivalRepository festivalRepository;

    public List<ReviewAndSenderResponse> getFestivalReviewsByCompanies(Long festivalId) {

        return reviewRepository.findReviewsByCompanies(festivalId, FESTIVAL).stream().map(ReviewAndSenderResponse::from).toList();
    }

    public List<ReviewAndSenderResponse> getFestivalReviewsByLabors(Long festivalId) {

        return reviewRepository.findReviewsByLabors(festivalId, FESTIVAL).stream().map(ReviewAndSenderResponse::from).toList();
    }

    public List<ReviewAndSenderResponse> getCompanyReviews(Long companyId) {

        return reviewRepository.findReviewsByFestivals(companyId, COMPANY).stream().map(ReviewAndSenderResponse::from).toList();
    }

    public FestivalReviewFormResponse getFestivalReviewForm(Long festivalId) {

        Festival festival = festivalRepository.findById(festivalId).orElseThrow(()-> new EntityNotFoundException("리뷰를 남길 축제를 찾을 수 없습니다."));

        return FestivalReviewFormResponse.from(festival);
    }

    public CompanyReviewFormResponse getCompanyReviewForm(Long companyId) {

        Company company = companyRepository.findById(companyId).orElseThrow(()-> new EntityNotFoundException("리뷰를 남길 업체를 찾을 수 없습니다."));

        return CompanyReviewFormResponse.from(company);
    }
}
