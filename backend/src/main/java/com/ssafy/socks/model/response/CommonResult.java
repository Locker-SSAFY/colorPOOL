package com.ssafy.socks.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResult {
	@Schema(defaultValue = "응답 성공여부 : true/false")
	private boolean success;

	@Schema(defaultValue = "응답 코드 번호 : >= 0 정상, < 0 비정상")
	private int code;

	@Schema(defaultValue = "응답 메시지")
	private String msg;
}
