package com.example.JavaWebCrawler.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "congnghelocdim")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CongNgheLocDim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String name;
}
