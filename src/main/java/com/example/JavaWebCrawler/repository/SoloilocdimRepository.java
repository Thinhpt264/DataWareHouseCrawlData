package com.example.JavaWebCrawler.repository;

import com.example.JavaWebCrawler.entities.SoLoiLocDim;
import com.example.JavaWebCrawler.entities.XuatXuDim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SoloilocdimRepository extends JpaRepository<SoLoiLocDim, Integer> {

    @Query("from SoLoiLocDim where name = :name ")
    public SoLoiLocDim findByName(@Param("name") String name);


}
