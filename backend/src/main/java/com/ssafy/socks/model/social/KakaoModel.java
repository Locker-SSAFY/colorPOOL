package com.ssafy.socks.model.social;

import javax.persistence.Embedded;

import com.ssafy.socks.model.user.UserInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class KakaoModel {
	@Embedded UserInfo userInfo;
	private String accessToken;
}
