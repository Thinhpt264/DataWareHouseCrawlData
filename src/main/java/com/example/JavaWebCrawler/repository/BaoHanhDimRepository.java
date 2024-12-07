package com.example.JavaWebCrawler.repository;

import com.example.JavaWebCrawler.entities.BaoHanhDim;
import com.example.JavaWebCrawler.entities.LoiLocDim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BaoHanhDimRepository extends JpaRepository<BaoHanhDim, Integer> {
    @Query("from BaoHanhDim where name = :name ")
    public BaoHanhDim findByName(@Param("name") String name);
}
