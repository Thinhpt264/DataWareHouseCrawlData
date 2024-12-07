package com.example.JavaWebCrawler.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
    private double oldPrice;           // Giá cũ
    private double newPrice;           // Giá mới
    private String percentDiscount;    // % Giảm giá
    private String link;               // Đường dẫn sản phẩm
    private String imageUrl;           // URL hình ảnh
    private String installment;        // Thông tin trả góp
    @ManyToOne
    @JoinColumn(name = "Thuong_hieu")
    private ThuongHieuDim thuongHieu;
    @ManyToOne
    @JoinColumn(name = "Tien_ich")    // Thương hiệu
    private TienIchDim tienIch;// Tiện ích
    @ManyToOne
    @JoinColumn(name = "Loai_may_loc_nuoc")
    private LoaiMayLocNuocDim loaiMayLocNuoc;     // Loại máy lọc nước
    @ManyToOne
    @JoinColumn(name = "Chung_loai")
    private ChungLoaiDim chungLoai;          // Chủng loại
    @ManyToOne
    @JoinColumn(name = "So_loi_loc")
    private SoLoiLocDim soLoiLoc;
    @ManyToOne
    @JoinColumn(name = "Cong_suat_loc")// Số lõi lọc
    private CongSuatLocDim congSuatLoc;        // Công suất lọc
    @ManyToOne
    @JoinColumn(name = "Dung_tich_binh_chua")
    private DungTichBinhChuaDim dungTichBinhChua;   // Dung tích bình chứa
    @ManyToOne
    @JoinColumn(name = "Cong_nghe_loc")
    private CongNgheLocDim congNgheLoc;        // Công nghệ lọc
    private String tinhNangNoiBat;     // Tính năng nổi bật
    @ManyToOne
    @JoinColumn(name = "Vo_tu")
    private VoTuDim voTu;               // Vỏ tủ
    private String congSuatTieuThu;    // Công suất tiêu thụ
    private String kichThuoc;// Kích thước
    @ManyToOne
    @JoinColumn(name = "Loi_loc")
    private LoiLocDim loiLoc;             // Lõi lọc
    @ManyToOne
    @JoinColumn(name = "Bao_hanh")
    private BaoHanhDim baoHanh;
    @ManyToOne
    @JoinColumn(name = "Xuat_xu")// Bảo hành
    private XuatXuDim xuatXu;             // Xuất xứ
    private LocalDateTime update_at;

    // Constructor không tham số


}