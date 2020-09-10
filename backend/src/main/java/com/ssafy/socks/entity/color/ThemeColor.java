package com.ssafy.socks.entity.color;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable @Setter @Getter @NoArgsConstructor @ToString
public class ThemeColor {
	@Column(name = "COLOR_ID_1", nullable = false)
	private Long colorId1;
	@Column(name = "COLOR_ID_2", nullable = false)
	private Long colorId2;
	@Column(name = "COLOR_ID_3", nullable = false)
	private Long colorId3;
	@Column(name = "COLOR_ID_4", nullable = false)
	private Long colorId4;
	@Column(name = "COLOR_ID_5", nullable = false)
	private Long colorId5;
}
