package com.example.JavaWebCrawler.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "product")
public class Product {

    @Id
    private String maSanPham;          // Mã sản phẩm (Chính)

    private String name;               // Tên sản phẩm
    private double oldPrice;           // Giá cũ
    private double newPrice;           // Giá mới
    private String percentDiscount;    // % Giảm giá
    private String link;               // Đường dẫn sản phẩm
    private String imageUrl;           // URL hình ảnh
    private String installment;        // Thông tin trả góp

    @DBRef
    private ThuongHieuDim thuongHieu; // Thương hiệu

    @DBRef
    private TienIchDim tienIch; // Tiện ích

    @DBRef
    private LoaiMayLocNuocDim loaiMayLocNuoc; // Loại máy lọc nước

    @DBRef
    private ChungLoaiDim chungLoai; // Chủng loại

    @DBRef
    private SoLoiLocDim soLoiLoc; // Số lõi lọc

    @DBRef
    private CongSuatLocDim congSuatLoc; // Công suất lọc

    @DBRef
    private DungTichBinhChuaDim dungTichBinhChua; // Dung tích bình chứa

    @DBRef
    private CongNgheLocDim congNgheLoc; // Công nghệ lọc

    private String tinhNangNoiBat; // Tính năng nổi bật

    @DBRef
    private VoTuDim voTu; // Vỏ tủ

    private String congSuatTieuThu; // Công suất tiêu thụ
    private String kichThuoc; // Kích thước

    @DBRef
    private LoiLocDim loiLoc; // Lõi lọc

    @DBRef
    private BaoHanhDim baoHanh; // Bảo hành

    @DBRef
    private XuatXuDim xuatXu; // Xuất xứ

    private LocalDateTime update_at; // Ngày cập nhật
}
