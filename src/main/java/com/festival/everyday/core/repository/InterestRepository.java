package com.festival.everyday.core.repository;


import com.festival.everyday.core.domain.interaction.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRepository extends JpaRepository<Interest, Long> {
}
