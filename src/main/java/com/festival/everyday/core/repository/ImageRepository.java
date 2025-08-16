package com.festival.everyday.core.repository;

import com.festival.everyday.core.domain.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
