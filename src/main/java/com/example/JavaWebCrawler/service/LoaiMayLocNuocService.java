package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.LoaiMayLocNuocDim;
import com.example.JavaWebCrawler.entities.SoLoiLocDim;

public interface LoaiMayLocNuocService  {

    public LoaiMayLocNuocDim findOrCreateByName(String name);

    public LoaiMayLocNuocDim findByName(String name);
}
