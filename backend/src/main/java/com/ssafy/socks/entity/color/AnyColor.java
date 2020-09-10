package com.ssafy.socks.entity.color;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @Setter @Getter @NoArgsConstructor @ToString
@Table(name = "ANY_COLOR")
public class AnyColor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ANY_COLOR_ID", nullable = false)
	private Long id;

	@Embedded
	private Color color;

}
