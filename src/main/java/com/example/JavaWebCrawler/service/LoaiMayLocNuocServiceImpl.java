package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.AutoIncrementListener;
import com.example.JavaWebCrawler.entities.CongSuatLocDim;
import com.example.JavaWebCrawler.entities.LoaiMayLocNuocDim;
import com.example.JavaWebCrawler.entities.SoLoiLocDim;
import com.example.JavaWebCrawler.repository.LoaiMayLocNuocDimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoaiMayLocNuocServiceImpl implements LoaiMayLocNuocService {

    @Autowired
    private LoaiMayLocNuocDimRepository loaiMayLocNuocDimRepository;

    @Autowired
    private AutoIncrementListener autoIncrementListener;
    @Override
    public LoaiMayLocNuocDim findOrCreateByName(String name) {
        LoaiMayLocNuocDim loaiMayLocNuocDim = loaiMayLocNuocDimRepository.findByName(name);
        if (loaiMayLocNuocDim == null) {
            // Nếu không tìm thấy, tạo mới
            int nextId = autoIncrementListener.getNextSequence("loaimaylocnuocdim"); // Lấy ID tiếp theo từ counters
            loaiMayLocNuocDim = new LoaiMayLocNuocDim(nextId, name); // Gán ID mới
            loaiMayLocNuocDim = loaiMayLocNuocDimRepository.save(loaiMayLocNuocDim); // Lưu vào database
        }
        return loaiMayLocNuocDim;
    }

    @Override
    public LoaiMayLocNuocDim findByName(String name) {
       return loaiMayLocNuocDimRepository.findByName(name);
    }
}
