package com.example.JavaWebCrawler.ActionLoadToStraging;

import com.example.JavaWebCrawler.WebCrawler;
import com.example.JavaWebCrawler.entities.*;
import com.example.JavaWebCrawler.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoadToStraging {
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
            product.setOldPrice(dataRaw.getOldPrice());
            product.setNewPrice(dataRaw.getNewPrice());
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
            product.setLoaiMayLocNuoc(dataRaw.getLoaiMayLocNuoc());
            product.setChungLoai(dataRaw.getChungLoai());
            SoLoiLocDim soLoiLocDim;
            String soLoiLoc = dataRaw.getSoLoiLoc();
            if(soLoiLoc != null) {
                soLoiLocDim = soloillocDimService.findOrSave(soLoiLoc);
            }else {
                soLoiLocDim = soloillocDimService.findByName("0 lõi");
            }
            product.setSoLoiLoc(soLoiLocDim);
            product.setCongSuatLoc(dataRaw.getCongSuatLoc());
            product.setDungTichBinhChua(dataRaw.getDungTichBinhChua());
            product.setCongNgheLoc(dataRaw.getCongNgheLoc());
            product.setTinhNangNoiBat(dataRaw.getTinhNangNoiBat());
            product.setVoTu(dataRaw.getVoTu());
            product.setCongSuatTieuThu(dataRaw.getCongSuatTieuThu());
            product.setKichThuoc(dataRaw.getKichThuoc());
            product.setLoiLoc(dataRaw.getLoiLoc());
            product.setBaoHanh(dataRaw.getBaoHanh());
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
