package com.ssafy.socks.entity.color;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ssafy.socks.entity.images.ThemeImages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString @Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "SELECTED_COLOR")
public class SelectedColor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SELECTED_COLOR_ID", nullable = false)
	private Long id;

	@Embedded
	private Color color;

	@OneToMany(mappedBy = "selectedColor")
	private List<ColorHistory> colorHistories;

	@OneToMany(mappedBy = "selectedColor")
	private List<Theme> themes;
}
