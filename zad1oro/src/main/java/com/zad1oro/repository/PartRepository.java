package com.zad1oro.repository;

import com.zad1oro.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
    List<Part> findPartsByPriceBetween(double minPrice, double maxPrice);
}
