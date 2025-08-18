package com.festival.everyday.core.service;

import com.festival.everyday.core.repository.EmbeddingRepository;
import com.festival.everyday.core.repository.company.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmbeddingService {

    private final EmbeddingModel embeddingModel;
    private final EmbeddingRepository embeddingRepository;
    private final CompanyRepository companyRepository;

    private float[] embed(String text) {
        // 단일 텍스트를 임베딩
        return embeddingModel.embed(text);
    }

    public void save(Long companyId, String text) {
        embeddingRepository.save(companyId, embed(text));
        return;
    }
}
