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
		options.addArguments("--User-Agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36");
		options.addArguments("--Accept-Language=ko-KR,ko;q=0.8,en-US;q=0.5,en;q=0.3");
//https://rimeestore.tistory.com/entry/%EC%9B%B9%EC%8A%A4%ED%81%AC%EB%9E%98%ED%95%91-Beautifulsoup4-%ED%99%9C%EC%9A%A9-2-%EC%BF%A0%ED%8C%A1
        crawlingUtils.setUp(new ChromeDriver(options));
		crawlingUtils.openPage("https://link.coupang.com/a/4TubA");
		crawlingUtils.getReview();

		crawlingUtils.tearDown();
	}

}
