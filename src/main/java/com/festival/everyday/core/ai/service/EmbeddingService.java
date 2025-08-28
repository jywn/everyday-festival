package com.festival.everyday.core.ai.service;

import com.festival.everyday.core.ai.exception.PgVectorFailedException;
import com.festival.everyday.core.ai.repository.EmbeddingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmbeddingService {

    private final OpenAiEmbeddingModel embeddingModel;
    private final EmbeddingRepository embeddingRepository;

    public void embedFestival(Long festivalId, String introduction) {
        float[] embedding = createEmbedding(introduction);
        embeddingRepository.saveFestival(festivalId, embedding);
    }

    public void embedCompany(Long companyId, String introduction) {
        float[] embedding = createEmbedding(introduction);
        embeddingRepository.saveCompany(companyId, embedding);
    }

    public List<Long> recommendCompanies(Long festivalId) {

        // 축제의 임베딩 값을 문자열로 조회하고, float 배열로 변환한다.
        float[] embedding = toFloat(embeddingRepository.findFestivalEmbeddings(festivalId));

        // 축제 임베딩 값을 바탕으로 가장 유사한 업체 ID 를 찾는다.
        return embeddingRepository.findRecommendedCompanies(embedding, 6);
    }

    public List<Long> recommendFestivals(Long companyId) {

        // 축제의 임베딩 값을 문자열로 조회하고, float 배열로 변환한다.
        float[] embedding = toFloat(embeddingRepository.findCompanyEmbeddings(companyId));

        // 축제 임베딩 값을 바탕으로 가장 유사한 업체 ID 를 찾는다.
        return embeddingRepository.findRecommendedFestivals(embedding, 6);
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

        if (embedding.length != 1536) {
            throw new PgVectorFailedException();
        }

        return embedding;
    }

    // 임베딩 값 반환
    public float[] createEmbedding(String text) {
        return embeddingModel.embed(text);
    }
}
