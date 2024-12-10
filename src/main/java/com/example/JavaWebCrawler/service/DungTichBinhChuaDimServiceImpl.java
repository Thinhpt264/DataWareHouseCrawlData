package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.AutoIncrementListener;
import com.example.JavaWebCrawler.entities.CongSuatLocDim;
import com.example.JavaWebCrawler.entities.DungTichBinhChuaDim;
import com.example.JavaWebCrawler.repository.DungTichBinhChuaDimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DungTichBinhChuaDimServiceImpl implements DungTichBinhChuaDimService{
    @Autowired
    private DungTichBinhChuaDimRepository dungTichBinhChuaDimRepository;

    @Autowired
    private AutoIncrementListener autoIncrementListener;
    @Override
    public DungTichBinhChuaDim findOrCreateByName(String name) {
        DungTichBinhChuaDim dungTichBinhChuaDim = dungTichBinhChuaDimRepository.findByName(name);
        if (dungTichBinhChuaDim == null) {
            // Nếu không tìm thấy, tạo mới
            int nextId = autoIncrementListener.getNextSequence("dungtichbinhchuadim"); // Lấy ID tiếp theo từ counters
            dungTichBinhChuaDim = new DungTichBinhChuaDim(nextId, name); // Gán ID mới
            dungTichBinhChuaDim = dungTichBinhChuaDimRepository.save(dungTichBinhChuaDim); // Lưu vào database
        }
        return dungTichBinhChuaDim;
    }

    @Override
    public DungTichBinhChuaDim findByName(String name) {
        return dungTichBinhChuaDimRepository.findByName(name);
    }
}
