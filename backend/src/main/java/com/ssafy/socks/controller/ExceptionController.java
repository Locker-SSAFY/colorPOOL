package com.ssafy.socks.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.socks.advice.exception.CAccessDeniedException;
import com.ssafy.socks.advice.exception.CAuthenticationEntryPointException;
import com.ssafy.socks.model.response.CommonResult;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(value = "/exception")
public class ExceptionController {

	@GetMapping(value = "/entrypoint")
	public CommonResult entryPointException() {
		throw new CAuthenticationEntryPointException();
	}

	@GetMapping(value = "/accessdenied")
	public CommonResult accessDeniedException() {
		throw new CAccessDeniedException();
	}

}
