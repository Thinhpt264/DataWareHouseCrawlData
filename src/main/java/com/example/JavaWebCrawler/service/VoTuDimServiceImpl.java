package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.AutoIncrementListener;
import com.example.JavaWebCrawler.entities.VoTuDim;
import com.example.JavaWebCrawler.entities.XuatXuDim;
import com.example.JavaWebCrawler.repository.VoTuDimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoTuDimServiceImpl implements  VoTuDimService {
    @Autowired
    private VoTuDimRepository voTuDimRepository;

    @Autowired
    private AutoIncrementListener autoIncrementListener;

    @Override
    public VoTuDim findOrCreateByName(String name) {
        VoTuDim voTuDim = voTuDimRepository.findByName(name);
        if (voTuDim == null) {
            // Nếu không tìm thấy, tạo mới
            int nextId = autoIncrementListener.getNextSequence("votudim"); // Lấy ID tiếp theo từ counters
            voTuDim = new VoTuDim(nextId, name); // Gán ID mới
            voTuDim = voTuDimRepository.save(voTuDim); // Lưu vào database
        }
        return voTuDim;
    }

    @Override
    public VoTuDim findByName(String name) {
        return voTuDimRepository.findByName(name);
    }
}
