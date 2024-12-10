package com.example.JavaWebCrawler.repository;

import com.example.JavaWebCrawler.entities.LoaiMayLocNuocDim;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoaiMayLocNuocDimRepository extends MongoRepository<LoaiMayLocNuocDim, Integer> {

    @Query("{ 'name': ?0 }")
    public LoaiMayLocNuocDim findByName( String name);
}
