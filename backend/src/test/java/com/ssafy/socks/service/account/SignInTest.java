package com.ssafy.socks.service.account;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ssafy.socks.advice.exception.CEmailSigninFailedException;
import com.ssafy.socks.entity.user.User;
import com.ssafy.socks.service.user.UserService;

@SpringBootTest @Transactional
public class SignInTest {
	@Autowired private UserService userService;
	@Autowired private PasswordEncoder passwordEncoder;

	@Test
	public void 로그인() throws Exception {
	    // given
		User user = User.builder()
			.email("test@test.com")
			.password(passwordEncoder.encode("1234!"))
			.nickname("testMan")
			.provider("ROOT")
			.roles(Collections.singletonList("ROLE_USER"))
			.build();
		userService.join(user);

	    // when
		User findUser = userService.findByEmail("test@test.com");
		assertThrows(CEmailSigninFailedException.class,() -> {
			userService.findByEmail("test@tset.com");
		});

		// then
		assertEquals(user,findUser);
	 }
}
