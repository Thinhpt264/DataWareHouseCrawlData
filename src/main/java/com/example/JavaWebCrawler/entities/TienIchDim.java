package com.example.JavaWebCrawler.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tienichdim")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TienIchDim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String name;
}