package com.ssafy.socks.controller.account;

import java.util.Collections;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.socks.advice.exception.CEmailSigninFailedException;
import com.ssafy.socks.config.security.JwtTokenProvider;
import com.ssafy.socks.entity.user.User;
import com.ssafy.socks.model.response.CommonResult;
import com.ssafy.socks.model.response.SingleResult;
import com.ssafy.socks.model.user.SignInModel;
import com.ssafy.socks.model.user.SignUpModel;
import com.ssafy.socks.service.ResponseService;
import com.ssafy.socks.service.user.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor @RestController
@Api(tags = {"1. Account"})
@RequestMapping(value = "/api")
public class AccountController {
	private final static String provider = "ROOT";
	private final static String role = "ROLE_USER";

	private final JwtTokenProvider jwtTokenProvider;
	private final ResponseService responseService;
	private final PasswordEncoder passwordEncoder;
	private final UserService userService;

	@ApiOperation(value = "가입", notes = "회원가입을 한다.")
	@PostMapping(value = "/signup")
	public CommonResult signUp(@RequestBody SignUpModel signUpModel) {
		userService.join(
			User.builder()
				.email(signUpModel.getEmail())
				.nickname(signUpModel.getNickname())
				.password(passwordEncoder.encode(signUpModel.getPassword()))
				.provider(provider)
				.roles(Collections.singletonList(role))
				.build());
		return responseService.getSuccessResult();
	}

	@ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
	@PostMapping(value = "/signin")
	public SingleResult<String> signIn(@RequestBody SignInModel signInModel) {
		User user = userService.findByEmail(signInModel.getEmail());
		if (!passwordEncoder.matches(signInModel.getPassword(), user.getPassword())) {
			throw new CEmailSigninFailedException();
		}
		return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(user.getId()), user.getRoles()));
	}
}
