package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.VoTuDim;
import com.example.JavaWebCrawler.repository.VoTuDimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoTuDimServiceImpl implements  VoTuDimService {
    @Autowired
    private VoTuDimRepository voTuDimRepository;

    @Override
    public VoTuDim findOrCreateByName(String name) {
        VoTuDim voTuDim = voTuDimRepository.findByName(name);
        if (voTuDim == null) {
            // Nếu không tìm thấy, tạo mới
            voTuDim = new VoTuDim();
            voTuDim.setName(name);
            voTuDim = voTuDimRepository.save(voTuDim); // Lưu vào database
        }
        return voTuDim;
    }

    @Override
    public VoTuDim findByName(String name) {
        return voTuDimRepository.findByName(name);
    }
}
