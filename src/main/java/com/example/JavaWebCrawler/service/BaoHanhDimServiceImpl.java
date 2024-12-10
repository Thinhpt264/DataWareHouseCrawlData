package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.AutoIncrementListener;
import com.example.JavaWebCrawler.entities.BaoHanhDim;
import com.example.JavaWebCrawler.entities.ChungLoaiDim;
import com.example.JavaWebCrawler.entities.ThuongHieuDim;
import com.example.JavaWebCrawler.repository.BaoHanhDimRepository;
import com.example.JavaWebCrawler.repository.ChungLoaiDimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaoHanhDimServiceImpl implements BaoHanhDimService{
    @Autowired
    private BaoHanhDimRepository baoHanhDimRepository;

    @Autowired
    private AutoIncrementListener autoIncrementListener;
    @Override
    public BaoHanhDim findOrCreateByName(String name) {
        BaoHanhDim baoHanhDim = baoHanhDimRepository.findByName(name);
        if (baoHanhDim == null) {
            // Nếu không tìm thấy, tạo mới
            int nextId = autoIncrementListener.getNextSequence("baohanhdim"); // Lấy ID tiếp theo từ counters
            baoHanhDim = new BaoHanhDim(nextId, name); // Gán ID mới
            baoHanhDim = baoHanhDimRepository.save(baoHanhDim); // Lưu vào database
        }
        return baoHanhDim;
    }

}
