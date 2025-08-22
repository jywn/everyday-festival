package com.festival.everyday.core.ai.service;

import com.festival.everyday.core.company.domain.Company;
import com.festival.everyday.core.company.dto.command.CompanySearchDto;
import com.festival.everyday.core.ai.repository.EmbeddingRepository;
import com.festival.everyday.core.company.repository.CompanyRepository;
import com.festival.everyday.core.festival.dto.command.FestivalSearchDto;
import com.festival.everyday.core.festival.repository.FestivalRepository;
import lombok.RequiredArgsConstructor;
import org.postgresql.util.PGobject;
import org.springframework.ai.embedding.Embedding;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendService {

    private final OpenAiEmbeddingModel embeddingModel;
    private final CompanyRepository companyRepository;
    private final FestivalRepository festivalRepository;
    private final EmbeddingRepository embeddingRepository;

    public void embedFestival(Long festivalId, String introduction) throws SQLException {
        float[] embedding = createEmbedding(introduction);
        embeddingRepository.saveFestival(festivalId, embedding);
    }

    public void embedCompany(Long companyId, String introduction) throws SQLException {
        float[] embedding = createEmbedding(introduction);
        embeddingRepository.saveCompany(companyId, embedding);
    }

    public List<Long> recommendCompanies(Long userId, Long festivalId) throws SQLException {

        // 축제의 임베딩 값을 문자열로 조회하고, float 배열로 변환한다.
        float[] embedding = toFloat(embeddingRepository.findFestivalEmbeddings(festivalId));

        // 축제 임베딩 값을 바탕으로 가장 유사한 업체 ID 를 찾는다.
        List<Long> recommendedCompanies = embeddingRepository.findRecommendedCompanies(embedding, 6);

        return recommendedCompanies;

        // 추천 축제 목록을 조회한다.
//        return companyRepository.findSimpleCompanyList(userId, recommendedCompanies);
    }

    public List<Long> recommendFestivals(Long userId, Long companyId) throws SQLException {

        // 축제의 임베딩 값을 문자열로 조회하고, float 배열로 변환한다.
        float[] embedding = toFloat(embeddingRepository.findCompanyEmbeddings(companyId));

        // 축제 임베딩 값을 바탕으로 가장 유사한 업체 ID 를 찾는다.
        List<Long> recommendedFestivals = embeddingRepository.findRecommendedFestivals(embedding, 6);

        return recommendedFestivals;

        // 추천 축제 목록을 조회한다.
        //return festivalRepository.findSimpleFestivalList(userId, recommendedFestivals);
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

//    // JAVA float[] -> pgvector vector
//    private String toPgVector(float[] embedding) {
//        StringBuilder sb = new StringBuilder("[");
//        for (int i = 0; i < embedding.length; i++) {
//            if (i > 0) sb.append(",");
//            sb.append(embedding[i]);
//        }
//        sb.append("]");
//        return sb.toString();
//    }


    // 임베딩 값 반환
    public float[] createEmbedding(String text) {
        return embeddingModel.embed(text);
    }
}
