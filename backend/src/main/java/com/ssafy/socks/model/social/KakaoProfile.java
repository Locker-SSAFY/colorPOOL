package com.ssafy.socks.model.social;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class KakaoModel {
	private Long id;
	private Properties properties;
	private String email;

	@Getter @Setter @ToString
	private static class Properties {
		private String nickname;
		private String thumbnail_image;
		private String profile_image;
	}
}
