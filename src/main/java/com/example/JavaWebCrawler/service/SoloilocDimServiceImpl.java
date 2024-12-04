package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.SoLoiLocDim;
import com.example.JavaWebCrawler.entities.ThuongHieuDim;
import com.example.JavaWebCrawler.repository.SoloilocdimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SoloilocDimServiceImpl implements SoloillocDimService {
    @Autowired
    private SoloilocdimRepository soloilocdimRepository;
    @Override
    public SoLoiLocDim findOrSave(String name) {
        // Tìm kiếm thương hiệu trong database
        SoLoiLocDim soLoiLocDim = soloilocdimRepository.findByName(name);
        if (soLoiLocDim == null) {
            // Nếu không tìm thấy, tạo mới
            soLoiLocDim = new SoLoiLocDim();
            soLoiLocDim.setName(name);
            soLoiLocDim = soloilocdimRepository.save(soLoiLocDim); // Lưu vào database
        }
        return soLoiLocDim;
    }

    @Override
    public SoLoiLocDim findByName(String name) {
        SoLoiLocDim product = soloilocdimRepository.findByName(name);
            return product;
    }
}
