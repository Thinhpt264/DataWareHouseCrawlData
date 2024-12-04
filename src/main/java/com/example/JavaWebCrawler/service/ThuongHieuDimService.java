package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.ThuongHieuDim;
import com.example.JavaWebCrawler.entities.XuatXuDim;

public interface ThuongHieuDimService {

    public ThuongHieuDim findByName(String name);

    public boolean save(ThuongHieuDim t) ;
    public ThuongHieuDim findOrCreateByName(String name);
}
