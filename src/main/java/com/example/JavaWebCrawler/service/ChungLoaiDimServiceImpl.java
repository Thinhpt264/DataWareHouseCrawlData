package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.ChungLoaiDim;
import com.example.JavaWebCrawler.entities.LoaiMayLocNuocDim;
import com.example.JavaWebCrawler.repository.ChungLoaiDimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChungLoaiDimServiceImpl implements ChungLoaiDimService{
    @Autowired
    private ChungLoaiDimRepository chungLoaiDimRepository;
    @Override
    public ChungLoaiDim findOrCreateByName(String name) {
        ChungLoaiDim chungLoaiDim = chungLoaiDimRepository.findByName(name);
        if (chungLoaiDim == null) {
            // Nếu không tìm thấy, tạo mới
            chungLoaiDim = new ChungLoaiDim();
            chungLoaiDim.setName(name);
            chungLoaiDim = chungLoaiDimRepository.save(chungLoaiDim); // Lưu vào database
        }
        return chungLoaiDim;
    }

    @Override
    public ChungLoaiDim findByName(String name) {
        return chungLoaiDimRepository.findByName(name);
    }
}
