package com.pratap.clinicals.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket clinicalApi() {
		
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
						.apis(RequestHandlerSelectors.basePackage("com.pratap.clinicals.controllers"))
						.paths(PathSelectors.regex("/api.*"))
						.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
						.description("Clinical Api Service")
						.version("2.0")
						.title("Clinical Api Documentation")
						.termsOfServiceUrl("Open Source")
						.contact(new Contact("Pratap Narayan", "https://www.linkedin.com/in/pratap-narayan-85a07725/", "narayanpratap86@gmail.com"))
						.license("Pratap Licence")
						.build();
	}

}
