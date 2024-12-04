package com.example.JavaWebCrawler.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataRaw {

    private String maSanPham;          // Mã sản phẩm (Chính)
    private String name;               // Tên sản phẩm
    private String oldPrice;           // Giá cũ
    private String newPrice;           // Giá mới
    private String percentDiscount;    // % Giảm giá
    private String link;               // Đường dẫn sản phẩm
    private String imageUrl;           // URL hình ảnh
    private String installment;        // Thông tin trả góp
    private String thuongHieu;
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
    private String baoHanh;

    private String xuatXu;             // Xuất xứ

    // Constructor không tham số
}
