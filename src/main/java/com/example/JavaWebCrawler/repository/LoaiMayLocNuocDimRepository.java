package com.example.JavaWebCrawler.repository;

import com.example.JavaWebCrawler.entities.LoaiMayLocNuocDim;
import com.example.JavaWebCrawler.entities.SoLoiLocDim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoaiMayLocNuocDimRepository extends JpaRepository<LoaiMayLocNuocDim, Integer> {

    @Query("from LoaiMayLocNuocDim where name = :name ")
    public LoaiMayLocNuocDim findByName(@Param("name") String name);
}
