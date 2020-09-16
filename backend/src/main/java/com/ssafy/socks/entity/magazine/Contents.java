package com.ssafy.socks.entity.magazine;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Setter @Getter @NoArgsConstructor
@Table(name = "CONTENTS")
public class Contents {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CONTENT_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MAGAZINE_ID", nullable = false)
	private Magazine magazine;
	public void addMagazine(Magazine magazine) {
		this.magazine = magazine;
		this.magazine.getContents().add(this);
	}

	@Lob
	@Column(name = "CONTENT", nullable = false)
	private String content;

	@Column(name = "PAGE", nullable = false)
	private Integer page;

}