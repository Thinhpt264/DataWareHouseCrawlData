package com.example.JavaWebCrawler.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "log") // Chỉ định tên collection trong MongoDB
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log {

    @Id // Đánh dấu trường id là khóa chính (_id)
    @AutoIncrement(collectionName = "log") // Tự động tăng ID cho collection "log"
    private int id;

    @Indexed // Tạo chỉ mục để tìm kiếm nhanh
    private LocalDateTime timestamp;

    private String level;

    private String message;

    private String context;


}
