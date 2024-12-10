package com.example.JavaWebCrawler.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chungloaidim")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChungLoaiDim {
    @Id
    @AutoIncrement(collectionName = "chungloaidim")
    private int id;

    @Indexed(unique = true)
    private String name;
}
