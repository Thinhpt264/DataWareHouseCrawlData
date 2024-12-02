package com.example.JavaWebCrawler.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;

@Entity
@Table(name = "xuatxudim")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class XuatXuDim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String name;
}
