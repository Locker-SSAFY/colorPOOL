package com.ssafy.socks.entity.color;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "RECOMMEND_COLOR")
public class RecommendColor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RECOMMEND_COLOR_ID", nullable = false)
	private Long id;

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
