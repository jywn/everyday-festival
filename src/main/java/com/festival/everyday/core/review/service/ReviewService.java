package com.festival.everyday.core.review.service;

import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.common.dto.ReceiverType;
import com.festival.everyday.core.review.domain.Review;
import com.festival.everyday.core.common.dto.SenderType;
import com.festival.everyday.core.company.domain.Company;
import com.festival.everyday.core.review.dto.response.*;
import com.festival.everyday.core.user.domain.Labor;
import com.festival.everyday.core.user.domain.User;
import com.festival.everyday.core.review.dto.request.CompanyReviewRequest;
import com.festival.everyday.core.review.dto.request.FestivalReviewRequest;
import com.festival.everyday.core.review.repository.ReviewRepository;
import com.festival.everyday.core.user.repository.UserRepository;
import com.festival.everyday.core.company.repository.CompanyRepository;
import com.festival.everyday.core.festival.repository.FestivalRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.security.access.AccessDeniedException;

import java.util.List;

import static com.festival.everyday.core.common.dto.SenderType.COMPANY;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final FestivalRepository festivalRepository;
    private final CompanyRepository companyRepository;
}
