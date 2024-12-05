package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.CongSuatLocDim;
import com.example.JavaWebCrawler.entities.LoaiMayLocNuocDim;
import com.example.JavaWebCrawler.repository.CongSuatLocDimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CongSuatLocDimServiceImpl implements CongSuatLocDimService{
    @Autowired
    private CongSuatLocDimRepository congSuatLocDimRepository;
    @Override
    public CongSuatLocDim findOrCreateByName(String name) {
        CongSuatLocDim congSuatLocDim = congSuatLocDimRepository.findByName(name);
        if (congSuatLocDim == null) {
            // Nếu không tìm thấy, tạo mới
            congSuatLocDim = new CongSuatLocDim();
            congSuatLocDim.setName(name);
            congSuatLocDim = congSuatLocDimRepository.save(congSuatLocDim); // Lưu vào database
        }
        return congSuatLocDim;
    }

    @Override
    public CongSuatLocDim findByName(String name) {
        return congSuatLocDimRepository.findByName(name);
    }
}
