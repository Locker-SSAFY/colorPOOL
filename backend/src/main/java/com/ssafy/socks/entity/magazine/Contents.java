package com.ssafy.socks.entity.magazine;

import javax.persistence.CascadeType;
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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Setter @Getter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "CONTENTS")
public class Contents {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CONTENT_ID")
	private Long id;

	@Column(name = "MAGAZINE_ID")
	private Long magazineId;

	@Column(name = "URL", nullable = false)
	private String url;

	@Column(name = "MAIN_TEXT", nullable = false)
	private String mainText;

	@Column(name = "SUB_TEXT")
	private String subText;

	@Column(name = "QUESTION", nullable = false)
	private String question;

	@Column(name = "ANSWER", nullable = false)
	private String answer;

	@Column(name = "TEMPLATE", nullable = false)
	private String template;
}
