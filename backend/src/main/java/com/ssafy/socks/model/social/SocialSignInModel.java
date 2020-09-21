package com.ssafy.socks.model.social;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SocialSignInModel {
	private String accessToken;
	private String provider;
}
