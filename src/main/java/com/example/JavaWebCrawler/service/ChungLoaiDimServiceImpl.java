package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.AutoIncrementListener;
import com.example.JavaWebCrawler.entities.BaoHanhDim;
import com.example.JavaWebCrawler.entities.ChungLoaiDim;
import com.example.JavaWebCrawler.entities.LoaiMayLocNuocDim;
import com.example.JavaWebCrawler.repository.ChungLoaiDimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChungLoaiDimServiceImpl implements ChungLoaiDimService{
    @Autowired
    private ChungLoaiDimRepository chungLoaiDimRepository;

    @Autowired
    private AutoIncrementListener autoIncrementListener;
    @Override
    public ChungLoaiDim findOrCreateByName(String name) {
        ChungLoaiDim chungLoaiDim = chungLoaiDimRepository.findByName(name);
        if (chungLoaiDim == null) {
            // Nếu không tìm thấy, tạo mới
            int nextId = autoIncrementListener.getNextSequence("chungloaidim"); // Lấy ID tiếp theo từ counters
            chungLoaiDim = new ChungLoaiDim(nextId, name); // Gán ID mới
            chungLoaiDim = chungLoaiDimRepository.save(chungLoaiDim); // Lưu vào database
        }
        return chungLoaiDim;
    }

    @Override
    public ChungLoaiDim findByName(String name) {
        return chungLoaiDimRepository.findByName(name);
    }
}
