package com.festival.everyday.core.service;

import com.festival.everyday.core.domain.Festival;
import com.festival.everyday.core.domain.interaction.ReceiverType;
import com.festival.everyday.core.domain.interaction.Review;
import com.festival.everyday.core.domain.interaction.SenderType;
import com.festival.everyday.core.domain.user.Company;
import com.festival.everyday.core.domain.user.Labor;
import com.festival.everyday.core.domain.user.User;
import com.festival.everyday.core.dto.request.CompanyReviewRequest;
import com.festival.everyday.core.dto.request.FestivalReviewRequest;
import com.festival.everyday.core.dto.response.*;
import com.festival.everyday.core.repository.CompanyRepository;
import com.festival.everyday.core.repository.FestivalRepository;
import com.festival.everyday.core.repository.ReviewRepository;
import com.festival.everyday.core.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.security.access.AccessDeniedException;

import java.util.List;

import static com.festival.everyday.core.domain.interaction.SenderType.COMPANY;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final FestivalRepository festivalRepository;
    private final CompanyRepository companyRepository;

    public Review createFestivalReview(Long festivalId, Long userId, String userType, FestivalReviewRequest request) {

        User sender=userRepository.findById(userId)
                .orElseThrow(()->new EntityNotFoundException("사용자를 찾을 수 없습니다."));

        Festival festival=festivalRepository.findById(festivalId)
                .orElseThrow(()->new EntityNotFoundException("리뷰를 남길 축제를 찾을 수 없습니다."));

        SenderType senderType=SenderType.COMPANY;

        if ("COMPANY".equals(userType))
        {
            senderType= COMPANY;
        }
        else if ("LABOR".equals(userType))
        {
            senderType=SenderType.LABOR;
        }

        Review review=request.toEntity(sender,senderType,festivalId, ReceiverType.FESTIVAL);

        return reviewRepository.save(review);
    }

    public Review createCompanyReview(Long companyId, Long holderId, CompanyReviewRequest request) {

        festivalRepository.findByIdAndHolderId(request.getFestivalId(), holderId)
                .orElseThrow(()->new AccessDeniedException("축제에 대한 권한이 없습니다."));

        Company company=companyRepository.findById(companyId)
                .orElseThrow(()->new EntityNotFoundException("리뷰를 남길 업체를 찾을 수 없습니다."));

        Review review=request.toEntity(SenderType.FESTIVAL, companyId, ReceiverType.COMPANY);

        return reviewRepository.save(review);
    }

    public FestivalReviewListResponse getFestivalReviews(Long festivalId) {

            List<Review> reviews=reviewRepository.findByReceiverIdAndReceiverType(festivalId, ReceiverType.FESTIVAL);

            List<FestivalReviewItemResponse> reviewList=reviews.stream()
                    .map(review-> {
                        User writer=userRepository.findById(review.getSenderId())
                                .orElseThrow(() -> new EntityNotFoundException("리뷰 작성자를 찾을 수 없습니다."));

                        String writerName="알 수 없는 사용자"; //디폴트는 이걸로..
                        if (writer instanceof Company company)
                        {
                            writerName=company.getName();
                        }
                        else if(writer instanceof Labor labor)
                        {
                            writerName=labor.getName();
                        }
                        return FestivalReviewItemResponse.of(review, writerName);
                    })
                    .toList();

            return FestivalReviewListResponse.from(reviewList);
    }

    public CompanyReviewListResponse getCompanyReviews(Long companyId) {

        List<Review> reviews=reviewRepository.findByReceiverIdAndReceiverType(companyId, ReceiverType.COMPANY);

        List<CompanyReviewItemResponse> reviewList=reviews.stream()
                .map(review-> {
                    Festival writer=festivalRepository.findById(review.getSenderId())
                            .orElseThrow(() -> new EntityNotFoundException("리뷰 작성자를 찾을 수 없습니다."));

                    String writerName=writer.getName();

                    return CompanyReviewItemResponse.of(review, writerName);
                })
                .toList();

        return CompanyReviewListResponse.from(reviewList);
    }

    public FestivalReviewFormResponse getFestivalReviewForm(Long festivalId) {

        Festival festival=festivalRepository.findById(festivalId)
                .orElseThrow(()-> new EntityNotFoundException("리뷰를 남길 축제를 찾을 수 없습니다."));

        return FestivalReviewFormResponse.of(festival);
    }

    public CompanyReviewFormResponse getCompanyReviewForm(Long companyId) {

        Company company=companyRepository.findById(companyId)
                .orElseThrow(()-> new EntityNotFoundException("리뷰를 남길 업체를 찾을 수 없습니다."));

        return CompanyReviewFormResponse.of(company);
    }
}
