package com.example.JavaWebCrawler.repository;

import com.example.JavaWebCrawler.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends MongoRepository<Product, String> {

}
