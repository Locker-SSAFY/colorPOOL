package com.ssafy.socks.model.social;

import javax.persistence.Embedded;

import com.ssafy.socks.model.user.UserInfo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @Builder
public class SocialResultModel {
	private Long id;
	private String nickname;
	private String token;
	@Embedded private UserInfo userInfo;
}
