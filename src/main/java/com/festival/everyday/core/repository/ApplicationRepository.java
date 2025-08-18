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
    //축제에 지원한 업체 목록
    @Query("""
       SELECT a FROM Application a
       JOIN FETCH a.user u
       WHERE a.festival.id = :festivalId AND TYPE(u) = Company
       """)
    List<Application> findCompanyApplicationsByFestivalId(@Param("festivalId") Long festivalId);

    //축제에 지원한 근로자 목록
    @Query("""
       SELECT a FROM Application a
       JOIN FETCH a.user u
       WHERE a.festival.id = :festivalId AND TYPE(u) = Labor
       """)
    List<Application> findLaborApplicationsByFestivalId(@Param("festivalId") Long festivalId);

    //내가 지원한 지원서 목록
    List<Application> findByUserId(Long userId);

    // 특정 축제에 특정 업체가 지원한 낸 지원서
    @Query("""
       SELECT a FROM Application a
       JOIN a.user u
       WHERE a.festival.id = :festivalId
         AND u.id = :companyId
         AND TYPE(u) = Company
       """)
    Optional<Application> findCompanyApplicationByFestivalAndCompany(@Param("festivalId") Long festivalId,
                                                                     @Param("companyId") Long companyId);

    // 특정 축제에 특정 근로자가 지원한 지원서
    @Query("""
       SELECT a FROM Application a
       JOIN a.user u
       WHERE a.festival.id = :festivalId
         AND u.id = :laborId
         AND TYPE(u) = Labor
       """)
    Optional<Application> findLaborApplicationByFestivalAndLabor(@Param("festivalId") Long festivalId,
                                                                 @Param("laborId") Long laborId);

    Long user(@NotNull User user);

    boolean existsByUserIdAndRecruitId(Long userId, Long recruitId);
}
