package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.SoLoiLocDim;

public interface SoloillocDimService {
    public boolean save(SoLoiLocDim c);

    public SoLoiLocDim findByName(String name);
}
