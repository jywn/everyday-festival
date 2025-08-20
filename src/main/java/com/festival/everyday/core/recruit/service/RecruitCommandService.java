package com.festival.everyday.core.recruit.service;

import com.festival.everyday.core.common.domain.Period;
import com.festival.everyday.core.recruit.domain.CompanyRecruit;
import com.festival.everyday.core.recruit.domain.ExtraQuestion;
import com.festival.everyday.core.recruit.domain.LaborRecruit;
import com.festival.everyday.core.recruit.dto.request.CreateCompanyRecruitRequest;
import com.festival.everyday.core.recruit.dto.request.CreateLaborRecruitRequest;
import com.festival.everyday.core.recruit.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RecruitCommandService {

    private final RecruitRepository recruitRepository;

    public Long saveCompanyRecruit(CreateCompanyRecruitRequest request) {
        // 업체 모집 공고를 생성한다.
        CompanyRecruit companyRecruit = CompanyRecruit.create(Period.create(request.getBegin(), request.getEnd()),
                request.getNotice(), request.getPreferred(), request.getCategories());

        // 추가 질문들과 모집 공고의 연관 관계를 설정한다.
        List<ExtraQuestion> questions = ExtraQuestion.createQuestions(companyRecruit, request.getExtraQuestions());

        /**
         * 모집 공고를 저장한다.
         * 모집 공고와 질문은 영속성 전파 관계이므로 저장/삭제가 함께 작동한다.
         */
        return recruitRepository.save(companyRecruit).getId();
    }

    public Long saveLaborRecruit(CreateLaborRecruitRequest request) {
        // 근로자 모집 공고를 생성한다.
        LaborRecruit laborRecruit = LaborRecruit.create(Period.create(request.getBegin(), request.getEnd()),
                request.getNotice(), request.getJob(), request.getWage(), request.getRemark());

        // 추가 질문들과 모집 공고의 연관 관계를 설정한다.
        List<ExtraQuestion> questions = ExtraQuestion.createQuestions(laborRecruit, request.getExtraQuestions());

        /**
         * 모집 공고를 저장한다.
         * 모집 공고와 질문은 영속성 전파 관계이므로 저장/삭제가 함께 작동한다.
         */
        return recruitRepository.save(laborRecruit).getId();
    }
}
