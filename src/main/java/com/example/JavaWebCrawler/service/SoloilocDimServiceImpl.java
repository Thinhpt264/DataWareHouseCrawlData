package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.SoLoiLocDim;
import com.example.JavaWebCrawler.repository.SoloilocdimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SoloilocDimServiceImpl implements SoloillocDimService {
    @Autowired
    private SoloilocdimRepository soloilocdimRepository;
    @Override
    public boolean save(SoLoiLocDim c) {
        return soloilocdimRepository.save(c) != null;
    }

    @Override
    public SoLoiLocDim findByName(String name) {
        SoLoiLocDim product = soloilocdimRepository.findByName(name);
            return product;
    }
}
