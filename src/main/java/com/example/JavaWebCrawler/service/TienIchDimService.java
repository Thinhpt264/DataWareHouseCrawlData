package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.ThuongHieuDim;
import com.example.JavaWebCrawler.entities.TienIchDim;

public interface TienIchDimService {

    public TienIchDim findByName(String name);

    public TienIchDim findOrCreateByName (String name);
}
