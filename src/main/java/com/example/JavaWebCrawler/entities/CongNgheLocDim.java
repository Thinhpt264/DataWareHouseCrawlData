package com.example.JavaWebCrawler.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "congnghelocdim")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CongNgheLocDim {
    @Id
    @AutoIncrement(collectionName = "congnghelocdim") // Tự động tăng ID cho collection "log"
    private int id;

    @Indexed(unique = true)
    private String name;
}
