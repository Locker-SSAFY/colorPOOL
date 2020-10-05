package com.ssafy.socks.service.crawling;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.socks.entity.color.SelectedColor;
import com.ssafy.socks.entity.color.Theme;
import com.ssafy.socks.entity.color.ThemeColor;
import com.ssafy.socks.entity.images.ThemeImages;
import com.ssafy.socks.repository.theme.ThemeImagesJpaRepository;
import com.ssafy.socks.repository.theme.ThemeJpaRepository;

@SpringBootTest @Transactional
public class CrawlingTest {
	@Autowired private ThemeJpaRepository themeJpaRepository;
	@Autowired private ThemeImagesJpaRepository themeImagesJpaRepository;

	@Test
	public void 테마_더미_데이터_디비에_넣기() throws Exception {
	    // given
		Theme theme = Theme.builder()
			.themeColor(new ThemeColor(1L,2L,3L,4L,5L))
			.selectedColor(new SelectedColor())
			.crawledImages(new ArrayList<ThemeImages>())
			.build();
		themeJpaRepository.save(theme);

	    // when
		ThemeImages themeImages = new ThemeImages();
		themeImages.setImageUrl("testURL");
		themeImages.addSelectedColor(theme);
		themeImagesJpaRepository.save(themeImages);

	    // then
		System.out.println(Arrays.toString(themeImagesJpaRepository.findAll().toArray()));
	 }

}
