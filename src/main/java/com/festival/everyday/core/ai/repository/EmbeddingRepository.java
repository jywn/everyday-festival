package com.festival.everyday.core.ai.repository;

import com.festival.everyday.core.ai.exception.PgVectorFailedException;
import lombok.RequiredArgsConstructor;
import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmbeddingRepository {

    // jdbcTemplate 을 활용해 직접 쿼리를 작성한다.
    private final JdbcTemplate jdbcTemplate;

    private PGobject toPgVector(float[] embedding) {

        PGobject vector = new PGobject();
        vector.setType("vector");

        try {
            vector.setValue(Arrays.toString(embedding).replace(" ", ""));
        } catch (SQLException e) {
            throw new PgVectorFailedException();
        }
        return vector;
    }

    public void saveCompany(Long companyId, float[] embedding) {
        String sql = "INSERT INTO company_embeddings (company_id, embedding) VALUES (?, ?)";
        jdbcTemplate.update(sql, companyId, toPgVector(embedding));
    }

    public void saveFestival(Long festivalId, float[] embedding) {
        String sql = "INSERT INTO festival_embeddings (festival_id, embedding) VALUES (?, ?)";
        jdbcTemplate.update(sql, festivalId, toPgVector(embedding));
    }

    public String findFestivalEmbeddings(Long festivalId) {
        String sql = "SELECT embedding FROM festival_embeddings WHERE festival_id = ?;";
        return jdbcTemplate.queryForObject(sql, String.class, festivalId);
    }

    public String findCompanyEmbeddings(Long companyId) {
        String sql = "SELECT embedding FROM company_embeddings WHERE company_id = ?;";
        return jdbcTemplate.queryForObject(sql, String.class, companyId);
    }

    public List<Long> findRecommendedFestivals(float[] embedding, int limit) {
        String sql = "SELECT festival_id FROM festival_embeddings " +
                "ORDER BY embedding <=> ? LIMIT ?";

        return jdbcTemplate.queryForList(sql, Long.class, toPgVector(embedding), limit);
    }

    public List<Long> findRecommendedCompanies(float[] embedding, int limit) {
        String sql = "SELECT company_id FROM company_embeddings " +
                "ORDER BY embedding <=> ? LIMIT ?";

        return jdbcTemplate.queryForList(sql, Long.class, toPgVector(embedding), limit);
    }

}
