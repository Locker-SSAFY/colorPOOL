package com.ssafy.socks.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.socks.advice.exception.CEmailSigninFailedException;
import com.ssafy.socks.advice.exception.CUserDuplicatedException;
import com.ssafy.socks.advice.exception.CUserNotFoundException;
import com.ssafy.socks.entity.user.User;
import com.ssafy.socks.repository.user.UserJpaRepository;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
	private final UserJpaRepository userJpaRepository;

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

	public void deleteById(Long id) {
		try {
			userJpaRepository.deleteById(id);
		} catch (Exception e) {
			throw new CUserNotFoundException();
		}
	}
}
