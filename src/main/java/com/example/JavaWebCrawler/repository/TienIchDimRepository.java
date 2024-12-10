package com.example.JavaWebCrawler.repository;

import com.example.JavaWebCrawler.entities.TienIchDim;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TienIchDimRepository extends MongoRepository<TienIchDim,Integer> {
    @Query("{ 'name': ?0 }")
    public TienIchDim findByName( String name);
}
