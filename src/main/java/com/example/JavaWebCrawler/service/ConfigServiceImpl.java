package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.Config;
import com.example.JavaWebCrawler.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigServiceImpl implements ConfigService{
    @Autowired
    private ConfigRepository configRepository;
    @Override
    public boolean save(Config c) {
        return configRepository.save(c) != null;
    }
}
