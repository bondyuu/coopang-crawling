package com.example.coopangcrawling;

import com.example.coopangcrawling.crawling.CoopangCrawlingUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class CoopangCrawlingApplication {

	public static void main(String[] args) throws Exception{
		SpringApplication.run(CoopangCrawlingApplication.class, args);

		CoopangCrawlingUtils crawlingUtils = new CoopangCrawlingUtils();

		ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
		options.addArguments("--User-Agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36");
		options.addArguments("--Accept-Language=ko-KR,ko;q=0.8,en-US;q=0.5,en;q=0.3");
		options.addArguments("--Sec-Ch-Ua-Platform=\"Windows\"");

        crawlingUtils.setUp(new ChromeDriver(options));
//		crawlingUtils.openPage("https://www.coupang.com/vp/products/6608244226?itemId=13081285942&vendorItemId=70180839370&src=1191000&spec=10999999&addtag=400&ctag=6608244226&lptag=CFM91774609&itime=20230725203458&pageType=PRODUCT&pageValue=6608244226&wPcid=16902848981585456576867&wRef=&wTime=20230725203458&redirect=landing&mcid=aa0c7b93487e401a9e949d516f6427a5&sharesource=sharebutton&style=&isshortened=Y&settlement=N&isAddedCart=");

//		crawlingUtils.openPage("https://www.coupang.com/vp/products/7038757249?itemId=17399508526&vendorItemId=84568953696&src=1191000&spec=10999999&addtag=400&ctag=7038757249&lptag=CFM91774609&itime=20230729202535&pageType=PRODUCT&pageValue=7038757249&wPcid=16902848981585456576867&wRef=&wTime=20230729202535&redirect=landing&mcid=c0b784416e3c47b2bab4b69b550fe8da&sharesource=sharebutton&style=&isshortened=Y&settlement=N&isAddedCart=");

		// 연어
//		crawlingUtils.openPage("https://www.coupang.com/vp/products/1656825434?itemId=2822948027&vendorItemId=70812422763&src=1191000&spec=10999999&addtag=400&ctag=1656825434&lptag=CFM91774609&itime=20230730152113&pageType=PRODUCT&pageValue=1656825434&wPcid=16902848981585456576867&wRef=&wTime=20230730152113&redirect=landing&mcid=b539e2739d904c5d9abb0ddf112e8718&sharesource=sharebutton&style=&isshortened=Y&settlement=N&isAddedCart=");

		// 브러쉬
//		crawlingUtils.openPage("https://www.coupang.com/vp/products/7285052758?itemId=18607376357&vendorItemId=85743233369&src=1191000&spec=10999999&addtag=400&ctag=7285052758&lptag=CFM91774609&itime=20230730154759&pageType=PRODUCT&pageValue=7285052758&wPcid=16902848981585456576867&wRef=&wTime=20230730154759&redirect=landing&mcid=6fc3849832d64b0eacafe7c328d7dea5&sharesource=sharebutton&style=&isshortened=Y&settlement=N&isAddedCart=");


		// 아이라이너
//		crawlingUtils.openPage("https://www.coupang.com/vp/products/1383341740?itemId=2417937349&vendorItemId=70412167572&src=1191000&spec=10999999&addtag=400&ctag=1383341740&lptag=CFM91774609&itime=20230730155016&pageType=PRODUCT&pageValue=1383341740&wPcid=16902848981585456576867&wRef=&wTime=20230730155016&redirect=landing&mcid=68045c91d6094af68b97ecd7b9b8bc39&sharesource=sharebutton&style=&isshortened=Y&isAddedCart=");

		// 케이블
		crawlingUtils.openPage("https://www.coupang.com/vp/products/5007358682?itemId=6700822305&vendorItemId=73994450125&src=1191000&spec=10999999&addtag=400&ctag=5007358682&lptag=CFM91774609&itime=20230730163830&pageType=PRODUCT&pageValue=5007358682&wPcid=16902848981585456576867&wRef=&wTime=20230730163830&redirect=landing&mcid=51a001bd66e141e9a3594b7da79c4510&sharesource=sharebutton&style=&isshortened=Y&settlement=N&isAddedCart=");


		crawlingUtils.getReview();

		crawlingUtils.tearDown();
	}

}
