package com.example.JavaWebCrawler.repository;

import com.example.JavaWebCrawler.entities.ChungLoaiDim;
import com.example.JavaWebCrawler.entities.LoiLocDim;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoiLocDimRepository extends MongoRepository<LoiLocDim, Integer> {
    @Query("{ 'name': ?0 }")
    public LoiLocDim findByName( String name);
}
