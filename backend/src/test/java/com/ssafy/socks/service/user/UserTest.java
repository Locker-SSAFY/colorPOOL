package com.ssafy.socks.service.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.socks.config.security.JwtTokenProvider;
import com.ssafy.socks.entity.user.User;
import com.ssafy.socks.model.user.SignUpModel;
import com.ssafy.socks.model.user.UserInfo;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class UserTest {
	private static final String token = "test-token";
	private static final String entryPointUri = "/exception/entrypoint";
	private static final String accessDeniedUri = "/exception/accessdenied";

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserService userService;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private WebApplicationContext context;
	@Autowired
	private ObjectMapper objectMapper;

	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders
			.webAppContextSetup(context)
			.apply(springSecurity())
			.build();
	}

	@Test
	public void 회원_단건_조회_using_jwt() throws Exception {
		// given
		User user = User.builder()
			.email("test@test.com")
			.password(passwordEncoder.encode("1234!"))
			.nickname("testMan")
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
		assertEquals(user, findUser);
	}

	@Test
	public void 잘못된_JWT_토큰_사용시_exception_entrypoint() throws Exception {
		// given
		String content = objectMapper.writeValueAsString(
			new SignUpModel(new UserInfo("test@test.com", "1234!", "test"), "testMan"));

		// when
		mockMvc.perform(MockMvcRequestBuilders
			.post("/api/signup")
			.content(content)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.characterEncoding("UTF-8"))
			.andDo(print())
			.andExpect(status().isOk());

		// then
		mockMvc.perform(MockMvcRequestBuilders
			.get("/api/user")
			.header("X-AUTH-TOKEN", token)
			.param("lang", "ko")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.characterEncoding("UTF-8"))
			.andDo(print())
			.andExpect(redirectedUrl(entryPointUri));
	}

	@Test
	@WithMockUser(username = "mockUser", roles = {"USER"}) // 가상의 Mock 유저 대입
	public void 유저_권한_부족_예외처리() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
			.get("/api/users"))
			.andDo(print())
			.andExpect(redirectedUrl(accessDeniedUri));
	}
}
