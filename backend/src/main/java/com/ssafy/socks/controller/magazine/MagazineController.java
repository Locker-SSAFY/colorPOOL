package com.ssafy.socks.controller.magazine;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.socks.entity.magazine.Magazine;
import com.ssafy.socks.model.magazine.SelectModel;
import com.ssafy.socks.model.magazine.MagazineModel;
import com.ssafy.socks.model.response.CommonResult;
import com.ssafy.socks.model.response.ListResult;
import com.ssafy.socks.model.response.SingleResult;
import com.ssafy.socks.service.ResponseService;
import com.ssafy.socks.service.crawling.CrawlingService;
import com.ssafy.socks.service.magazine.MagazineService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "4. Magazine")
@RequestMapping(value = "/api")
public class MagazineController {

	private final CrawlingService crawlingService;
	private final ResponseService responseService;
	private final MagazineService magazineService;


	// @Parameters({
	// 	@Parameter(name = "X-AUTH-TOKEN", description = "JWT", required = true, in = ParameterIn.HEADER)
	// })
	// @Operation(summary = "테마 & 카테고리 별 이미지 크롤링 저장하기", description = "테마 & 카테고리 별 이미지를 크롤링해서 저장한다.")
	// @PostMapping(value = "/images")
	// public ListResult<Images> saveImages(@RequestBody ThemesAndCategory themesAndCategory) {
	// 	return responseService.getListResult(crawlingService.saveCategoryImages(themesAndCategory));
	// }

	@Parameters({
		@Parameter(name = "X-AUTH-TOKEN", description = "JWT", required = true, in = ParameterIn.HEADER)
	})
	@Operation(summary = "유저 잡지 저장하기", description = "유저 잡지를 저장한다.")
	@PostMapping(value = "/magazine")
	public CommonResult saveMagazine(@RequestBody MagazineModel magazineModel) {
		magazineService.saveMagazine(magazineModel);
		return responseService.getSuccessResult();
	}

	@Parameters({
		@Parameter(name = "X-AUTH-TOKEN", description = "JWT", required = true, in = ParameterIn.HEADER)
	})
	@Operation(summary = "유저 모든 잡지 조회", description = "유저에 대한 모든 잡지 정보를 조회한다.")
	@GetMapping(value = "/magazine")
	public ListResult<MagazineModel> getMagazineByUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authentication.getName();
		return responseService.getListResult(magazineService.getMagazinesByUser(userEmail));
	}

	@Parameters({
		@Parameter(name = "X-AUTH-TOKEN", description = "JWT", required = true, in = ParameterIn.HEADER)
	})
	@Operation(summary = "잡지 단건 조회", description = "하나의 잡지 정보를 조회한다.")
	@GetMapping(value = "/magazine/{magazineId}")
	public SingleResult<Magazine> getMagazine(@PathVariable Long magazineId) {
		return responseService.getSingleResult(magazineService.getMagazine(magazineId));
	}

	@Parameters({
		@Parameter(name = "X-AUTH-TOKEN", description = "JWT", required = true, in = ParameterIn.HEADER)
	})
	@Operation(summary = "인기순 잡지 정보", description = "모든 잡지 정보를 인기도 순으로 조회한다.")
	@GetMapping(value = "/magazines")
	public ListResult<MagazineModel> getAllMagazinesByLikes() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authentication.getName();
		return responseService.getListResult(magazineService.getMagazines(userEmail));
	}

	@Parameters({
		@Parameter(name = "X-AUTH-TOKEN", description = "JWT", required = true, in = ParameterIn.HEADER)
	})
	@Operation(summary = "즐겨찾기 잡지 조회", description = "즐겨 찾기 한 잡지를 조회한다.")
	@GetMapping(value = "/magazines/bookmark")
	public ListResult<MagazineModel> getMagazinesByBookmark() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authentication.getName();
		return responseService.getListResult(magazineService.getBookmarkMagazines(userEmail));
	}

	@Parameters({
		@Parameter(name = "X-AUTH-TOKEN", description = "JWT", required = true, in = ParameterIn.HEADER)
	})
	@Operation(summary = "즐겨찾기 잡지 조회", description = "즐겨 찾기 한 잡지를 조회한다.")
	@GetMapping(value = "/magazines/like")
	public ListResult<MagazineModel> getMagazinesByLikes() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authentication.getName();
		return responseService.getListResult(magazineService.getLikesMagazines(userEmail));
	}

	@Parameters({
		@Parameter(name = "X-AUTH-TOKEN", description = "JWT", required = true, in = ParameterIn.HEADER)
	})
	@Operation(summary = "좋아요", description = "좋아요를 클릭 한다.")
	@PostMapping(value = "/magazine/like")
	public SingleResult<SelectModel> setLike(@RequestParam Long magazineId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authentication.getName();
		return responseService.getSingleResult(magazineService.setLikes(magazineId,userEmail));
	}

	@Parameters({
		@Parameter(name = "X-AUTH-TOKEN", description = "JWT", required = true, in = ParameterIn.HEADER)
	})
	@Operation(summary = "북마크", description = "북마크를 클릭 한다.")
	@PostMapping(value = "/magazine/bookmark")
	public SingleResult<SelectModel> setBookmark(@RequestParam Long magazineId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authentication.getName();
		return responseService.getSingleResult(magazineService.setBookmark(magazineId,userEmail));
	}

}
