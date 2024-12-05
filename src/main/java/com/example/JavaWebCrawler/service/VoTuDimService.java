package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.VoTuDim;

public interface VoTuDimService {
    public VoTuDim findOrCreateByName(String name);

    public VoTuDim findByName(String name);
}
