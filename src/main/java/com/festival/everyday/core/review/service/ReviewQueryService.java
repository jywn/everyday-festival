package com.festival.everyday.core.review.service;

import com.festival.everyday.core.common.dto.ReceiverType;
import com.festival.everyday.core.common.dto.response.PageResponse;
import com.festival.everyday.core.company.domain.Company;
import com.festival.everyday.core.company.dto.command.SimpleCompanyWithImageDto;
import com.festival.everyday.core.company.repository.CompanyRepository;
import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.festival.dto.command.SimpleFestivalWithImageDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.festival.everyday.core.common.dto.ReceiverType.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final CompanyRepository companyRepository;
    private final FestivalRepository festivalRepository;

    public Page<ReviewAndSenderDto> getFestivalReviewsByCompanies(Long festivalId, Pageable pageable) {

        return reviewRepository.findReviewsByCompanies(festivalId, FESTIVAL, pageable);
    }

    public Page<ReviewAndSenderDto> getFestivalReviewsByLabors(Long festivalId, Pageable pageable) {

        return reviewRepository.findReviewsByLabors(festivalId, FESTIVAL, pageable);
    }

    public Page<ReviewAndSenderDto> getCompanyReviews(Long companyId, Pageable pageable) {

        return reviewRepository.findReviewsByFestivals(companyId, COMPANY, pageable);
    }

    public SimpleFestivalWithImageDto getFestivalReviewForm(Long festivalId) {

        return festivalRepository.findSimpleFestivalWithImage(festivalId);
    }

    public SimpleCompanyWithImageDto getCompanyReviewForm(Long companyId) {

        return companyRepository.findSimpleCompanyWithImage(companyId);
    }
}
