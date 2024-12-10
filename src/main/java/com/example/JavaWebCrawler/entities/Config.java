package com.example.JavaWebCrawler.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "config")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Config {
    @Id
    @AutoIncrement(collectionName = "config") // Tự động tăng ID cho collection "log"
    private int id;

    @Indexed(unique = true)
    private String key_name;

    private String value;
    private String description;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
