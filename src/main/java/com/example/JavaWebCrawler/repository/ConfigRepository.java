package com.example.JavaWebCrawler.repository;

import com.example.JavaWebCrawler.entities.Config;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigRepository extends MongoRepository<Config, Integer> {

}
