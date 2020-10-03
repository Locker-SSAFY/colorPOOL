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

import com.ssafy.socks.advice.exception.CCommunicationException;
import com.ssafy.socks.entity.color.Theme;
import com.ssafy.socks.entity.images.ThemeImages;
import com.ssafy.socks.model.magazine.Images;
import com.ssafy.socks.model.magazine.ThemesAndCategory;
import com.ssafy.socks.repository.theme.ThemeImagesJpaRepository;
import com.ssafy.socks.repository.theme.ThemeJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CrawlingService {
	private final ThemeImagesJpaRepository themeImagesJpaRepository;
	private final ThemeJpaRepository themeJpaRepository;

	public List<Images> getCategoryImages(ThemesAndCategory themesAndCategory) {
		StringBuilder URL = new StringBuilder();
		String TAG = themesAndCategory.getCategory();
		List<Images> images = new ArrayList<>();

		// 1. WebDriver 경로 설정
		Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/chromedriver");
		System.setProperty("webdriver.chrome.driver", path.toString());

		// 2. WebDriver 옵션 설정
		ChromeOptions options = new ChromeOptions();

		// 3. WebDriver 객체 생성
		ChromeDriver driver = new ChromeDriver(options);
		URL.append("http://labs.tineye.com/multicolr/#colors=");

		StringBuilder weightStr = new StringBuilder();
		int weight = 100 / themesAndCategory.getHexColor().size();

		for (int i = 0; i < themesAndCategory.getHexColor().size(); i++) {
			URL.append(themesAndCategory.getHexColor().get(i));
			weightStr.append(weight);

			if (i != themesAndCategory.getHexColor().size() - 1) {
				URL.append(",");
				weightStr.append(",");
			}
		}
		URL.append(";weights=").append(weightStr).append(";tags=").append(TAG);

		// 4. 웹페이지 요청
		driver.get(URL.toString());

		WebDriverWait wait = new WebDriverWait(driver, 10);
		if (wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("filler")))) {

			Document document = Jsoup.parse(driver.getPageSource());
			List<Element> elements = document.body().getElementsByClass("image_hover");
			List<String> imgList = new ArrayList<>();

			for (int j = 0; j < 5; j++) {
				String origin = elements.get(j).id().split("/")[1];
				String src = origin.substring(0, origin.length() - 1) + "c.jpg";
				imgList.add(src);
			}
			images.add(new Images(imgList));
		}

		// WebDriver 종료
		driver.quit();

		// 이미지 디비에 저장
		saveImages(images, themesAndCategory);

		return images;
	}

	private void saveImages(List<Images> images, ThemesAndCategory themesAndCategory) {
		for (Images image : images) {
			for (String img : image.getImageList()) {
				ThemeImages themeImages = new ThemeImages();
				themeImages.setImageUrl(img);
				themeImages.addSelectedColor(themeJpaRepository.findById(themesAndCategory.getThemeId()).orElseThrow(
					CCommunicationException::new));

				themeImagesJpaRepository.save(themeImages);
			}
		}
	}
}
