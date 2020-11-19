package com.ssafy.socks.model.magazine;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString @Builder
public class ContentsModel {
	private String url;
	private String mainText;
	private String subText;
	private String question;
	private String answer;
	private String template;
}
