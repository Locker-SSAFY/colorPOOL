package com.ssafy.socks.controller.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.socks.entity.user.User;
import com.ssafy.socks.model.response.CommonResult;
import com.ssafy.socks.model.response.ListResult;
import com.ssafy.socks.model.response.SingleResult;
import com.ssafy.socks.model.user.SignInModel;
import com.ssafy.socks.service.ResponseService;
import com.ssafy.socks.service.user.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "2. User")
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@RestController
@CrossOrigin
public class UserController {

	private final UserService userService;
	private final ResponseService responseService; // 결과를 처리할 Service

	@Parameters({
		@Parameter(name = "X-AUTH-TOKEN", description = "로그인 성공 후 JWT-TOKEN", required = true, in = ParameterIn.HEADER)
	})
	@Operation(summary = "회원 리스트 조회", description = "모든 회원을 조회한다")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "회원 리스트 조회 성공",
			content = {@Content(mediaType = "application/json",
				schema = @Schema(implementation = SignInModel.class))}),
		@ApiResponse(responseCode = "409", description = "해당 사항 없음", content = @Content),
		@ApiResponse(responseCode = "500", description = "토큰 정보 부정확 or 권한 부족",
			content = @Content)})
	@GetMapping(value = "/users")
	public ListResult<User> findAllUser() {
		// 결과데이터가 여러건인경우 getListResult를 이용해서 결과를 출력한다.
		return responseService.getListResult(userService.findUsers());
	}

	@Parameters({
		@Parameter(name = "X-AUTH-TOKEN", description = "로그인 성공 후 JWT-TOKEN", required = true, in = ParameterIn.HEADER)
	})
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "회원 조회 성공", content = @Content),
		@ApiResponse(responseCode = "409", description = "해당 사항 없음", content = @Content),
		@ApiResponse(responseCode = "500", description = "토큰 정보 부정확", content = @Content)})
	@Operation(summary = "회원 단건 조회", description = "회원번호(pk)로 회원을 조회한다")
	@GetMapping(value = "/user")
	public SingleResult<User> findUserById(@Parameter(name = "lang", example = "ko") @RequestParam String lang) {
		// SecurityContext에서 인증받은 회원의 정보를 얻어온다.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String id = authentication.getName();
		// 결과데이터가 단일건인경우 getSingleResult를 이용해서 결과를 출력한다.
		return responseService.getSingleResult(userService.findByEmail(id));
	}

	@Parameters({
		@Parameter(name = "X-AUTH-TOKEN", description = "로그인 성공 후 access_token", required = true, in = ParameterIn.HEADER)
	})
	@Operation(summary = "회원 삭제", description = "userId로 회원정보를 삭제한다")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "회원 삭제 성공", content = @Content),
		@ApiResponse(responseCode = "409", description = "해당 사항 없음", content = @Content),
		@ApiResponse(responseCode = "500", description = "토큰 정보 부정확",
			content = @Content)})
	@DeleteMapping(value = "/user/{id}")
	public CommonResult delete(
		@Parameter(required = true) @PathVariable Long id) {
		userService.deleteById(id);
		// 성공 결과 정보만 필요한경우 getSuccessResult()를 이용하여 결과를 출력한다.
		return responseService.getSuccessResult();
	}
}
