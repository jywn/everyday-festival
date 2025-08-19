package com.festival.everyday.core.application.service;

import com.festival.everyday.core.application.domain.Answer;
import com.festival.everyday.core.application.domain.Application;
import com.festival.everyday.core.application.domain.ExtraAnswer;
import com.festival.everyday.core.application.domain.SELECTED;
import com.festival.everyday.core.application.dto.request.ApplicationRequest;
import com.festival.everyday.core.application.dto.response.UpdateApplicationStatusResponse;
import com.festival.everyday.core.application.repository.ApplicationRepository;
import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.festival.repository.FestivalRepository;
import com.festival.everyday.core.recruit.domain.Recruit;
import com.festival.everyday.core.recruit.repository.RecruitRepository;
import com.festival.everyday.core.user.domain.User;
import com.festival.everyday.core.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationCommandService {

    private final UserRepository userRepository;
    private final FestivalRepository festivalRepository;
    private final ApplicationRepository applicationRepository;


    // 지원서를 작성합니다.
    public Long createCompanyApplication(Long userId, Long festivalId, ApplicationRequest request) {

        // 사용자를 조회합니다.
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다."));

        // 쿼리 튜닝 -> 페치 조인을 적용해 축제와 공고를 조회합니다.
        Festival festival = festivalRepository.findFestivalWithCompanyRecruit(festivalId).orElseThrow(() -> new EntityNotFoundException("축제 + 업체 공고 조회에 실패하였습니다."));

        // 지원서를 생성합니다.
        Application application = Application.create(festival.getCompanyRecruit(), user, festival);
        // 추가 답변을 생성합니다.
        ExtraAnswer.createExtraAnswers(application, request.getExtraAnswers());
        // 답변을 생성합니다.
        Answer.createAnswers(application, request.getAnswers());
        // 지원서를 저장합니다.
        Application saved = applicationRepository.save(application);
        return saved.getId();
    }

    // 지원서를 작성합니다.
    public Long createLaborApplication(Long userId, Long festivalId, ApplicationRequest request) {

        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다."));

        // 쿼리 튜닝 -> 페치 조인을 적용해 축제와 공고를 조회합니다.
        Festival festival = festivalRepository.findFestivalWithLaborRecruit(festivalId).orElseThrow(() -> new EntityNotFoundException("축제 + 근로자 공고 조회를 실패했습니다."));

        // 지원서를 생성합니다.
        Application application = Application.create(festival.getLaborRecruit(), user, festival);
        // 추가 답변을 생성합니다.
        ExtraAnswer.createExtraAnswers(application, request.getExtraAnswers());
        // 답변을 생성합니다.
        Answer.createAnswers(application, request.getAnswers());
        // 지원서를 저장합니다.
        Application saved = applicationRepository.save(application);
        return saved.getId();
    }

    public void selectApplication(Long id) {
        // 지원 사실을 조회합니다.
        Application application = applicationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("지원 사실을 찾을 수 없습니다."));

        // 지원 - 수락
        application.accept();
    }

    public void denyApplication(Long id) {
        // 지원 사실을 조회합니다.
        Application application = applicationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("지원 사실을 찾을 수 없습니다."));

        // 지원 - 수락
        application.deny();
    }
}
