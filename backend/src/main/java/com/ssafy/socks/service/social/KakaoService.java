package com.ssafy.socks.service.social;

import java.util.Objects;
import java.util.Optional;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.ssafy.socks.advice.exception.CCommunicationException;
import com.ssafy.socks.config.security.JwtTokenProvider;
import com.ssafy.socks.entity.user.User;
import com.ssafy.socks.model.social.KakaoModel;
import com.ssafy.socks.model.social.KakaoProfile;
import com.ssafy.socks.model.social.SocialResultModel;
import com.ssafy.socks.model.user.UserInfo;
import com.ssafy.socks.repository.user.UserJpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor @Service
public class KakaoService {
	private final static String PROVIDER = "kakao";

	private final UserJpaRepository userJpaRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final RestTemplate restTemplate;
	private final Environment env;
	private final Gson gson;

	/**
	 * 카카오 프로필 가져 오기
	 * @param accessToken
	 * @return
	 */
	public KakaoProfile getKakaoProfile(String accessToken) {
		// Set header : Content-type: application/x-www-form-urlencoded
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.set("Authorization", "Bearer " + accessToken);

		// Set http entity
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, headers);
		try {
			// Request profile
			ResponseEntity<String> response = restTemplate.postForEntity(
				Objects.requireNonNull(env.getProperty("spring.social.kakao.url.profile")), request, String.class);
			if (response.getStatusCode() == HttpStatus.OK)
				return gson.fromJson(response.getBody(), KakaoProfile.class);
		} catch (Exception e) {
			throw new CCommunicationException();
		}
		throw new CCommunicationException();
	}

	/**
	 * 카카오 통합 회원이
	 * 		있을 경우 -> 로그인
	 *		없을 경우 -> 회원 가입 틀 반환
	 * @param kakaoModel
	 * @return
	 */
	public SocialResultModel getKakaoResultModel(KakaoModel kakaoModel) {
		SocialResultModel resultModel = null;
		KakaoProfile kakaoProfile = this.getKakaoProfile(kakaoModel.getAccessToken());

		Optional<User> optionalUser = userJpaRepository.findByEmailAndProvider(
			kakaoModel.getUserInfo().getEmail(), kakaoModel.getUserInfo().getProvider());
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			resultModel = SocialResultModel.builder()
				.id(user.getId())
				.nickname(user.getNickname())
				.userInfo(new UserInfo(user.getEmail(),user.getPassword(),user.getProvider()))
				.token(jwtTokenProvider.createToken(String.valueOf(user.getId()), user.getRoles()))
				.build();
		} else {
			resultModel = SocialResultModel.builder()
				.id(null)
				.nickname(kakaoProfile.getProperties().getNickname())
				.userInfo(new UserInfo(kakaoProfile.getEmail(),null, PROVIDER))
				.token(null)
				.build();
		}
		return resultModel;
	}

}
