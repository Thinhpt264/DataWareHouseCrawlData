package com.example.JavaWebCrawler.repository;

import com.example.JavaWebCrawler.entities.CongSuatLocDim;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CongSuatLocDimRepository extends MongoRepository<CongSuatLocDim, Integer> {

    @Query("{ 'name': ?0 }")
    public CongSuatLocDim findByName( String name);
}
