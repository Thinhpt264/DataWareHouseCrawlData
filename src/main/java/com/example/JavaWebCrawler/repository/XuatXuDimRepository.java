package com.example.JavaWebCrawler.repository;

import com.example.JavaWebCrawler.entities.XuatXuDim;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

public interface XuatXuDimRepository extends MongoRepository<XuatXuDim, Integer> {

    @Query("{ 'name': ?0 }")
    public XuatXuDim findByName( String name);


}
