package com.example.JavaWebCrawler.repository;

import com.example.JavaWebCrawler.entities.CongSuatLocDim;
import com.example.JavaWebCrawler.entities.LoaiMayLocNuocDim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CongSuatLocDimRepository extends JpaRepository<CongSuatLocDim, Integer> {

    @Query("from CongSuatLocDim where name = :name ")
    public CongSuatLocDim findByName(@Param("name") String name);
}
