package com.ssafy.socks.entity.color;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ssafy.socks.entity.images.ThemeImages;
import com.ssafy.socks.entity.magazine.Magazine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "THEME")
public class Theme {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "THEME_ID", nullable = false)
	private Long id;

	@Column(name = "R1", nullable = false)
	private Integer red1;
	@Column(name = "G1", nullable = false)
	private Integer green1;
	@Column(name = "B1", nullable = false)
	private Integer blue1;

	@Column(name = "R2", nullable = false)
	private Integer red2;
	@Column(name = "G2", nullable = false)
	private Integer green2;
	@Column(name = "B2", nullable = false)
	private Integer blue2;

	@Column(name = "R3", nullable = false)
	private Integer red3;
	@Column(name = "G3", nullable = false)
	private Integer green3;
	@Column(name = "B3", nullable = false)
	private Integer blue3;

	@Column(name = "R4", nullable = false)
	private Integer red4;
	@Column(name = "G4", nullable = false)
	private Integer green4;
	@Column(name = "B4", nullable = false)
	private Integer blue4;

	@Column(name = "R5", nullable = false)
	private Integer red5;
	@Column(name = "G5", nullable = false)
	private Integer green5;
	@Column(name = "B5", nullable = false)
	private Integer blue5;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SELECTED_COLOR_ID")
	private SelectedColor selectedColor;

	@OneToMany(mappedBy = "theme")
	private List<ThemeImages> crawledImages;

	/* 연관관계 편의 메서드 */
	public void addSelectedColor(SelectedColor selectedColor) {
		this.selectedColor = selectedColor;
		this.selectedColor.getThemes().add(this);	// 양방향의 순수 객체 상태를 유지하기위함
	}
}
