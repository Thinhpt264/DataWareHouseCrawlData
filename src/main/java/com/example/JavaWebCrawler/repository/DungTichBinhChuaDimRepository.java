package com.example.JavaWebCrawler.repository;

import com.example.JavaWebCrawler.entities.CongSuatLocDim;
import com.example.JavaWebCrawler.entities.DungTichBinhChuaDim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DungTichBinhChuaDimRepository extends JpaRepository<DungTichBinhChuaDim, Integer> {
    @Query("from DungTichBinhChuaDim where name = :name ")
    public DungTichBinhChuaDim findByName(@Param("name") String name);
}
