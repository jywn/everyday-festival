package com.festival.everyday.core.ai.service;

import com.festival.everyday.core.company.domain.Company;
import com.festival.everyday.core.company.dto.command.CompanySearchDto;
import com.festival.everyday.core.ai.repository.EmbeddingRepository;
import com.festival.everyday.core.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendService {

    private final CompanyRepository companyRepository;
    private final EmbeddingRepository embeddingRepository;

    public List<CompanySearchDto> recommendCompany(Long userId, Long festivalId) {

        // 축제의 임베딩 값을 문자열로 조회하고, float 배열로 변환한다.
        float[] embedding = toFloat(embeddingRepository.findFestivalEmbeddings(festivalId));

        // 축제 임베딩 값을 바탕으로 가장 유사한 업체 ID 를 찾는다.
        List<Long> recommendedCompanies = embeddingRepository.findRecommendedCompanies(toPgVector(embedding), 6);

        // 추천 축제 목록을 조회한다.
        return companyRepository.findSimpleCompanyList(userId, recommendedCompanies);
    }

    private float[] toFloat(String str) {
        String[] splitNum = str
                .replace("[", "")
                .replace("]", "")
                .split(",");

        float[] embedding = new float[splitNum.length];
        for (int i = 0; i < splitNum.length; i++) {
            embedding[i] = Float.parseFloat(splitNum[i].trim());
        }

        return embedding;
    }

    // JAVA float[] -> pgvector vector
    private String toPgVector(float[] embedding) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < embedding.length; i++) {
            if (i > 0) sb.append(",");
            sb.append(embedding[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}
