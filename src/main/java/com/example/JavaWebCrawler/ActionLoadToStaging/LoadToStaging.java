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
    private WebCrawler crawler; // Inject WebCrawler do Spring quản lý

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
        List<Product> result = new ArrayList<>();
        List<DataRaw> products = crawler.crawlProducts();
        LocalDateTime localDateTime = LocalDateTime.now();
        Log log = new Log();
        log.setLevel("Info");
        log.setMessage("process Data to Staging");
        log.setContext("Data Staging");
        log.setTimestamp(localDateTime);
        logService.save(log);

        for (DataRaw dataRaw : products) {
            Product product = new Product();
            product.setMaSanPham(dataRaw.getMaSanPham());
            product.setName(dataRaw.getName());
            String odlPrice = dataRaw.getOldPrice();
            String regex = "^[\\d.]+";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(odlPrice);
            if (matcher.find()) {
                String result2 = matcher.group();
                // Loại bỏ dấu chấm nếu cần thiết
                double numericValue = Double.parseDouble(result2.replace(".", ""));
                product.setOldPrice(numericValue);
            } else {
                System.out.println("Không tìm thấy số hợp lệ.");
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
            }
            product.setPercentDiscount(dataRaw.getPercentDiscount());
            product.setLink(dataRaw.getLink());
            product.setImageUrl(dataRaw.getImageUrl());
            product.setInstallment(dataRaw.getInstallment());
                    String thuongHieu = dataRaw.getThuongHieu();
                    ThuongHieuDim thuongHieuDim;
                    if (thuongHieu != null) {
                        thuongHieuDim = thuongHieuDimService.findOrCreateByName(thuongHieu);
                    } else {
                        thuongHieuDim = thuongHieuDimService.findByName("Chưa có thương hiệu");
                    };
                    product.setThuongHieu(thuongHieuDim);

            TienIchDim tienIchDim;
            String tienIch = dataRaw.getTienIch();
            if(tienIch != null) {
                tienIchDim = tienIchDimService.findOrCreateByName(tienIch);
            }else  {
                tienIchDim = tienIchDimService.findByName("Tiện ích không có sẵn");
            }
            product.setTienIch(tienIchDim);

                    String loaiMayLocNuoc = dataRaw.getLoaiMayLocNuoc();
                    LoaiMayLocNuocDim loaiMayLocNuocDim;
                    if(loaiMayLocNuoc != null){
                        loaiMayLocNuocDim = loaiMayLocNuocService.findOrCreateByName(loaiMayLocNuoc);
                    }else{
                        loaiMayLocNuocDim = loaiMayLocNuocService.findByName("Loại máy lọc nước chưa có sẵn");
                    }
                    product.setLoaiMayLocNuoc(loaiMayLocNuocDim);

            String chungLoai = dataRaw.getChungLoai();
            ChungLoaiDim chungLoaiDim;
            if(chungLoai != null){
                chungLoaiDim = chungLoaiDimService.findOrCreateByName(chungLoai);
            }else{
                chungLoaiDim = chungLoaiDimService.findOrCreateByName("Chủng Loại chưa có sẵn");
            }
            product.setChungLoai(chungLoaiDim);

                    SoLoiLocDim soLoiLocDim;
                    String soLoiLoc = dataRaw.getSoLoiLoc();
                    if(soLoiLoc != null) {
                        soLoiLocDim = soloillocDimService.findOrSave(soLoiLoc);
                    }else {
                        soLoiLocDim = soloillocDimService.findByName("0 lõi");
                    }
                    product.setSoLoiLoc(soLoiLocDim);

            String congSuatLoc = dataRaw.getCongSuatLoc();
            CongSuatLocDim congSuatLocDim ;
            if(congSuatLoc != null) {
                congSuatLocDim = congSuatLocDimService.findOrCreateByName(congSuatLoc);
            }else {
                congSuatLocDim = congSuatLocDimService.findOrCreateByName("Hiện chưa rõ số lõi lọc cụ thể");
            }
            product.setCongSuatLoc(congSuatLocDim);

                    String dungTichBinhChua = dataRaw.getDungTichBinhChua();
                    DungTichBinhChuaDim dungTichBinhChuaDim;
                    if(dungTichBinhChua != null) {
                        dungTichBinhChuaDim = dungTichBinhChuaDimService.findOrCreateByName(dungTichBinhChua);
                    }else {
                        dungTichBinhChuaDim = dungTichBinhChuaDimService.findOrCreateByName("Chưa rõ dung tích");
                    }
                    product.setDungTichBinhChua(dungTichBinhChuaDim);

            String congNgheLoc = dataRaw.getCongNgheLoc();
            CongNgheLocDim congNgheLocDim;
            if(congNgheLoc != null) {
                congNgheLocDim =congNgheLocDimService.findOrCreateByName(congNgheLoc);
            }else {
                congNgheLocDim =congNgheLocDimService.findOrCreateByName("Chưa rõ công nghệ lọc");
            }
            product.setCongNgheLoc(congNgheLocDim);
            String tinhnangnoibat = dataRaw.getTinhNangNoiBat();
            if(tinhnangnoibat != null) {
                product.setTinhNangNoiBat(tinhnangnoibat);
            }else {
                product.setTinhNangNoiBat("Chưa cập nhật");
            }
            product.setTinhNangNoiBat(dataRaw.getTinhNangNoiBat());

            String voTu = dataRaw.getVoTu();
            VoTuDim voTuDim;
            if(voTu != null) {
                voTuDim = voTuDimService.findOrCreateByName(voTu);
            }else {
                voTuDim = voTuDimService.findOrCreateByName("Chưa rõ vỏ tủ");
            }
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
                loiLocDim = loiLocDimService.findOrCreated(loiloc);
            }else{
                loiLocDim = loiLocDimService.findOrCreated("Không có");
            }
            product.setLoiLoc(loiLocDim);
            String baohanh = dataRaw.getBaoHanh();
            BaoHanhDim baoHanhDim;
            if(loiloc != null) {
                baoHanhDim = baoHanhDimService.findOrCreateByName(baohanh);
            }else{
                baoHanhDim = baoHanhDimService.findOrCreateByName("Đã Hết Bảo Hành");
            }
            product.setBaoHanh(baoHanhDim);
            String xuatXu = dataRaw.getXuatXu();
            XuatXuDim xuatXuDim ;
            if(xuatXu != null) {
                xuatXuDim = xuatXuDimService.findOrCreated(xuatXu);
            }else {
                xuatXuDim = xuatXuDimService.findOrCreated("Không rõ nguồn gốc");
            }
            product.setXuatXu(xuatXuDim);
            result.add(product);
        }
        Log log2 = new Log();
        log2.setLevel("Info");
        log2.setMessage("Complete Data to Staging");
        log2.setContext("Data Staging");
        log2.setTimestamp(localDateTime);
        logService.save(log2);
        return result;
    }
}
