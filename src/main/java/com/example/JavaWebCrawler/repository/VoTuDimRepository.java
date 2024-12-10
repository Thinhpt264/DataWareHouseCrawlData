package com.example.JavaWebCrawler.repository;

import com.example.JavaWebCrawler.entities.VoTuDim;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VoTuDimRepository extends MongoRepository<VoTuDim,Integer> {
    @Query("{ 'name': ?0 }")
    public VoTuDim findByName( String name);
}
