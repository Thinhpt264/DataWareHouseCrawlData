package com.example.JavaWebCrawler.repository;

import com.example.JavaWebCrawler.entities.BaoHanhDim;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BaoHanhDimRepository extends MongoRepository<BaoHanhDim, Integer> {
    @Query("{ 'name': ?0 }")
    public BaoHanhDim findByName(String name);
}
