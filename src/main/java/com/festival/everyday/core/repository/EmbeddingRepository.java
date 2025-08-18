package com.festival.everyday.core.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class EmbeddingRepository {

    // jdbcTemplate 을 활용해 직접 쿼리를 작성한다.
    private final JdbcTemplate jdbcTemplate;

    @Qualifier("vectorDataSource")
    private final DataSource dataSource;

    public void saveCompany(Long companyId, float[] embedding) {
        String sql = "INSERT INTO company_embeddings (company_id, embedding) VALUES (?, ?)";
        jdbcTemplate.update(sql, companyId, embedding);
    }

    public void saveFestival(Long festivalId, float[] embedding) {
        String sql = "INSERT INTO festival_embeddings (festival_id, embedding) VALUES (?, ?)";
        jdbcTemplate.update(sql, festivalId, embedding);
    }

    public String findFestivalEmbeddings(Long festivalId) {
        String sql = "SELECT embedding FROM festival_embeddings WHERE festival_id = ?;";
        return jdbcTemplate.queryForObject(sql, String.class, festivalId);
    }

    public List<Long> findRecommendedCompanies(String embedding, int limit) {
        String sql = "SELECT company_id FROM company_embeddings " +
                "ORDER BY embedding <=> ? LIMIT ?";

        return jdbcTemplate.queryForList(sql, Long.class, embedding, limit);
    }

}
