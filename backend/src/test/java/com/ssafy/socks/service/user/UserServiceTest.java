package com.ssafy.socks.service.user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.socks.advice.exception.CUserDuplicatedException;
import com.ssafy.socks.entity.user.User;
import com.ssafy.socks.repository.user.UserJpaRepository;

@SpringBootTest @Transactional
class UserServiceTest {
	@Autowired private UserJpaRepository userJpaRepository;
	@Autowired private UserService userService;
	@Autowired private PasswordEncoder passwordEncoder;

	@Test
	public void 회원가입() throws Exception {
	    // given
		User user = User.builder()
			.email("test@test.com")
			.password(passwordEncoder.encode("1234!"))
			.provider("root")
			.roles(Collections.singletonList("GUEST"))
			.build();
	    
	    // when
		Long savedId = userService.join(user);

		// then
		assertEquals(user,userService.findOne(savedId));
	 }
	 
	 @Test
	 public void 중복_회원_예외() throws Exception {
		 // given
		 User user1 = User.builder()
			 .email("test@test.com")
			 .password(passwordEncoder.encode("1234!"))
			 .provider("root")
			 .roles(Collections.singletonList("GUEST"))
			 .build();

		 User user2 = User.builder()
			 .email("test@test.com")
			 .password(passwordEncoder.encode("1234!"))
			 .provider("root")
			 .roles(Collections.singletonList("GUEST"))
			 .build();

		 // when
		 userService.join(user1);

		 // then
		 assertThrows(CUserDuplicatedException.class, () -> {
			 userService.join(user2);
		 });
	 }

	@Test
	public void 동시_같은_아이디_가입() throws Exception {
		// given
		User user1 = User.builder()
			.email("test@test.com")
			.password(passwordEncoder.encode("1234!"))
			.provider("root")
			.roles(Collections.singletonList("GUEST"))
			.build();

		User user2 = User.builder()
			.email("test@test.com")
			.password(passwordEncoder.encode("1234!"))
			.provider("root")
			.roles(Collections.singletonList("GUEST"))
			.build();

		// when
		userJpaRepository.save(user1);

		// then
		assertThrows(DataIntegrityViolationException.class, () -> {
			userJpaRepository.save(user2);
		});
	}
}
