package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.ThuongHieuDim;
import com.example.JavaWebCrawler.repository.ThuongHieuDimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThuongHieuDimServiceImpl implements ThuongHieuDimService{
    @Autowired
    private ThuongHieuDimRepository thuongHieuDimRepository;

    @Override
    public ThuongHieuDim findByName(String name) {
        ThuongHieuDim thuongHieuDim = thuongHieuDimRepository.findByName(name);
            return thuongHieuDim;
    }

    @Override
    public boolean save(ThuongHieuDim t) {
        return thuongHieuDimRepository.save(t) != null;
    }
}
