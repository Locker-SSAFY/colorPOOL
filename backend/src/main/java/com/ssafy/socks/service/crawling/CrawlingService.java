package com.ssafy.socks.service.crawling;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

import com.ssafy.socks.model.magazine.Images;
import com.ssafy.socks.model.magazine.ThemesAndCategory;

@Service
public class CrawlingService {
	public List<Images> getCategoryImages(ThemesAndCategory themesAndCategory) {
		String URL = null;
		String TAG = themesAndCategory.getCategory();
		List<Images> images = new ArrayList<>();

		// 1. WebDriver 경로 설정
		Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/chromedriver");
		System.setProperty("webdriver.chrome.driver", path.toString());

		// 2. WebDriver 옵션 설정
		ChromeOptions options = new ChromeOptions();

		// 3. WebDriver 객체 생성
		ChromeDriver driver = new ChromeDriver(options);

		for (String color : themesAndCategory.getHexColor()) {
			URL = "http://labs.tineye.com/multicolr/#colors=" + color + ";weights=100;tags=" + TAG;

			// 4. 웹페이지 요청
			driver.get(URL);

			WebDriverWait wait = new WebDriverWait(driver, 10);
			if (wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("filler")))) {

				Document document = Jsoup.parse(driver.getPageSource());
				System.out.println(document.body().toString());
				List<Element> elements = document.body().getElementsByClass("image_hover");
				List<String> imgList = new ArrayList<>();

				for (int j = 0; j < 5; j++) {
					String origin = elements.get(j).id().split("/")[1];
					String src = origin.substring(0, origin.length() - 1) + "c.jpg";
					imgList.add(src);
				}
				images.add(new Images(imgList));
			}
		}
		// WebDriver 종료
		driver.quit();

		return images;
	}
}
