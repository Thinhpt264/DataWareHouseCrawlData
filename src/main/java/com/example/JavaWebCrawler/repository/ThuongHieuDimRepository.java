package com.example.JavaWebCrawler.repository;

import com.example.JavaWebCrawler.entities.Config;
import com.example.JavaWebCrawler.entities.ThuongHieuDim;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface ThuongHieuDimRepository extends MongoRepository<ThuongHieuDim, Integer> {

    @Query("{ 'name': ?0 }")
    public ThuongHieuDim findByName(@Param("name") String name);
}
