package com.example.JavaWebCrawler;

import com.example.JavaWebCrawler.entities.*;
import com.example.JavaWebCrawler.service.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public  class WebCrawler {
    @Autowired
    private LogService logService;
    @Autowired
    private Environment environment;
    @Autowired
    private  MailService mailServicel;
    // Tạo danh sách để chứa thông tin sản phẩm
    List<DataRaw> productList ;
    public WebCrawler() {
        productList = new ArrayList<>();
    }
    public List<DataRaw> crawlProducts()  {
        // 1 Cấu Hình Selenium
//        System.setProperty("webdriver.chrome.driver", "D:\\WH\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "C:\\project\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // 2 Khởi tạo danh sách sản phẩm
        productList =  new ArrayList<>();
        try {
            //3 Truy cập trang danh sách sản phẩm
            String url = "https://mediamart.vn/may-loc-nuoc";
            driver.get(url);

            // Kiểm tra trạng thái DOM
            wait.until(webDriver -> js.executeScript("return document.readyState").equals("complete"));

            //4 Phân tích HTML danh sách sản phẩm
            WebElement cardElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.card.mb-4")));
            // Lấy HTML của trang
            String html = driver.getPageSource();

            // Sử dụng Jsoup để phân tích HTML
            Document doc = Jsoup.parse(html);
           // 5. Tìm Danh Sách Sản Phẩm
            Elements products = doc.select("div.card.mb-4");


            //6 Bắt đầu crawl từng sản phẩm
            for (Element product : products) {
                //6.1 Tạo đối tượng sản phẩm
                DataRaw productObj = new DataRaw();
                //6.2 Crawl thông tin cơ bản sản phẩm
                // Lấy link sản phẩm
                String link = product.select("a.product-item").attr("href");
                String productUrl = "https://mediamart.vn" + link;

                // Lấy tên sản phẩm qua alt của ảnh
                String name = product.select("img").attr("alt");

                Log log = new Log();
                log.setLevel("INFO");
                log.setTimestamp(LocalDateTime.now());
                log.setContext("Crawl process");
                log.setMessage("Đang Crawl Dữ Liệu của " + name);
                logService.save(log);

                // Lấy giá cũ
                String oldPrice = product.select("p.product-price-regular span").text();
                // Lấy giá mới
                String newPrice = product.select("p.card-text.product-price").text();

                // Lấy URL hình ảnh của sản phẩm
                String imageUrl = product.select("img").attr("src");

                // Tìm thông tin trả góp
                String installment = product.select("span.product-type.product-type-11").text();

                // % Giảm giá
                String percentDiscount = product.select("span.product-price-saving").text();

                productObj.setLink(productUrl);
                productObj.setName(name);
                productObj.setOldPrice(oldPrice);
                productObj.setNewPrice(newPrice);
                productObj.setImageUrl(imageUrl);
                productObj.setInstallment(installment);
                productObj.setPercentDiscount(percentDiscount);

                //6.3 Truy Cập Trang Chi Tiết Sản Phẩm
                driver.get(productUrl);
                wait.until(webDriver -> js.executeScript("return document.readyState").equals("complete"));
                //6.4 Kiểm tra dữ liệu Chi tiết sản phẩm
                try {
                    // Chờ cho phần tử "pdetail-attrs-comps" xuất hiện trên trang chi tiết sản phẩm
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.pdetail-attrs-comps")));

                    // Lấy HTML của trang chi tiết
                    String detailHtml = driver.getPageSource();
                    Document detailDoc = Jsoup.parse(detailHtml);

                    // Lấy thông tin từ bảng chi tiết sản phẩm
                    Elements detailRows = detailDoc.select("div.pdetail-attrs-comps table.table.table-striped tr");
                    //6.7 Crawl Chi Tiết Sản Phẩm
                    for (Element row : detailRows) {
                        Elements columns = row.select("td");
                        if (columns.size() == 2) {
                            String specTitle = columns.get(0).text().trim();
                            String specValue = columns.get(1).text().trim();
                            switch (specTitle.toLowerCase()) {
                                case "công suất lọc:":
                                    productObj.setCongSuatLoc(specValue);
                                    break;
                                case "thương hiệu":
                                    productObj.setThuongHieu(specValue);
                                   break;
                                case "mã sản phẩm":
                                    productObj.setMaSanPham(specValue);
                                    break;
                                case "tiện ích:":
                                    productObj.setTienIch(specValue);

                                case "loại máy lọc nước:":
                                    productObj.setLoaiMayLocNuoc(specValue);
                                    break;
                                case "chủng loại:":
                                    productObj.setChungLoai(specValue);

                                    break;
                                case "công nghệ lọc:":
                                    productObj.setCongNgheLoc(specValue);

                                    break;
                                case "số lõi lọc:":
                                    productObj.setSoLoiLoc(specValue);
                                    break;
                                case "tính năng nổi bật:":
                                    productObj.setTinhNangNoiBat(specValue);

                                    break;
                                case "vỏ tủ:":
                                    productObj.setVoTu(specValue);

                                    break;
                                case "kích thước:":
                                    productObj.setKichThuoc(specValue);

                                    break;
                                case "bảo hành":
                                    productObj.setBaoHanh(specValue);

                                    break;
                                case "lõi lọc:":
                                    productObj.setLoiLoc(specValue);

                                    break;
                                case "công suất tiêu thụ:":
                                    productObj.setCongSuatTieuThu(specValue);

                                    break;
                                case "dung tích bình chứa:":
                                    productObj.setDungTichBinhChua(specValue);

                                    break;
                                case "xuất xứ":
                                    productObj.setXuatXu(specValue);
                                    break;
                                default:
                                    break;
                            }
                        }
                    }

                } catch (org.openqa.selenium.TimeoutException e) {
                    //6.5 Ghi log
                    // Nếu không tìm thấy phần tử chi tiết sản phẩm trong thời gian chờ, bỏ qua sản phẩm này
                    System.out.println("Không thể tìm thấy phần tử 'pdetail-attrs-comps' cho sản phẩm: " + productUrl + ", bỏ qua sản phẩm này.");
                    Log log2 = new Log();
                    log2.setLevel("ERROR");
                    log2.setTimestamp(LocalDateTime.now());
                    log2.setContext("Crawl process");
                    log2.setMessage("Crawl Dữ Liệu của " + name + "Thất Bại");
                    logService.save(log2);
                    e.printStackTrace();
                    //6.6 Gửi Mail thông báo
                    String from = environment.getProperty("spring.mail.username");
                    String gmail = "21130590@st.hcmuaf.edu.vn";
                    String to = gmail;

                    String content = "Không thể tìm thấy phần tử 'pdetail-attrs-comps' cho sản phẩm: " + productUrl + ", bỏ qua sản phẩm này.";

                    mailServicel.send(from, to, "Báo Cáo Vấn Đề", content);
                    driver.navigate().back();
                    continue;
                }
                //6.8 Thêm Sản Phẩm vào Danh Sách Sản Phẩm
                productList.add(productObj);
                Log log3 = new Log();
                log3.setLevel("INFO");
                log3.setTimestamp(LocalDateTime.now());
                log3.setContext("Crawl process");
                log3.setMessage("Crawl Dữ Liệu của " + name + "Thành Công");
                logService.save(log3);

                //2.7 Quay lại trang danh sách sản phẩm
                driver.navigate().back();
                wait.until(webDriver -> js.executeScript("return document.readyState").equals("complete"));
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.card.mb-4")));
            }

            for (DataRaw product : productList) {
                System.out.println(product);
            }
        } catch (Exception e) {
            Log log = new Log();
            log.setLevel("ERROR");
            log.setTimestamp(LocalDateTime.now());
            log.setContext("Crawl error");
            log.setMessage("Lỗi trong quá trình truy cập trang");
            logService.save(log);
            System.out.println("Không thể tìm thấy phần tử ");

        } finally {

            driver.quit();
        }
        //7 Ghi Dữ liệu vào csv
        // Đường dẫn và tên file
        String folderPath = "C:\\project\\JavaWebCrawler\\CSV";
        String fileName = generateFileName();
        String filePath = folderPath + "\\" + fileName;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (DataRaw line : productList) {
                writer.write(line.toString());
                writer.newLine(); // Xuống dòng
            }
            //7.1 ghi thành công
            System.out.println("File CSV đã được ghi tại: " + filePath);
            Log log = new Log();
            log.setLevel("INFO");
            log.setTimestamp(LocalDateTime.now());
            log.setContext("Crawl Data to csv");
            log.setMessage("Đã khi csv tại "+ folderPath + " với tên " + fileName);
            logService.save(log);
        } catch (IOException e) {
            //7.2 ghi thất bại
            System.err.println("Lỗi khi ghi file: " + e.getMessage());
            Log log = new Log();
            log.setLevel("ERROR");
            log.setTimestamp(LocalDateTime.now());
            log.setContext("Crawl Data to csv");
            log.setMessage("Lỗi Khi ghi dữ liệu vào csv");
            logService.save(log);
        }
        return productList;
    }

    // Hàm tạo tên file dựa trên thời gian hiện tại
    private static String generateFileName() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH_mm_ss_dd_MM_yyyy");
        return "productRaw_" + now.format(formatter) + ".csv";
    }
}