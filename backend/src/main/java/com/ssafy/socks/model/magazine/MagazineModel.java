package com.ssafy.socks.model.magazine;

import java.time.LocalDateTime;
import java.util.List;

import com.ssafy.socks.entity.magazine.Contents;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class MagazineModel {
	private Long id;
	private String email;
	private List<Contents> content;
	private String magazineName;
	private String userNickname;
	private LocalDateTime createdDate;
}
