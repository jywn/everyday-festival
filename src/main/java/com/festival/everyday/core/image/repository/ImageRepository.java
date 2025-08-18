package com.festival.everyday.core.image.repository;

import com.festival.everyday.core.image.domain.Image;
import com.festival.everyday.core.image.domain.OwnerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("SELECT i.url FROM Image i "
            + "WHERE i.ownerType = :ownerType "
            + "AND i.ownerId = :ownerId ")
    Optional<String> findUrlByOwnerTypeAndOwnerId(OwnerType ownerType, Long ownerId);
}
