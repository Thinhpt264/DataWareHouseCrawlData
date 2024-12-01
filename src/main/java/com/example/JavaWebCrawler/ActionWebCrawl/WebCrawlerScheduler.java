package com.example.JavaWebCrawler.ActionWebCrawl;

import com.example.JavaWebCrawler.WebCrawler;
import com.example.JavaWebCrawler.entities.Product;
import com.example.JavaWebCrawler.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Component
public class WebCrawlerScheduler {
    @Autowired
    private ProductService productService;


    @Scheduled(fixedRate = 300000) // Chạy mỗi 5 phút (300.000 ms)
    public void crawlAndSaveProducts() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Chạy tác vụ lúc: " + now);

        WebCrawler crawler = new WebCrawler();
        List<Product> products = crawler.crawlProducts();

        for (Product product : products) {
            product.setUpdate_at(LocalDateTime.now());
            productService.save(product);
        }

        System.out.println("Chạy xong tác vụ");
    }

}
