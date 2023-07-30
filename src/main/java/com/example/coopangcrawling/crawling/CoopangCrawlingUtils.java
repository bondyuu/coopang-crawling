package com.example.coopangcrawling.crawling;

import ch.qos.logback.core.joran.spi.ElementSelector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
public class CoopangCrawlingUtils {
    private final int TOTAL_NUM = 798;
    private final int PAGE_NUM = 5;
    private WebDriver driver;
    private JavascriptExecutor js;

    private WebDriverWait wait;

    public void setUp(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
    }

    public void openPage(String url) {
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5L));
        driver.manage().window().maximize();
    }

    public void getReview() throws Exception{
        List<Review> result = new ArrayList<>();

        try {
            Thread.sleep(3000L);
            js.executeScript("window.scrollTo(0, document.body.scrollHeight / 2 + 300);");
            Thread.sleep(10000L);
            System.out.println(driver.getPageSource().contains("그런데 그건 성분특성상 바른후 빛(자외선, 전등불빛)을 보면 안된다고해서 신경쓰여서 잘 안쓰게되어서 유효기간을 넘기고말았어요."));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            for (int i = 0; i < TOTAL_NUM/PAGE_NUM; i++) {
                Thread.sleep(2000L);
                System.out.println("i : " + i);
                String html = driver.getPageSource();
//            System.out.println(html);
                Document doc = Jsoup.parse(html);
                Elements elements = doc.getElementsByClass("js_reviewArticleListContainer").get(0)
                        .getElementsByTag("article");
                List<Review> reviewList = parseReview(elements);
                result.addAll(reviewList);
                System.out.println(result);
                goToNextPage(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(result);

        ExcelWriter excelWriter = new ExcelWriter();
        excelWriter.write(result);
    }

    public void tearDown() {
        driver.quit();
    }

    public List<Review> parseReview(Elements elements) {
        List<Review> reviewList = new ArrayList<>();

//        System.out.println(elements);
        for (Element element : elements) {
            String scoreStr = element.getElementsByClass("sdp-review__article__list__info").get(0)
                    .getElementsByClass("sdp-review__article__list__info__product-info").get(0)
                    .getElementsByClass("sdp-review__article__list__info__product-info__star-gray").get(0)
                    .getElementsByClass("sdp-review__article__list__info__product-info__star-orange js_reviewArticleRatingValue").get(0)
                    .attributes().get("data-rating");

            int score = Integer.parseInt(scoreStr);


            String content = "";
            try {
                content = element.getElementsByClass("sdp-review__article__list__review js_reviewArticleContentContainer").get(0)
                        .getElementsByClass("sdp-review__article__list__review__content js_reviewArticleContent").get(0)
                        .text();
            } catch (Exception e) {
                content = "없음";
            }


            boolean isContain = content.contains("체험단");

            Review review = new Review(content, score, isContain);
            reviewList.add(review);
        }

        return reviewList;
    }

    public void goToNextPage(int i) throws InterruptedException {
        int now = i+1;

        int plus = TOTAL_NUM % PAGE_NUM == 0 ? 0 : 1;

        if (now == TOTAL_NUM/PAGE_NUM + plus) {
            return;
        }

        Thread.sleep(1000L);
        Point point = driver.findElement(By.xpath("//*[@id=\"btfTab\"]/ul[2]/li[3]/div/div[6]/section[4]/div[3]")).getLocation();
        driver.manage().window().setPosition(point);

        if (now % 10 == 0) {
            driver.findElement(By.xpath("//*[@id=\"btfTab\"]/ul[2]/li[3]/div/div[6]/section[4]/div[3]/button[12]")).click();
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li[text()='상품상세']"))));
            driver.manage().window().setPosition(point);
        }
        Thread.sleep(1000L);
        int next = now +1;
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-page='" + next + "']"))).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li[text()='상품상세']"))));
    }
}
