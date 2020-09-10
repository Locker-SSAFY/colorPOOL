package com.ssafy.socks.entity.color;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Embeddable @Getter @Setter @NoArgsConstructor @ToString
public class Color {
	@Column(name = "RED", nullable = false)
	private Integer red;
	@Column(name = "GREEN", nullable = false)
	private Integer green;
	@Column(name = "BLUE", nullable = false)
	private Integer blue;
}
