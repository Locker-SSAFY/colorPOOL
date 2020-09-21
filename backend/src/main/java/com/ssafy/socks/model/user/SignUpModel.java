package com.ssafy.socks.model.user;

import java.util.List;

import javax.persistence.Embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class SignUpModel {
	@Embedded UserInfo userInfo;
	private String nickname;
}
