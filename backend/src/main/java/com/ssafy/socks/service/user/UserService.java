package com.ssafy.socks.service.user;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.socks.advice.exception.CEmailSigninFailedException;
import com.ssafy.socks.advice.exception.CUserDuplicatedException;
import com.ssafy.socks.advice.exception.CUserNotFoundException;
import com.ssafy.socks.entity.user.User;
import com.ssafy.socks.model.user.SignUpModel;
import com.ssafy.socks.repository.user.UserJpaRepository;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
	private final static String ROLE_USER = "ROLE_USER";

	private final UserJpaRepository userJpaRepository;
	private final PasswordEncoder passwordEncoder;

	/**
	 * 회원 가입
	 * @param user
	 * @return
	 */
	@Transactional
	public Long join (User user) {
		validateDuplicateUser(user); // 중복회원 검증
		userJpaRepository.save(user);
		return user.getId();
	}

	/**
	 * 회원 가입
	 * @param signUpModel
	 */
	@Transactional
	public void join (SignUpModel signUpModel) {
		User user = User.builder()
			.email(signUpModel.getUserInfo().getEmail())
			.nickname(signUpModel.getNickname())
			.password(passwordEncoder.encode(signUpModel.getUserInfo().getPassword()))
			.provider(signUpModel.getUserInfo().getProvider())
			.roles(Collections.singletonList(ROLE_USER))
			.build();

		validateDuplicateUser(user); // 중복회원 검증
		userJpaRepository.save(user);
	}

	/**
	 * 중복회원 검사
	 * 중복
	 * @param user
	 */
	private void validateDuplicateUser(User user) {
		Optional<User> optionalUser = userJpaRepository.findByEmail(user.getEmail());
		optionalUser.ifPresent(findUser -> {
			throw new CUserDuplicatedException();
		});
	}

	/**
	 * 모든 유저 검색
	 * @return
	 */
	public List<User> findUsers() {
		return userJpaRepository.findAll();
	}

	/**
	 * 특정 유저 검색
	 * @param id
	 * @return
	 */
	public User findOne(Long id) {
		return userJpaRepository.findById(id).orElseThrow(CUserNotFoundException::new);
	}

	/**
	 * 이메일로 유저 검색
	 * @param email
	 * @return
	 */
	public User findByEmail(String email) {
		return userJpaRepository.findByEmail(email).orElseThrow(CEmailSigninFailedException::new);
	}

	/**
	 * PK로 유저 탈퇴
	 * @param id
	 */
	public void deleteById(Long id) {
		try {
			userJpaRepository.deleteById(id);
		} catch (Exception e) {
			throw new CUserNotFoundException();
		}
	}

	/**
	 * 아이디와 소셜 서비스 제공처로 검색 하기
	 * @param email
	 * @param provider
	 */
	public User findByEmailAndProvider(String email, String provider) {
		return userJpaRepository.findByEmailAndProvider(email,provider).orElseThrow(CUserNotFoundException::new);
	}
}
