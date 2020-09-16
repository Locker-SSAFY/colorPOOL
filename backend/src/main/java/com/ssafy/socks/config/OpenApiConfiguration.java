package com.ssafy.socks.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
	info = @Info(title = "color-POOL API 명세서",
		description = "API 명세서",
		version = "v1",
		contact = @Contact(name = "Socks", email = "junhok82@gmail.com"),
		license = @License(name = "Apache 2.0",
			url = "http://www.apache.org/licenses/LICENSE-2.0.html")
	)
)
@Configuration
public class OpenApiConfiguration {
	/**
	 * customApi
	 * @return GroupedOpenApi
	 */
	@Bean
	public GroupedOpenApi customApi() {
		String[] paths = {"/api/**"};
		return GroupedOpenApi.builder().setGroup("color-POOL API").pathsToMatch(paths)
			.build();
	}
}
