package com.example.JavaWebCrawler.ActionWebCrawl;

import com.example.JavaWebCrawler.WebCrawler;
import com.example.JavaWebCrawler.entities.Config;
import com.example.JavaWebCrawler.entities.Log;
import com.example.JavaWebCrawler.entities.Product;
import com.example.JavaWebCrawler.service.ConfigService;
import com.example.JavaWebCrawler.service.LogService;
import com.example.JavaWebCrawler.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;


@Component
public class WebCrawlerScheduler {
    @Autowired
    private ProductService productService;
    @Autowired
    private LogService logService;
    @Autowired
    private ConfigService configService;
    @Autowired
    private WebCrawler crawler; // Inject WebCrawler do Spring quản lý

    @Scheduled(fixedRate = 300000) // Chạy mỗi 5 phút (300.000 ms)
    public void crawlAndSaveProducts() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Chạy tác vụ lúc: " + now);
        Config config = new Config();
        config.setKey_name("crawl status");
        config.setValue("in process");
        config.setDescription("Quá Trình Crawl đang thực hiện");
        config.setCreated_at(LocalDateTime.now());
        config.setUpdated_at(LocalDateTime.now());
        if(configService.save(config) == false) {
            Log log = new Log();
            log.setLevel("ERROR");
            log.setTimestamp(LocalDateTime.now());
            log.setContext("Config process");
            log.setMessage("Lỗi Khi Cập Nhật Config");
            logService.save(log);
        } else {
            Log log = new Log();
            log.setLevel("INFO");
            log.setTimestamp(LocalDateTime.now());
            log.setContext("Config process");
            log.setMessage("Cập Nhật Config Thành Công");
            logService.save(log);
        }

        List<Product> products = crawler.crawlProducts();

        for (Product product : products) {
            product.setUpdate_at(LocalDateTime.now());
            productService.save(product);
            Log log = new Log();
            log.setLevel("INFO");
            log.setTimestamp(LocalDateTime.now());
            log.setContext("Product process");
            log.setMessage("Cập Nhật Sản Phẩm: " + product.getName() + " vào DB hoàn Tất");

            logService.save(log);
        }
        Config config2 = new Config();
        config2.setKey_name("crawl status");
        config2.setValue("complete");
        config2.setDescription("Quá Trình Crawl đã hoàn thành");
        config2.setCreated_at(LocalDateTime.now());
        config2.setUpdated_at(LocalDateTime.now());
        if(configService.save(config2) == false) {
            Log log = new Log();
            log.setLevel("ERROR");
            log.setTimestamp(LocalDateTime.now());
            log.setContext("Config process");
            log.setMessage("Lỗi Khi Cập Nhật Config");
            logService.save(log);
        }
        Log log = new Log();
        log.setLevel("INFO");
        log.setTimestamp(LocalDateTime.now());
        log.setContext("Product process");
        log.setMessage("Hoàn Thành Quá Trình Crawl Dữ Liệu");
        logService.save(log);
        System.out.println("Chạy xong tác vụ");
    }

}
