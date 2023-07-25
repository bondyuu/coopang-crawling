package com.example.coopangcrawling.crawling;

import ch.qos.logback.core.joran.spi.ElementSelector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
public class CoopangCrawlingUtils {
    private final int TOTAL_NUM = 15500;
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
        driver.manage().window().fullscreen();
    }

    public void getReview() {
        List<Review> result = new ArrayList<>();

        for (int i = 0; i < TOTAL_NUM/PAGE_NUM; i++) {
            String html = driver.getPageSource();

            Document doc = Jsoup.parse(html);
            Elements elements = doc.getElementsByTag("article");
            List<Review> reviewList = parseReview(elements);
            result.addAll(reviewList);

            goToNextPage(i);
        }
        System.out.println(result);
    }

    public void tearDown() {
        driver.quit();
    }

    public List<Review> parseReview(Elements elements) {
        List<Review> reviewList = new ArrayList<>();

        for (Element element : elements) {
            String scoreStr = element.getElementsByClass("sdp-review__article__list__info").get(0)
                    .getElementsByClass("sdp-review__article__list__info__product-info").get(0)
                    .getElementsByClass("sdp-review__article__list__info__product-info__star-gray").get(0)
                    .getElementsByClass("sdp-review__article__list__info__product-info__star-orange js_reviewArticleRatingValue").get(0)
                    .attributes().get("data-rating");

            int score = Integer.parseInt(scoreStr);


            String content = element.getElementsByClass("sdp-review__article__list__review js_reviewArticleContentContainer").get(0)
                    .getElementsByClass("sdp-review__article__list__review__content js_reviewArticleContent").get(0)
                    .text();

            boolean isContain = content.contains("체험단");

            Review review = new Review(content, score, isContain);
            reviewList.add(review);
        }

        return reviewList;
    }

    public void goToNextPage(int i) {
        int now = i+1;

        int plus = TOTAL_NUM % PAGE_NUM == 0 ? 0 : 1;

        if (now == TOTAL_NUM/PAGE_NUM + plus) {
            return;
        }

        Point point = driver.findElement(By.xpath("//*[@id=\"btfTab\"]/ul[2]/li[3]/div/div[6]/section[4]/div[3]")).getLocation();
        driver.manage().window().setPosition(point);

        if (now % 10 == 0) {
            driver.findElement(By.xpath("//*[@id=\"btfTab\"]/ul[2]/li[3]/div/div[6]/section[4]/div[3]/button[12]")).click();
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li[text()='상품상세']"))));
            driver.manage().window().setPosition(point);
        }

        driver.findElement(By.xpath("//button[@data-page='" + now+1 + "']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li[text()='상품상세']"))));
    }
}
