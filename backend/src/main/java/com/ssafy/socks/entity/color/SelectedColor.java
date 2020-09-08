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

@ToString @Builder @Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "SELECTED_COLOR")
public class SelectedColor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SELECTED_COLOR_ID", nullable = false)
	private Long id;

	@Column(name = "RED", nullable = false)
	private Integer red;
	@Column(name = "GREEN", nullable = false)
	private Integer green;
	@Column(name = "BLUE", nullable = false)
	private Integer blue;
	@Column(name = "IMG", nullable = false)
	private String img;
}
