package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.Product;
import com.example.JavaWebCrawler.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface ProductService {
   public boolean save(Product product);

   public Product findByMaSanPham(String maSp);
}
