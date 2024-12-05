package com.example.JavaWebCrawler.repository;

import com.example.JavaWebCrawler.entities.CongNgheLocDim;
import com.example.JavaWebCrawler.entities.CongSuatLocDim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CongNgheLocDimRepository extends JpaRepository<CongNgheLocDim, Integer> {
    @Query("from CongNgheLocDim where name = :name ")
    public CongNgheLocDim findByName(@Param("name") String name);
}
