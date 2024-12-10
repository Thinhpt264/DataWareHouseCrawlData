package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.AutoIncrementListener;
import com.example.JavaWebCrawler.entities.CongNgheLocDim;
import com.example.JavaWebCrawler.entities.CongSuatLocDim;
import com.example.JavaWebCrawler.entities.LoaiMayLocNuocDim;
import com.example.JavaWebCrawler.repository.CongSuatLocDimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CongSuatLocDimServiceImpl implements CongSuatLocDimService{
    @Autowired
    private CongSuatLocDimRepository congSuatLocDimRepository;

    @Autowired
    private AutoIncrementListener autoIncrementListener;
    @Override
    public CongSuatLocDim findOrCreateByName(String name) {
        CongSuatLocDim congSuatLocDim = congSuatLocDimRepository.findByName(name);
        if (congSuatLocDim == null) {
            // Nếu không tìm thấy, tạo mới
            int nextId = autoIncrementListener.getNextSequence("congsuatlocdim"); // Lấy ID tiếp theo từ counters
            congSuatLocDim = new CongSuatLocDim(nextId, name); // Gán ID mới
            congSuatLocDim = congSuatLocDimRepository.save(congSuatLocDim); // Lưu vào database
        }
        return congSuatLocDim;
    }

    @Override
    public CongSuatLocDim findByName(String name) {
        return congSuatLocDimRepository.findByName(name);
    }
}
