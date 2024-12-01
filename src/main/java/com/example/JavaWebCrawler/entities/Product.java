package com.example.JavaWebCrawler.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.checkerframework.checker.signature.qual.Identifier;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Product {

    @Id
    @Column(name = "Ma_san_pham")
    private String maSanPham;          // Mã sản phẩm (Chính)
    private String name;               // Tên sản phẩm
    private String oldPrice;           // Giá cũ
    private String newPrice;           // Giá mới
    private String percentDiscount;    // % Giảm giá
    private String link;               // Đường dẫn sản phẩm
    private String imageUrl;           // URL hình ảnh
    private String installment;        // Thông tin trả góp
    private String thuongHieu;         // Thương hiệu
    private String tienIch;            // Tiện ích
    private String loaiMayLocNuoc;     // Loại máy lọc nước
    private String chungLoai;          // Chủng loại
    private String soLoiLoc;           // Số lõi lọc
    private String congSuatLoc;        // Công suất lọc
    private String dungTichBinhChua;   // Dung tích bình chứa
    private String congNgheLoc;        // Công nghệ lọc
    private String tinhNangNoiBat;     // Tính năng nổi bật
    private String voTu;               // Vỏ tủ
    private String congSuatTieuThu;    // Công suất tiêu thụ
    private String kichThuoc;          // Kích thước
    private String loiLoc;             // Lõi lọc
    private String baoHanh;            // Bảo hành
    private String xuatXu;             // Xuất xứ
    private LocalDateTime update_at;

    // Constructor không tham số


}