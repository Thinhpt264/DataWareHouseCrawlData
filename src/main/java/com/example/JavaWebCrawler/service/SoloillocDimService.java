package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.SoLoiLocDim;

public interface SoloillocDimService {
    public SoLoiLocDim findOrSave(String name);

    public SoLoiLocDim findByName(String name);
}
