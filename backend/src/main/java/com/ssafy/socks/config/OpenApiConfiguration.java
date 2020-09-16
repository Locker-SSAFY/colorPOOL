package com.ssafy.socks.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfiguration {
	@Bean
	public OpenAPI openAPI(@Value("${springdoc.version}") String appVersion) {
		Info info = new Info().title("color-POOL API").version(appVersion)
			.description("Spring Boot를 이용한 color-POOL 웹 애플리케이션 API입니다.")
			.termsOfService("https://j3a303.p.ssafy.io/")
			.contact(
				new Contact().name("Team. Socks").url("https://github.com/Locker-SSAFY").email("junhok82@gmail.com"))
			.license(
				new License().name("Apache License Version 2.0").url("http://www.apache.org/licenses/LICENSE-2.0"));

		return new OpenAPI()
			.components(new Components())
			.info(info);
	}
}
