package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.ChungLoaiDim;
import com.example.JavaWebCrawler.entities.LoaiMayLocNuocDim;

public interface ChungLoaiDimService {
    public ChungLoaiDim findOrCreateByName(String name);

    public ChungLoaiDim findByName(String name);
}
