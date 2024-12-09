package com.example.JavaWebCrawler.ActionLoadToStaging;

import com.example.JavaWebCrawler.WebCrawler;
import com.example.JavaWebCrawler.entities.*;
import com.example.JavaWebCrawler.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class LoadToStaging {
    @Autowired
    private WebCrawler crawler; // khai báo WebCrawler do Spring quản lý

    @Autowired
    private LogService logService;
    @Autowired
    private XuatXuDimService xuatXuDimService;
    @Autowired
    private ThuongHieuDimService thuongHieuDimService;
    @Autowired
    private SoloillocDimService soloillocDimService;
    @Autowired
    private TienIchDimService tienIchDimService;
    @Autowired
    private LoaiMayLocNuocService loaiMayLocNuocService;
    @Autowired
    private ChungLoaiDimService chungLoaiDimService;
    @Autowired
    private CongSuatLocDimService congSuatLocDimService;
    @Autowired
    private DungTichBinhChuaDimService dungTichBinhChuaDimService;
    @Autowired
    private CongNgheLocDimService congNgheLocDimService;
    @Autowired
    private VoTuDimService voTuDimService;
    @Autowired
    private LoiLocDimService loiLocDimService;
    @Autowired
    private BaoHanhDimService baoHanhDimService;

    public List<Product> dataStraging() {
//       1.Thu thập dữ liệu từ WebCrawler
        List<DataRaw> products = crawler.crawlProducts();
//        2.Tạo List<Product> result
        List<Product> result = new ArrayList<>();

//      2.Ghi log "process Data to Staging"
//        Bắt đầu quá trình load to staging
        LocalDateTime localDateTime = LocalDateTime.now();
        Log log = new Log();
        log.setLevel("Info");
        log.setMessage("process Data to Staging");
        log.setContext("Data Staging");
        log.setTimestamp(localDateTime);
        logService.save(log);

//        4.Kiểm tra từng phần tử của dữ liệu thô để transform
        for (DataRaw dataRaw : products) {
//        5.Tạo đối tượng sản phẩm(Product product)
            Product product = new Product();
//          5.1 Kiểm tra các field cần dim
//          5.1.1 Set giá trị đã crawl được
//          6.Lưu giá trị vào product
            product.setMaSanPham(dataRaw.getMaSanPham());
            product.setName(dataRaw.getName());
            String odlPrice = dataRaw.getOldPrice();
            String regex = "^[\\d.]+";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(odlPrice);
            // Hợp lệ, chuyển sang kiểu số
            if (matcher.find()) {
                String result2 = matcher.group();
                // Loại bỏ dấu chấm nếu cần thiết
                double numericValue = Double.parseDouble(result2.replace(".", ""));
                product.setOldPrice(numericValue);
            } else {
                // không hợp lệ, set mặc định là 0
                System.out.println("Không tìm thấy số hợp lệ.");
                product.setOldPrice(0);
            }
            // Xử lý newPrice
            String newPrice = dataRaw.getNewPrice();
            Matcher matcher1 = pattern.matcher(newPrice);
            if (matcher1.find()) {
                String result2 = matcher1.group();
                // Loại bỏ dấu chấm nếu cần thiết
                double numericValue = Double.parseDouble(result2.replace(".", ""));
                product.setNewPrice(numericValue);
            } else {
                System.out.println("Không tìm thấy newPrice hợp lệ.");
                product.setNewPrice(0);
            }
            //            6.Lưu giá trị vào product
            product.setPercentDiscount(dataRaw.getPercentDiscount());
            product.setLink(dataRaw.getLink());
            product.setImageUrl(dataRaw.getImageUrl());
            product.setInstallment(dataRaw.getInstallment());
//          5.2 Kiểm tra giá trị null các field có dim
            String thuongHieu = dataRaw.getThuongHieu();
            ThuongHieuDim thuongHieuDim;
            if (thuongHieu != null) {
//          5.2.2 Kiểm tra đã tồn tại trong dim
//                Thông qua lớp Service để kiểm tra giá trị đã tồn tại trong bảng dim hay chưa
                thuongHieuDim = thuongHieuDimService.findOrCreateByName(thuongHieu);
            } else {
//          5.2.1 Set giá trị mặc định
                thuongHieuDim = thuongHieuDimService.findByName("Chưa có thương hiệu");
            };
            //            6.Lưu giá trị vào product
            product.setThuongHieu(thuongHieuDim);

            TienIchDim tienIchDim;
            String tienIch = dataRaw.getTienIch();
            if(tienIch != null) {
//          5.2.2 Kiểm tra đã tồn tại trong dim
//                Thông qua lớp Service để kiểm tra giá trị đã tồn tại trong bảng dim hay chưa
                tienIchDim = tienIchDimService.findOrCreateByName(tienIch);
            }else  {
                tienIchDim = tienIchDimService.findByName("Tiện ích không có sẵn");
            }
            //            6.Lưu giá trị vào product
            product.setTienIch(tienIchDim);

            String loaiMayLocNuoc = dataRaw.getLoaiMayLocNuoc();
            LoaiMayLocNuocDim loaiMayLocNuocDim;
            if(loaiMayLocNuoc != null){
//          5.2.2 Kiểm tra đã tồn tại trong dim
//                Thông qua lớp Service để kiểm tra giá trị đã tồn tại trong bảng dim hay chưa
                loaiMayLocNuocDim = loaiMayLocNuocService.findOrCreateByName(loaiMayLocNuoc);
            }else{
                loaiMayLocNuocDim = loaiMayLocNuocService.findByName("Loại máy lọc nước chưa có sẵn");
            }
            //            6.Lưu giá trị vào product
            product.setLoaiMayLocNuoc(loaiMayLocNuocDim);

            String chungLoai = dataRaw.getChungLoai();
            ChungLoaiDim chungLoaiDim;
            if(chungLoai != null){
//          5.2.2 Kiểm tra đã tồn tại trong dim
//                Thông qua lớp Service để kiểm tra giá trị đã tồn tại trong bảng dim hay chưa
                chungLoaiDim = chungLoaiDimService.findOrCreateByName(chungLoai);
            }else{
                chungLoaiDim = chungLoaiDimService.findOrCreateByName("Chủng Loại chưa có sẵn");
            }
            //            6.Lưu giá trị vào product
            product.setChungLoai(chungLoaiDim);

            SoLoiLocDim soLoiLocDim;
            String soLoiLoc = dataRaw.getSoLoiLoc();
            if(soLoiLoc != null) {
//          5.2.2 Kiểm tra đã tồn tại trong dim
//                Thông qua lớp Service để kiểm tra giá trị đã tồn tại trong bảng dim hay chưa

                soLoiLocDim = soloillocDimService.findOrSave(soLoiLoc);
            }else {
                soLoiLocDim = soloillocDimService.findByName("0 lõi");
            }
            //            6.Lưu giá trị vào product
            product.setSoLoiLoc(soLoiLocDim);

            String congSuatLoc = dataRaw.getCongSuatLoc();
            CongSuatLocDim congSuatLocDim ;
            if(congSuatLoc != null) {
//          5.2.2 Kiểm tra đã tồn tại trong dim
//                Thông qua lớp Service để kiểm tra giá trị đã tồn tại trong bảng dim hay chưa

                congSuatLocDim = congSuatLocDimService.findOrCreateByName(congSuatLoc);
            }else {
                congSuatLocDim = congSuatLocDimService.findOrCreateByName("Hiện chưa rõ số lõi lọc cụ thể");
            }
            //            6.Lưu giá trị vào product
            product.setCongSuatLoc(congSuatLocDim);

            String dungTichBinhChua = dataRaw.getDungTichBinhChua();
            DungTichBinhChuaDim dungTichBinhChuaDim;
            if(dungTichBinhChua != null) {
//          5.2.2 Kiểm tra đã tồn tại trong dim
//                Thông qua lớp Service để kiểm tra giá trị đã tồn tại trong bảng dim hay chưa

                dungTichBinhChuaDim = dungTichBinhChuaDimService.findOrCreateByName(dungTichBinhChua);
            }else {
                dungTichBinhChuaDim = dungTichBinhChuaDimService.findOrCreateByName("Chưa rõ dung tích");
            }
            //            6.Lưu giá trị vào product
            product.setDungTichBinhChua(dungTichBinhChuaDim);

            String congNgheLoc = dataRaw.getCongNgheLoc();
            CongNgheLocDim congNgheLocDim;

            if(congNgheLoc != null) {
//          5.2.2 Kiểm tra đã tồn tại trong dim
//                Thông qua lớp Service để kiểm tra giá trị đã tồn tại trong bảng dim hay chưa
                congNgheLocDim =congNgheLocDimService.findOrCreateByName(congNgheLoc);
            }else {
                congNgheLocDim =congNgheLocDimService.findOrCreateByName("Chưa rõ công nghệ lọc");
            }
            //            6.Lưu giá trị vào product
            product.setCongNgheLoc(congNgheLocDim);
            String tinhnangnoibat = dataRaw.getTinhNangNoiBat();
            if(tinhnangnoibat != null) {

                product.setTinhNangNoiBat(tinhnangnoibat);
            }else {
                product.setTinhNangNoiBat("Chưa cập nhật");
            }


            String voTu = dataRaw.getVoTu();
            VoTuDim voTuDim;
            if(voTu != null) {
//          5.2.2 Kiểm tra đã tồn tại trong dim
//                Thông qua lớp Service để kiểm tra giá trị đã tồn tại trong bảng dim hay chưa
                voTuDim = voTuDimService.findOrCreateByName(voTu);
            }else {
                voTuDim = voTuDimService.findOrCreateByName("Chưa rõ vỏ tủ");
            }
            //            6.Lưu giá trị vào product
            product.setVoTu(voTuDim);
            String Congsuattieuthu = dataRaw.getCongSuatTieuThu();
            if(Congsuattieuthu != null) {
                product.setCongSuatTieuThu(Congsuattieuthu);
            }else {
                product.setCongSuatTieuThu("Không có");
            }
            String kichthuoc = dataRaw.getKichThuoc();
            if(kichthuoc != null) {
                product.setKichThuoc(kichthuoc);
            }else {
                product.setKichThuoc("Không có thông tin");
            }
            String loiloc = dataRaw.getLoiLoc();
            LoiLocDim loiLocDim;
            if(loiloc != null) {
//          5.2.2 Kiểm tra đã tồn tại trong dim
//                Thông qua lớp Service để kiểm tra giá trị đã tồn tại trong bảng dim hay chưa
                loiLocDim = loiLocDimService.findOrCreated(loiloc);
            }else{
                loiLocDim = loiLocDimService.findOrCreated("Không có");
            }
            //            6.Lưu giá trị vào product
            product.setLoiLoc(loiLocDim);
            String baohanh = dataRaw.getBaoHanh();
            BaoHanhDim baoHanhDim;
            if(baohanh != null) {
//          5.2.2 Kiểm tra đã tồn tại trong dim
//                Thông qua lớp Service để kiểm tra giá trị đã tồn tại trong bảng dim hay chưa
                baoHanhDim = baoHanhDimService.findOrCreateByName(baohanh);
            }else{
                baoHanhDim = baoHanhDimService.findOrCreateByName("Đã Hết Bảo Hành");
            }
//            6.Lưu giá trị vào product
            product.setBaoHanh(baoHanhDim);
            String xuatXu = dataRaw.getXuatXu();
            XuatXuDim xuatXuDim ;
            if(xuatXu != null) {
//          5.2.2 Kiểm tra đã tồn tại trong dim
//                Thông qua lớp Service để kiểm tra giá trị đã tồn tại trong bảng dim hay chưa
                xuatXuDim = xuatXuDimService.findOrCreated(xuatXu);
            }else {
                xuatXuDim = xuatXuDimService.findOrCreated("Không rõ nguồn gốc");
            }
//          6.Lưu giá trị vào product
            product.setXuatXu(xuatXuDim);
//          7.Gán giá trị vào result tạo ở bước 2
            result.add(product);
        }
//        8.Ghi log "Complete Data to Staging"
        Log log2 = new Log();
        log2.setLevel("Info");
        log2.setMessage("Complete Data to Staging");
        log2.setContext("Data Staging");
        log2.setTimestamp(localDateTime);
        logService.save(log2);
        return result;
    }
}