package com.example.JavaWebCrawler.repository;

import com.example.JavaWebCrawler.entities.XuatXuDim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface XuatXuDimRepository extends JpaRepository<XuatXuDim, Integer> {

    @Query("from XuatXuDim where name = :name ")
    public XuatXuDim findByName(@Param("name") String name);


}
