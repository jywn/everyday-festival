package com.festival.everyday.core.service;

import com.festival.everyday.core.domain.common.value.Period;
import com.festival.everyday.core.domain.recruit.CompanyRecruit;
import com.festival.everyday.core.domain.recruit.ExtraQuestion;
import com.festival.everyday.core.domain.recruit.LaborRecruit;
import com.festival.everyday.core.domain.recruit.Recruit;
import com.festival.everyday.core.dto.CompanyRecruitDto;
import com.festival.everyday.core.dto.LaborRecruitDto;
import com.festival.everyday.core.dto.request.CreateCompanyRecruitRequest;
import com.festival.everyday.core.dto.request.CreateLaborRecruitRequest;
import com.festival.everyday.core.repository.recruit.CompanyRecruitRepository;
import com.festival.everyday.core.repository.recruit.ExtraQuestionRepository;
import com.festival.everyday.core.repository.recruit.LaborRecruitRepository;
import com.festival.everyday.core.repository.recruit.RecruitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecruitService {

    private final RecruitRepository recruitRepository;
    private final CompanyRecruitRepository companyRecruitRepository;
    private final LaborRecruitRepository laborRecruitRepository;
    private final ExtraQuestionRepository extraQuestionRepository;

    public Long saveCompanyRecruit(CreateCompanyRecruitRequest request) {
        // 업체 모집 공고를 생성한다.
        CompanyRecruit companyRecruit = CompanyRecruit.create(Period.create(request.getBegin(), request.getEnd()), request.getNotice(), request.getPreferred());

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
        LaborRecruit laborRecruit = LaborRecruit.create(Period.create(request.getBegin(), request.getEnd()), request.getNotice(), request.getJob(), request.getWage(), request.getRemark());

        // 추가 질문들과 모집 공고의 연관 관계를 설정한다.
        List<ExtraQuestion> questions = ExtraQuestion.createQuestions(laborRecruit, request.getExtraQuestions());

        /**
         * 모집 공고를 저장한다.
         * 모집 공고와 질문은 영속성 전파 관계이므로 저장/삭제가 함께 작동한다.
         */
        return recruitRepository.save(laborRecruit).getId();
    }

    public CompanyRecruitDto findCompanyRecruit(Long festivalId) {
        CompanyRecruit companyRecruit = companyRecruitRepository.findById(festivalId).orElseThrow(() -> new EntityNotFoundException("업체 모집 공고를 찾을 수 없습니다."));
        return CompanyRecruitDto.from(companyRecruit);
    }

    public LaborRecruitDto findLaborRecruit(Long festivalId) {
        LaborRecruit laborRecruit = laborRecruitRepository.findById(festivalId).orElseThrow(() -> new EntityNotFoundException("근로자 모집 공고를 찾을 수 없습니다."));
        return LaborRecruitDto.from(laborRecruit);
    }

}

