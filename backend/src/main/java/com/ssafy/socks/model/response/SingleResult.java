package com.ssafy.socks.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SingleResult<T> extends CommonResult {
	private T data;
}
