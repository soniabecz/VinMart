package com.example.catalog.repository;

import com.example.catalog.entity.Vinyl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VinylRepository
        extends JpaRepository<Vinyl, Long> {
}
