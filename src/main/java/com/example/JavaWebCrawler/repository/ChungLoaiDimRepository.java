package com.example.JavaWebCrawler.repository;

import com.example.JavaWebCrawler.entities.ChungLoaiDim;
import com.example.JavaWebCrawler.entities.LoaiMayLocNuocDim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChungLoaiDimRepository extends JpaRepository<ChungLoaiDim, Integer> {
    @Query("from ChungLoaiDim where name = :name ")
    public ChungLoaiDim findByName(@Param("name") String name);
}
