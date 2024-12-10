package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.AutoIncrementListener;
import com.example.JavaWebCrawler.entities.LoaiMayLocNuocDim;
import com.example.JavaWebCrawler.entities.LoiLocDim;
import com.example.JavaWebCrawler.entities.XuatXuDim;
import com.example.JavaWebCrawler.repository.LoiLocDimRepository;
import com.example.JavaWebCrawler.repository.XuatXuDimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoiLocDimServiceImpl implements LoiLocDimService{
    @Autowired
    private LoiLocDimRepository loiLocDimRepository;
    @Autowired
    private AutoIncrementListener autoIncrementListener;
    @Override
    public LoiLocDim findOrCreated(String name) {
            LoiLocDim loiLocDim = loiLocDimRepository.findByName(name);
        if (loiLocDim == null) {
            // Nếu không tìm thấy, tạo mới
            int nextId = autoIncrementListener.getNextSequence("loilocdim"); // Lấy ID tiếp theo từ counters
            loiLocDim = new LoiLocDim(nextId, name); // Gán ID mới
            loiLocDim = loiLocDimRepository.save(loiLocDim); // Lưu vào database
        }
            return loiLocDim;
    }
}
