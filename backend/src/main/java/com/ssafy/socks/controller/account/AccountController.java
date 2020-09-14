package com.ssafy.socks.controller.account;

import java.util.Collections;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.socks.entity.user.User;
import com.ssafy.socks.model.response.CommonResult;
import com.ssafy.socks.model.user.UserResult;
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
		private final static String role = "USER";

		private final ResponseService responseService;
		private final PasswordEncoder passwordEncoder;
		private final UserService userService;

		@ApiOperation(value = "가입", notes = "회원가입을 한다.")
		@PostMapping(value = "/signup")
		public CommonResult signUp(@RequestBody UserResult userResult) {
			userService.join(
				User.builder()
					.email(userResult.getEmail())
					.password(passwordEncoder.encode(userResult.getPassword()))
					.provider(provider)
					.roles(Collections.singletonList(role))
					.build());
			return responseService.getSuccessResult();
		}
	}
