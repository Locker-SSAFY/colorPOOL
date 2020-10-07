package com.ssafy.socks.model.magazine;

import java.time.LocalDateTime;
import java.util.List;

import com.ssafy.socks.entity.magazine.Contents;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class MagazineModel {
	private Long themeId;
	private Long selectedColorId;
	private String email;
	private List<ContentsModel> contents;
	private String magazineName;
	private String userNickname;
	private LocalDateTime createdDate;
}
