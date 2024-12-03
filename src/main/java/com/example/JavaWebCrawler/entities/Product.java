package com.example.JavaWebCrawler.entities;

import jakarta.persistence.*;
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
    @ManyToOne
    @JoinColumn(name = "Thuong_hieu")
    private ThuongHieuDim thuongHieu;
    @ManyToOne
    @JoinColumn(name = "Tien_ich")    // Thương hiệu
    private TienIchDim tienIch;            // Tiện ích
    private String loaiMayLocNuoc;     // Loại máy lọc nước
    private String chungLoai;          // Chủng loại
    @ManyToOne
    @JoinColumn(name = "So_loi_loc")
    private SoLoiLocDim soLoiLoc;           // Số lõi lọc
    private String congSuatLoc;        // Công suất lọc
    private String dungTichBinhChua;   // Dung tích bình chứa
    private String congNgheLoc;        // Công nghệ lọc
    private String tinhNangNoiBat;     // Tính năng nổi bật
    private String voTu;               // Vỏ tủ
    private String congSuatTieuThu;    // Công suất tiêu thụ
    private String kichThuoc;          // Kích thước
    private String loiLoc;             // Lõi lọc
    private String baoHanh;
    @ManyToOne
    @JoinColumn(name = "Xuat_xu")// Bảo hành
    private XuatXuDim xuatXu;             // Xuất xứ
    private LocalDateTime update_at;

    // Constructor không tham số


}