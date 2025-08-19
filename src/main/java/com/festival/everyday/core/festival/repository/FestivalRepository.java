package com.festival.everyday.core.festival.repository;

import com.festival.everyday.core.festival.domain.Festival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FestivalRepository extends JpaRepository<Festival,Long>, FestivalRepositoryCustom{

    Optional<Festival> findByIdAndHolderId(Long festivalId, Long holderId);

    @Query("SELECT f from Festival f "
            + "join fetch f.companyRecruit cr "
            + "where f.id = :festivalId")
    public Optional<Festival> findFestivalWithCompanyRecruit(@Param("festivalId") Long festivalId);

    @Query("SELECT f from Festival f "
            + "join fetch f.laborRecruit lr "
            + "where f.id = :festivalId")
    public Optional<Festival> findFestivalWithLaborRecruit(@Param("festivalId") Long festivalId);


}
