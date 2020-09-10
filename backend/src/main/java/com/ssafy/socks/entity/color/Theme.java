package com.ssafy.socks.entity.color;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
@Table(name = "THEME")
public class Theme {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "THEME_ID", nullable = false)
	private Long id;

	@Embedded
	private ThemeColor themeColor;

	@ManyToOne
	@JoinColumn(name = "SELECTED_COLOR_ID")
	private SelectedColor selectedColor;
}
