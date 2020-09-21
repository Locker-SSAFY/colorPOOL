package com.ssafy.socks.service.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.ssafy.socks.advice.exception.CUserNotFoundException;
import com.ssafy.socks.repository.user.UserJpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor @Service
public class CustomUserDetailService implements UserDetailsService {

	private final UserJpaRepository userJpaRepository;

	public UserDetails loadUserByUsername(String userPk) {
		return userJpaRepository.findById(Long.parseLong(userPk)).orElseThrow(CUserNotFoundException::new);
	}
}
