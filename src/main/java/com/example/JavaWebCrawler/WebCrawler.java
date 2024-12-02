package com.example.JavaWebCrawler;

import com.example.JavaWebCrawler.entities.Log;
import com.example.JavaWebCrawler.entities.Product;
import com.example.JavaWebCrawler.service.LogService;
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
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public  class WebCrawler {
    @Autowired
    private LogService logService;

    // Tạo danh sách để chứa thông tin sản phẩm
    List<Product> productList ;
    public WebCrawler() {
        productList = new ArrayList<>();
    }
    public List<Product> crawlProducts()  {
        // Cấu hình Selenium
        System.setProperty("webdriver.chrome.driver", "C:\\project\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Tạo danh sách để chứa thông tin sản phẩm
        productList =  new ArrayList<>();
        try {
            // Truy cập trang web
            String url = "https://mediamart.vn/may-loc-nuoc";
            driver.get(url);

            // Kiểm tra trạng thái DOM
            wait.until(webDriver -> js.executeScript("return document.readyState").equals("complete"));

            // Chờ cho đến khi phần tử xuất hiện trong DOM
            WebElement cardElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.card.mb-4")));
            // Lấy HTML của trang
            String html = driver.getPageSource();

            // Sử dụng Jsoup để phân tích HTML
            Document doc = Jsoup.parse(html);
            Elements products = doc.select("div.card.mb-4");



            for (Element product : products) {
                Map<String, String> productInfo = new HashMap<>();

                // Lấy link sản phẩm
                String link = product.select("a.product-item").attr("href");
                String productUrl = "https://mediamart.vn" + link;
                productInfo.put("link", productUrl);

                // Lấy tên sản phẩm qua alt của ảnh
                String name = product.select("img").attr("alt");
                productInfo.put("name", name.isEmpty() ? "Không có tên" : name);

//                Log log = new Log();
//                log.setLevel("INFO");
//                log.setTimestamp(LocalDateTime.now());
//                log.setContext("Crawl process");
//                log.setMessage("Đang Crawl Dữ Liệu của " + name);
//                logService.save(log);

                // Lấy giá cũ
                String oldPrice = product.select("p.product-price-regular span").text();
                productInfo.put("old_price", oldPrice.isEmpty() ? "Không có giá cũ" : oldPrice);

                // Lấy giá mới
                String newPrice = product.select("p.card-text.product-price").text();
                productInfo.put("new_price", newPrice.isEmpty() ? "Không có giá mới" : newPrice);

                // Lấy URL hình ảnh của sản phẩm
                String imageUrl = product.select("img").attr("src");
                productInfo.put("image_url", imageUrl.isEmpty() ? "Không có hình ảnh" : imageUrl);

                // Tìm thông tin trả góp
                String installment = product.select("span.product-type.product-type-11").text();
                productInfo.put("installment", installment.isEmpty() ? "Không có trả góp" : installment);

                // % Giảm giá
                String percentDiscount = product.select("span.product-price-saving").text();
                productInfo.put("percent_discount", percentDiscount.isEmpty() ? "Không hiển thị" : percentDiscount);

                Product productObj = new Product();
                productObj.setLink(productUrl);
                productObj.setName(name);
                productObj.setOldPrice(oldPrice);
                productObj.setNewPrice(newPrice);
                productObj.setImageUrl(imageUrl);
                productObj.setInstallment(installment);
                productObj.setPercentDiscount(percentDiscount);

                // Truy cập trang chi tiết sản phẩm để lấy thêm thông tin
                driver.get(productUrl);
                wait.until(webDriver -> js.executeScript("return document.readyState").equals("complete"));

                try {
                    // Chờ cho phần tử "pdetail-attrs-comps" xuất hiện trên trang chi tiết sản phẩm
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.pdetail-attrs-comps")));

                    // Lấy HTML của trang chi tiết
                    String detailHtml = driver.getPageSource();
                    Document detailDoc = Jsoup.parse(detailHtml);

                    // Lấy thông tin từ bảng chi tiết sản phẩm
                    Elements detailRows = detailDoc.select("div.pdetail-attrs-comps table.table.table-striped tr");

                    for (Element row : detailRows) {
                        Elements columns = row.select("td");
                        if (columns.size() == 2) {
                            String specTitle = columns.get(0).text().trim();
                            System.out.println(specTitle);
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
                                    break;
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
                    // Nếu không tìm thấy phần tử chi tiết sản phẩm trong thời gian chờ, bỏ qua sản phẩm này
                    System.out.println("Không thể tìm thấy phần tử 'pdetail-attrs-comps' cho sản phẩm: " + productUrl + ", bỏ qua sản phẩm này.");
//                    Log log2 = new Log();
//                    log.setLevel("ERROR");
//                    log.setTimestamp(LocalDateTime.now());
//                    log.setContext("Crawl process");
//                    log.setMessage("Crawl Dữ Liệu của " + name + "Thất Bại");
//                    logService.save(log2);
                    driver.navigate().back();
                    continue;
                }

                // Thêm thông tin sản phẩm vào danh sách
                productList.add(productObj);
//                Log log3 = new Log();
//                log.setLevel("INFO");
//                log.setTimestamp(LocalDateTime.now());
//                log.setContext("Crawl process");
//                log.setMessage("Crawl Dữ Liệu của " + name + "Thành Công");
//                logService.save(log3);
                // Quay lại trang danh sách sản phẩm
                driver.navigate().back();
                wait.until(webDriver -> js.executeScript("return document.readyState").equals("complete"));
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.card.mb-4")));
            }

            // In kết quả
            for (Product product : productList) {
                System.out.println(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Không thể tìm thấy phần tử 'pdetail-attrs-comps' cho sản phẩm, bỏ qua sản phẩm này.");

        } finally {
            // Đóng trình duyệt
            driver.quit();
        }
        return productList;
    }
}