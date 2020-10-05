package com.ssafy.socks.model.magazine;

import java.time.LocalDateTime;
import java.util.List;

import com.ssafy.socks.entity.color.ThemeColor;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class MagazineModel {
	private Long id;
	private String email;
	private List<String> content;
	private String magazineName;
	private String userNickname;
	private LocalDateTime createdDate;
	private ThemeColor themeColor;
}
