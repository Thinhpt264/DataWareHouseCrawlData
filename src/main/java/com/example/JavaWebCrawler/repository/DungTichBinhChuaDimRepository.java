package com.example.JavaWebCrawler.repository;

import com.example.JavaWebCrawler.entities.CongSuatLocDim;
import com.example.JavaWebCrawler.entities.DungTichBinhChuaDim;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DungTichBinhChuaDimRepository extends MongoRepository<DungTichBinhChuaDim, Integer> {
    @Query("{ 'name': ?0 }")
    public DungTichBinhChuaDim findByName( String name);
}
