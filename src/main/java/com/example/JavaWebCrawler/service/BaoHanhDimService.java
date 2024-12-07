package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.BaoHanhDim;

public interface BaoHanhDimService {

    public BaoHanhDim findOrCreateByName(String name);
}
