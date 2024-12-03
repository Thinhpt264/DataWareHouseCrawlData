package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.ThuongHieuDim;
import com.example.JavaWebCrawler.entities.TienIchDim;
import com.example.JavaWebCrawler.repository.ThuongHieuDimRepository;
import com.example.JavaWebCrawler.repository.TienIchDimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TienIchDimServiceImpl implements TienIchDimService{

    @Autowired
    private TienIchDimRepository tienIchDimRepository;
    @Override
    public TienIchDim findByName(String name) {
        TienIchDim tienIchDim = tienIchDimRepository.findByName(name);
        return tienIchDim;
    }

    @Override
    public boolean save(TienIchDim tienIchDim) {
        return tienIchDimRepository.save(tienIchDim) != null;
    }
}


