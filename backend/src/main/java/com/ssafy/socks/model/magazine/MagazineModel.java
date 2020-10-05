package com.ssafy.socks.model.magazine;

import java.time.LocalDateTime;
import java.util.List;

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
}
