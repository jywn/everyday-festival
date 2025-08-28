package com.festival.everyday.core.review.service;

import com.festival.everyday.core.application.repository.ApplicationRepository;
import com.festival.everyday.core.common.domain.SenderType;
import com.festival.everyday.core.company.dto.command.SimpleCompanyWithImageDto;
import com.festival.everyday.core.company.exception.CompanyNotFoundException;
import com.festival.everyday.core.company.repository.CompanyRepository;
import com.festival.everyday.core.festival.dto.command.SimpleFestivalWithImageDto;
import com.festival.everyday.core.festival.exception.FestivalNotFoundException;
import com.festival.everyday.core.festival.repository.FestivalRepository;
import com.festival.everyday.core.review.dto.command.ReviewAndSenderDto;
import com.festival.everyday.core.review.exception.ExistingReviewException;
import com.festival.everyday.core.review.exception.InvalidReviewerException;
import com.festival.everyday.core.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.festival.everyday.core.common.domain.ReceiverType.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryService {

    private final ApplicationRepository applicationRepository;
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

    public SimpleFestivalWithImageDto getFestivalReviewForm(Long userId, String userType, Long festivalId) {

        // 중복 리뷰인가
        checkExistingFestivalReview(userId, userType, festivalId);

        SimpleFestivalWithImageDto simpleFestivalWithImage = festivalRepository.findSimpleFestivalWithImage(festivalId);
        if (simpleFestivalWithImage == null) {
            throw new FestivalNotFoundException();
        }

        return simpleFestivalWithImage;
    }

    private void checkExistingFestivalReview(Long userId, String userType, Long festivalId) {
        SenderType type = switch (userType) {
            case "COMPANY" -> SenderType.COMPANY;
            case "LABOR" -> SenderType.LABOR;
            default -> throw new InvalidReviewerException();
        };
        if (reviewRepository.existsBySenderIdAndSenderTypeAndReceiverIdAndReceiverType(userId, type, festivalId, FESTIVAL)) {
            throw new ExistingReviewException();
        }
    }

    public SimpleCompanyWithImageDto getCompanyReviewForm(Long companyId) {

        // 대상 업체를 선정한 축제 목록중, 기획자 아이디(나)가 등록한 축제를 찾는다.
        SimpleCompanyWithImageDto simpleCompanyWithImage = companyRepository.findSimpleCompanyWithImage(companyId);
        if (simpleCompanyWithImage == null) {
            throw new CompanyNotFoundException();
        }

        return simpleCompanyWithImage;
    }
}
