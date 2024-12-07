package com.example.JavaWebCrawler.repository;

import com.example.JavaWebCrawler.entities.ChungLoaiDim;
import com.example.JavaWebCrawler.entities.LoiLocDim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoiLocDimRepository extends JpaRepository<LoiLocDim, Integer> {
    @Query("from LoiLocDim where name = :name ")
    public LoiLocDim findByName(@Param("name") String name);
}
