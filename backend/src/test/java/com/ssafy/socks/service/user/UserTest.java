package com.ssafy.socks.service.user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ssafy.socks.config.security.JwtTokenProvider;
import com.ssafy.socks.entity.user.User;

@SpringBootTest @Transactional
public class UserTest {
	@Autowired private PasswordEncoder passwordEncoder;
	@Autowired private UserService userService;
	@Autowired private JwtTokenProvider jwtTokenProvider;

	@Test
	public void 회원_단건_조회_using_jwt() throws Exception {
	    // given
		User user = User.builder()
			.email("test@test.com")
			.password(passwordEncoder.encode("1234!"))
			.provider("root")
			.roles(Collections.singletonList("ROLE_USER"))
			.build();
		userService.join(user);

	    // when
		String token = jwtTokenProvider.createToken(String.valueOf(user.getId()), user.getRoles());
		Authentication authentication = jwtTokenProvider.getAuthentication(token);
		String email = authentication.getName();
		User findUser = userService.findByEmail(email);

		// then
		assertEquals(user,findUser);
	 }
}
