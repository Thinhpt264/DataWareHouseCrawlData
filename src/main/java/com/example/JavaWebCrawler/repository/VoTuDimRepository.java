package com.example.JavaWebCrawler.repository;

import com.example.JavaWebCrawler.entities.VoTuDim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VoTuDimRepository extends JpaRepository<VoTuDim,Integer> {
    @Query("from VoTuDim where name = :name ")
    public VoTuDim findByName(@Param("name") String name);
}
