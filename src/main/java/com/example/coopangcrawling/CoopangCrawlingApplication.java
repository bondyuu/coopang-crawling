package com.example.coopangcrawling;

import com.example.coopangcrawling.crawling.CoopangCrawlingUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoopangCrawlingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoopangCrawlingApplication.class, args);

		CoopangCrawlingUtils crawlingUtils = new CoopangCrawlingUtils();

		ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        crawlingUtils.setUp(new ChromeDriver(options));
		crawlingUtils.openPage("https://link.coupang.com/a/4TubA");
		crawlingUtils.getReview();

		crawlingUtils.tearDown();
	}

}
