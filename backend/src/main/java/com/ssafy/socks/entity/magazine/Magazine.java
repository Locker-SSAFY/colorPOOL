package com.ssafy.socks.entity.magazine;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ssafy.socks.entity.color.SelectedColor;
import com.ssafy.socks.entity.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Setter @Getter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "MAGAZINE")
public class Magazine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MAGAZINE_ID", nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SELECTED_COLOR_ID", nullable = false)
	private SelectedColor selectedColor;

	@OneToMany(mappedBy = "magazine")
	private List<Contents> contents;

	@OneToMany(mappedBy = "magazine")
	private List<Likes> likes;

	@OneToMany(mappedBy = "magazineBookmark")
	private List<Bookmark> bookmarks;

	@Column(name = "CREATED_DATE", nullable = false)
	private LocalDateTime createdDate;
}
