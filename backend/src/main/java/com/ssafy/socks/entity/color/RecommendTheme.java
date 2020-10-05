package com.ssafy.socks.entity.color;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "RECOMMEND_THEME")
public class RecommendTheme {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RECOMMEND_THEME_ID", nullable = false)
	private Long id;

	@Column(name = "THEME_ID_1", nullable = false)
	private Long themeId1;
	@Column(name = "THEME_ID_2", nullable = false)
	private Long themeId2;
	@Column(name = "THEME_ID_3", nullable = false)
	private Long themeId3;
	@Column(name = "THEME_ID_4", nullable = false)
	private Long themeId4;
	@Column(name = "THEME_ID_5", nullable = false)
	private Long themeId5;
	@Column(name = "THEME_ID_6", nullable = false)
	private Long themeId6;
	@Column(name = "THEME_ID_7", nullable = false)
	private Long themeId7;
	@Column(name = "THEME_ID_8", nullable = false)
	private Long themeId8;
	@Column(name = "THEME_ID_9", nullable = false)
	private Long themeId9;
	@Column(name = "THEME_ID_10", nullable = false)
	private Long themeId10;
}
