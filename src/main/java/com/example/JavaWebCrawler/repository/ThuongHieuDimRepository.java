package com.example.JavaWebCrawler.repository;

import com.example.JavaWebCrawler.entities.Config;
import com.example.JavaWebCrawler.entities.ThuongHieuDim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface ThuongHieuDimRepository extends JpaRepository<ThuongHieuDim, Integer> {

    @Query("from ThuongHieuDim where name = :name ")
    public ThuongHieuDim findByName(@Param("name") String name);
}
