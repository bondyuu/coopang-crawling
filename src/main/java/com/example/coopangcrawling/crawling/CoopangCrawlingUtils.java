package com.example.coopangcrawling.crawling;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class CoopangCrawlingUtils {
    private WebDriver driver;
    private WebDriverWait wait;

    public void setUp(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
    }

    public void openPage(String url) {
        driver.get(url);
    }

    public void tearDown() {
        driver.quit();
    }


}
