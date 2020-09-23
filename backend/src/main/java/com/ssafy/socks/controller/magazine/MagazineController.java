package com.ssafy.socks.controller.magazine;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.socks.model.magazine.Images;
import com.ssafy.socks.model.magazine.ThemesAndCategory;
import com.ssafy.socks.model.response.ListResult;
import com.ssafy.socks.service.ResponseService;
import com.ssafy.socks.service.crawling.CrawlingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "3. Magazine")
@RequestMapping(value = "/api")
public class MagazineController {

	private final CrawlingService crawlingService;
	private final ResponseService responseService;

	@Parameters({
		@Parameter(name = "X-AUTH-TOKEN", description = "JWT", required = true, in = ParameterIn.HEADER)
	})
	@Operation(summary = "테마 & 카테고리 별 이미지 크롤링", description = "테마 & 카테고리 별 이미지를 크롤링한다.")
	@PostMapping(value = "/images")
	public ListResult<Images> findImages(@RequestBody ThemesAndCategory themesAndCategory) {
		return responseService.getListResult(crawlingService.getCategoryImages(themesAndCategory));
	}
}
