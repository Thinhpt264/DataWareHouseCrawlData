package com.example.JavaWebCrawler.repository;

import com.example.JavaWebCrawler.entities.ChungLoaiDim;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChungLoaiDimRepository extends MongoRepository<ChungLoaiDim, Integer> {
    @Query("{ 'name': ?0 }")
    public ChungLoaiDim findByName(String name);
}
