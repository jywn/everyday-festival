package com.festival.everyday.core.image.repository;

import com.festival.everyday.core.image.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
