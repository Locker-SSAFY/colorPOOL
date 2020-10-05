package com.ssafy.socks.controller.color;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.socks.entity.color.Theme;
import com.ssafy.socks.model.response.ListResult;
import com.ssafy.socks.service.ResponseService;
import com.ssafy.socks.service.color.ColorService;
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
@Tag(name = "3. Color")
@RequestMapping(value = "/api")
public class ColorController {
	private final ResponseService responseService;
	private final ColorService colorService;
	private final CrawlingService crawlingService;

	@Parameters({
		@Parameter(name = "X-AUTH-TOKEN", description = "JWT", required = true, in = ParameterIn.HEADER)
	})
	@Operation(summary = "배색 조합 추천 받기", description = "색 선택 시 배색 조합 10개를 추천받는다.")
	@GetMapping(value = "/themes/{colorId}")
	public ListResult<Theme> getThemes(@PathVariable Long colorId) {
		return responseService.getListResult(colorService.getThemes(colorId));
	}

	@Parameters({
		@Parameter(name = "X-AUTH-TOKEN", description = "JWT", required = true, in = ParameterIn.HEADER)
	})
	@Operation(summary = "테마 & 카테고리 별 이미지 불러오기", description = "테마 & 카테고리 별 이미지를 조회한다.")
	@GetMapping(value = "/images/{themeId}")
	public ListResult<String> findImages(@PathVariable Long themeId) {
		return responseService.getListResult(crawlingService.getCategoryImages(themeId));
	}
}





