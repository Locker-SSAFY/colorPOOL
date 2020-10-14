package com.ssafy.socks.model.magazine;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class SelectModel {
	private Long userId;
	private Long magazineId;
	private boolean clicked;
}
