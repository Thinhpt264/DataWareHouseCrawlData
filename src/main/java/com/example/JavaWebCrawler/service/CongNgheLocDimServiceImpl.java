package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.AutoIncrementListener;
import com.example.JavaWebCrawler.entities.ChungLoaiDim;
import com.example.JavaWebCrawler.entities.CongNgheLocDim;
import com.example.JavaWebCrawler.entities.CongSuatLocDim;
import com.example.JavaWebCrawler.repository.CongNgheLocDimRepository;
import com.example.JavaWebCrawler.repository.CongSuatLocDimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CongNgheLocDimServiceImpl implements  CongNgheLocDimService{
    @Autowired
    private CongNgheLocDimRepository congNgheLocDimRepository;

    @Autowired
    private AutoIncrementListener autoIncrementListener;
    @Override
    public CongNgheLocDim findOrCreateByName(String name) {
        CongNgheLocDim congNgheLocDim = congNgheLocDimRepository.findByName(name);
        if (congNgheLocDim == null) {
            // Nếu không tìm thấy, tạo mới
            int nextId = autoIncrementListener.getNextSequence("congnghelocdim"); // Lấy ID tiếp theo từ counters
            congNgheLocDim = new CongNgheLocDim(nextId, name); // Gán ID mới
            congNgheLocDim = congNgheLocDimRepository.save(congNgheLocDim); // Lưu vào database
        }
        return congNgheLocDim;
    }

    @Override
    public CongNgheLocDim findByName(String name) {
        return congNgheLocDimRepository.findByName(name);
    }
}
