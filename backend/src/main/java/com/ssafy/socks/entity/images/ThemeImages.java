package com.ssafy.socks.entity.images;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ssafy.socks.entity.color.Theme;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThemeImages {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "THEME_IMG_ID", nullable = false)
	private Long id;

	@Column(name = "IMG_URL", nullable = false)
	private String imageUrl;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "THEME_ID")
	private Theme theme;

	/* 연관관계 편의 메서드 */
	public void addSelectedColor(Theme theme) {
		this.theme = theme;
		this.theme.getCrawledImages().add(this);	// 양방향의 순수 객체 상태를 유지하기위함
	}
}
