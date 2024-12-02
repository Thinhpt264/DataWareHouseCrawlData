package com.example.JavaWebCrawler.repository;

import com.example.JavaWebCrawler.entities.TienIchDim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TienIchDimRepository extends JpaRepository<TienIchDim,Integer> {
    @Query("from TienIchDim where name = :name ")
    public TienIchDim findByName(@Param("name") String name);
}
