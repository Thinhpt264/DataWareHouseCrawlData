package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.AutoIncrementListener;
import com.example.JavaWebCrawler.entities.ThuongHieuDim;
import com.example.JavaWebCrawler.repository.ThuongHieuDimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThuongHieuDimServiceImpl implements ThuongHieuDimService{
    @Autowired
    private ThuongHieuDimRepository thuongHieuDimRepository;
    @Autowired
    private AutoIncrementListener autoIncrementListener;
    @Override
    public ThuongHieuDim findByName(String name) {
        ThuongHieuDim thuongHieuDim = thuongHieuDimRepository.findByName(name);
            return thuongHieuDim;
    }

    @Override
    public boolean save(ThuongHieuDim t) {
        return thuongHieuDimRepository.save(t) != null;
    }

    public ThuongHieuDim findOrCreateByName(String name) {
        System.out.println(name);
        // Tìm kiếm thương hiệu trong database
        ThuongHieuDim thuongHieuDim = thuongHieuDimRepository.findByName(name);
        if (thuongHieuDim == null) {
            // Nếu không tìm thấy, tạo mới
            int nextId = autoIncrementListener.getNextSequence("thuonghieudim"); // Lấy ID tiếp theo từ counters
            thuongHieuDim = new ThuongHieuDim(nextId, name); // Gán ID mới
            thuongHieuDim = thuongHieuDimRepository.save(thuongHieuDim); // Lưu vào database
        }
        return thuongHieuDim;
    }

}
