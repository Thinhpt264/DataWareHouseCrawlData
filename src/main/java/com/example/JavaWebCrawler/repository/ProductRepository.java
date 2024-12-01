package com.example.JavaWebCrawler.repository;

import com.example.JavaWebCrawler.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends JpaRepository<Product, String> {

}
