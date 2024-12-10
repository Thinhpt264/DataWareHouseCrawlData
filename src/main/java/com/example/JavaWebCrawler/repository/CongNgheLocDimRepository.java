package com.example.JavaWebCrawler.repository;

import com.example.JavaWebCrawler.entities.CongNgheLocDim;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CongNgheLocDimRepository extends MongoRepository<CongNgheLocDim, Integer> {
    @Query("{ 'name': ?0 }")
    public CongNgheLocDim findByName( String name);
}
