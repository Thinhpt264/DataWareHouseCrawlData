package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.Config;
import com.example.JavaWebCrawler.entities.SoLoiLocDim;
import com.example.JavaWebCrawler.entities.XuatXuDim;

public interface XuatXuDimService {
    public boolean save(XuatXuDim c);


    public XuatXuDim findOrCreated(String name);
}
