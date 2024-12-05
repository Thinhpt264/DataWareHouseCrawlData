package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.CongSuatLocDim;
import com.example.JavaWebCrawler.entities.DungTichBinhChuaDim;

public interface DungTichBinhChuaDimService {
    public DungTichBinhChuaDim findOrCreateByName(String name);

    public DungTichBinhChuaDim findByName(String name);
}
