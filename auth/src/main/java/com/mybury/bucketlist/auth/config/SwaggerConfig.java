package com.mybury.bucketlist.auth.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
						.title("MYBURY 2.0 - API")
						.build();
	}
	
	@Bean
	public Docket api() {
		ParameterBuilder builder = new ParameterBuilder();
		builder.name("X-Auth-Token")
				.modelRef(new ModelRef("string"))
				.parameterType("header")
				.required(false)
				.build();
		
		List<Parameter> builders = new ArrayList<Parameter>();
		builders.add(builder.build());
		
		return new Docket(DocumentationType.SWAGGER_2)
						.globalOperationParameters(builders)
						.useDefaultResponseMessages(false)
						.apiInfo(this.apiInfo())
						.select()
			            .apis(RequestHandlerSelectors.basePackage("com.mybury.bucketlist.auth.v2.controller"))
			            .paths(PathSelectors.any())
						.build();
	}	
}