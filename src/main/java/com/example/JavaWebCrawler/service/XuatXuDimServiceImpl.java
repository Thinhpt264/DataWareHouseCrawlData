package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.*;
import com.example.JavaWebCrawler.repository.ConfigRepository;
import com.example.JavaWebCrawler.repository.XuatXuDimRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class XuatXuDimServiceImpl implements XuatXuDimService{
    @Autowired
    private XuatXuDimRepository xuatXuDimRepository;
    @Autowired
    private AutoIncrementListener autoIncrementListener;
    @Override
    public boolean save(XuatXuDim c) {
        return xuatXuDimRepository.save(c) != null;
    }

    @Override
    public XuatXuDim findOrCreated(String name) {
            XuatXuDim xuatXuDim = xuatXuDimRepository.findByName(name);
        if (xuatXuDim == null) {
            // Nếu không tìm thấy, tạo mới
            int nextId = autoIncrementListener.getNextSequence("xuatxudim"); // Lấy ID tiếp theo từ counters
            xuatXuDim = new XuatXuDim(nextId, name); // Gán ID mới
            xuatXuDim = xuatXuDimRepository.save(xuatXuDim); // Lưu vào database
        }
            return xuatXuDim;
    }
}
