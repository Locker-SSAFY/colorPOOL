package com.ssafy.socks.service.crawling;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

@Service
public class CrawlingService {
	public String[] getCategoryImages(String color, String tag) {
		final String URL = "http://labs.tineye.com/multicolr/#colors=" + color + ";weights=100;tags=" + tag + "fashion";
		String[] resultUrls = new String[5];

		// 1. WebDriver 경로 설정
		Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", path.toString());

		// 2. WebDriver 옵션 설정
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("--headless");                 // Browser를 띄우지 않음
		//options.addArguments("--disable-gpu");              // GPU를 사용하지 않음, Linux에서 headless를 사용하는 경우 필요함.
		//options.addArguments("--no-sandbox");               // Sandbox 프로세스를 사용하지 않음, Linux에서 headless를 사용하는 경우 필요함.

		// 3. WebDriver 객체 생성
		ChromeDriver driver = new ChromeDriver(options);

		// 4. 웹페이지 요청
		driver.get(URL);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		if (wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("filler")))) {

			Document document = Jsoup.parse(driver.getPageSource());
			List<Element> elements = document.body().getElementsByClass("image_hover");

			for (int i = 0; i < 5; i++) {
				String origin = elements.get(i).id().split("/")[1];
				String src = origin.substring(0, origin.length() - 1) + "c.jpg";
				resultUrls[i] = src;
			}
		}
		// WebDriver 종료
		driver.quit();

		return resultUrls;
	}
}
