package com.example.JavaWebCrawler.repository;

import com.example.JavaWebCrawler.entities.Log;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<Log, Integer> {

}
