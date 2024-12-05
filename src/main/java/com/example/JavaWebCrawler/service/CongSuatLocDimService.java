package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.CongSuatLocDim;
import com.example.JavaWebCrawler.entities.LoaiMayLocNuocDim;

public interface CongSuatLocDimService {

    public CongSuatLocDim findOrCreateByName(String name);

    public CongSuatLocDim findByName(String name);
}
