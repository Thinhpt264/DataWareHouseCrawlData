package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.AutoIncrementListener;
import com.example.JavaWebCrawler.entities.Product;
import com.example.JavaWebCrawler.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AutoIncrementListener autoIncrementListener;

    public boolean save(Product product) {
        return  productRepository.save(product) != null;
    }

    public Product findByMaSanPham(String maSp) {
        Product result = new Product();
        Optional<Product> product = productRepository.findById(maSp);
        BeanUtils.copyProperties(result, product);
        return result;
    }
}
