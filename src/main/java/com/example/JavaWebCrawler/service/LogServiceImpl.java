package com.example.JavaWebCrawler.service;

import com.example.JavaWebCrawler.entities.Config;
import com.example.JavaWebCrawler.entities.Log;
import com.example.JavaWebCrawler.repository.ConfigRepository;
import com.example.JavaWebCrawler.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService{
    @Autowired
    private LogRepository logRepository;
    @Override
    public boolean save(Log log) {
        return logRepository.save(log) != null;
    }
}
