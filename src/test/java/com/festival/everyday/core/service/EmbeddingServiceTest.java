package com.festival.everyday.core.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmbeddingServiceTest {

    @Autowired
    private EmbeddingService embeddingService;

    @Test
    void embeddingTest() {
        float[] v1 = embeddingService.createEmbedding("해외 증시 하락으로 코스피가 2% 급락했습니다. 장 마감 후 애플 실적 발표가 예정돼 있습니다.");
        float[] v2 = embeddingService.createEmbedding("내일 비가 온다니 우산을 챙기세요. 퇴근길에 마트 들러 장을 볼 예정입니다.");

        System.out.println(CosineSimilarity.calculate(v1, v2));
    }

    public class CosineSimilarity {

        public static double calculate(float[] vec1, float[] vec2) {
            if (vec1.length != vec2.length) {
                throw new IllegalArgumentException("벡터 길이가 다릅니다.");
            }

            double dotProduct = 0.0;
            double normA = 0.0;
            double normB = 0.0;

            for (int i = 0; i < vec1.length; i++) {
                dotProduct += vec1[i] * vec2[i];
                normA += Math.pow(vec1[i], 2);
                normB += Math.pow(vec2[i], 2);
            }

            return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
        }
    }
}