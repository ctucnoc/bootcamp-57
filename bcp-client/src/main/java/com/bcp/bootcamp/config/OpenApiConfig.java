package com.bcp.bootcamp.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;
import io.swagger.v3.core.util.Yaml;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class OpenApiConfig {

	@Bean
	OpenAPI customOpenAPI() throws IOException {
		Resource resource = new ClassPathResource("openapi/openapi.yaml");
		String openApiYaml = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
		OpenAPI openAPI = Yaml.mapper().readValue(openApiYaml, OpenAPI.class);
		return openAPI;
	}
}
