package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.CongNgheLocDim;
import com.example.JavaWebCrawler.entities.CongSuatLocDim;

public interface CongNgheLocDimService {
    public CongNgheLocDim findOrCreateByName(String name);

    public CongNgheLocDim findByName(String name);
}
