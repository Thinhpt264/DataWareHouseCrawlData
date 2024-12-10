package com.example.JavaWebCrawler.repository;

import com.example.JavaWebCrawler.entities.SoLoiLocDim;
import com.example.JavaWebCrawler.entities.XuatXuDim;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SoloilocdimRepository extends MongoRepository<SoLoiLocDim, Integer> {

    @Query("{ 'name': ?0 }")
    public SoLoiLocDim findByName(@Param("name") String name);


}
