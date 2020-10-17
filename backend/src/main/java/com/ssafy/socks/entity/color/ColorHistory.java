package com.ssafy.socks.entity.color;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ssafy.socks.entity.user.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @Setter @Getter @NoArgsConstructor @ToString
@Table(name = "COLOR_HISTORY")
public class ColorHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COLOR_HISTORY_ID", nullable = false)
	private Long id;

	@Column(name = "SELECTED_COLOR_ID")
	private Long selectedColorId;

	@Column(name = "USER_ID")
	private Long userId;
}
