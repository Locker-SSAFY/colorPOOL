package com.ssafy.socks.controller.account;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.socks.advice.exception.CEmailSigninFailedException;
import com.ssafy.socks.config.security.JwtTokenProvider;
import com.ssafy.socks.entity.user.User;
import com.ssafy.socks.model.response.CommonResult;
import com.ssafy.socks.model.response.SingleResult;
import com.ssafy.socks.model.social.SocialResultModel;
import com.ssafy.socks.model.user.SignInModel;
import com.ssafy.socks.model.user.SignUpModel;
import com.ssafy.socks.service.ResponseService;
import com.ssafy.socks.service.social.SocialService;
import com.ssafy.socks.service.user.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@Tag(name = "1. Account")
@RequestMapping(value = "/api")
public class AccountController {

	private final JwtTokenProvider jwtTokenProvider;
	private final ResponseService responseService;
	private final PasswordEncoder passwordEncoder;
	private final UserService userService;
	private final SocialService socialService;

	@Operation(summary = "가입", description = "회원가입을 한다.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "회원가입 성공", content = @Content),
		@ApiResponse(responseCode = "409", description = "중복 회원 (unique constraint)",
			content = @Content),
		@ApiResponse(responseCode = "500", description = "중복 회원",
			content = @Content)})
	@PostMapping(value = "/signup")
	public CommonResult signUp(@RequestBody SignUpModel signUpModel) {
		userService.join(signUpModel);
		return responseService.getSuccessResult();
	}

	@Operation(summary = "로그인", description = "이메일 회원 로그인을 한다.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content),
		@ApiResponse(responseCode = "409", description = "해당 사항 없음", content = @Content),
		@ApiResponse(responseCode = "500", description = "존재 하지 않는 회원",
			content = @Content)})
	@PostMapping(value = "/signin")
	public SingleResult<String> signIn(@RequestBody SignInModel signInModel) {
		User user = userService.findByEmail(signInModel.getUserInfo().getEmail());
		if (!passwordEncoder.matches(signInModel.getUserInfo().getPassword(), user.getPassword())) {
			throw new CEmailSigninFailedException();
		}
		return responseService.getSingleResult(
			jwtTokenProvider.createToken(String.valueOf(user.getId()), user.getRoles()));
	}

	@Operation(summary = "소셜 계정 가입", description = "소셜 계정 회원가입을 한다.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "회원 가입 성공", content = @Content),
		@ApiResponse(responseCode = "409", description = "중복 회원 (unique constraint)", content = @Content),
		@ApiResponse(responseCode = "500", description = "중복 회원",
			content = @Content)})
	@PostMapping(value = "/signup/{provider}")
	public CommonResult signUpProvider(@RequestBody SignUpModel signUpModel) {
		userService.join(signUpModel);
		return responseService.getSuccessResult();
	}

	@Operation(summary = "소셜 로그인", description = "소셜 회원 로그인을 한다.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content),
		@ApiResponse(responseCode = "409", description = "해당 사항 없음", content = @Content),
		@ApiResponse(responseCode = "500", description = "알 수 없는 오류",
			content = @Content)})
	@PostMapping(value = "/signin/social")
	public SingleResult<SocialResultModel> signInByProvider(@RequestBody String accessToken) {
		return responseService.getSingleResult(socialService.getSocialResultModel(accessToken));
	}
}
