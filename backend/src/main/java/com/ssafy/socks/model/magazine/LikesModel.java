package com.ssafy.socks.model.magazine;

import lombok.Setter;

@Setter
public class LikesModel {
	private Long userId;
	private Long magazineId;
	private boolean isClicked;
}
