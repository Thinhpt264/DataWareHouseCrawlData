package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.Config;
import com.example.JavaWebCrawler.entities.Product;
import com.example.JavaWebCrawler.entities.TienIchDim;
import com.example.JavaWebCrawler.entities.XuatXuDim;
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
    @Override
    public boolean save(XuatXuDim c) {
        return xuatXuDimRepository.save(c) != null;
    }

    @Override
    public XuatXuDim findOrCreated(String name) {
            XuatXuDim xuatXuDim = xuatXuDimRepository.findByName(name);
        if (xuatXuDim == null) {
            // Nếu không tìm thấy, tạo mới
            xuatXuDim = new XuatXuDim();
            xuatXuDim.setName(name);
            xuatXuDim = xuatXuDimRepository.save(xuatXuDim); // Lưu vào database
        }
            return xuatXuDim;
    }
}
