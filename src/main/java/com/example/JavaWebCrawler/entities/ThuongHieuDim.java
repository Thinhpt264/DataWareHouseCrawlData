package com.example.JavaWebCrawler.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "thuonghieudim")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThuongHieuDim {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        
        private String name;
    }
