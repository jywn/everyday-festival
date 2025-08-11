package com.festival.everyday.core.repository;

import com.festival.everyday.core.domain.user.Holder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolderRepository extends JpaRepository<Holder,Long> {
}
