package com.ssafy.socks.entity.magazine;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ssafy.socks.entity.user.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @Setter @Getter @ToString @NoArgsConstructor
@Table(name = "LIKE")
public class Like {

	@Id
	@Column(name = "MAGAZINE_ID")
	private Long magazineId;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;
}
