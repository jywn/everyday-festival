package com.festival.everyday.core.repository;

import com.festival.everyday.core.domain.application.Application;
import com.festival.everyday.core.domain.user.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    @Query("""
       SELECT a FROM Application a
       JOIN FETCH a.user u
       WHERE a.festival.id = :festivalId AND TYPE(u) = Company
       """)
    List<Application> findCompanyApplicationsByFestivalId(@Param("festivalId") Long festivalId);

    @Query("""
       SELECT a FROM Application a
       JOIN FETCH a.user u
       WHERE a.festival.id = :festivalId AND TYPE(u) = Labor
       """)
    List<Application> findLaborApplicationsByFestivalId(@Param("festivalId") Long festivalId);

    Optional<Application> findByIdAndUserId(Long id, Long userId);

    @Query("""
        SELECT a FROM Application a
        JOIN FETCH a.festival f
        JOIN FETCH f.holder h
        WHERE a.user.id=:userId
        """)
    List<Application> findApplicationByApplicationIdWithDetails(@Param("userId") Long UserId);


    Long user(@NotNull User user);

    boolean existsByUserIdAndRecruitId(Long userId, Long recruitId);
}
