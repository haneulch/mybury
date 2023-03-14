package com.mybury.bucketlist.auth.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {
  @Bean
  public GroupedOpenApi SecurityGroupOpenApi() {
    return GroupedOpenApi
      .builder()
      .group("Mybury")
      .pathsToExclude("/v2/**", "/")
      .addOpenApiCustomiser(buildSecurityOpenApi())
      .build();
  }

  public OpenApiCustomiser buildSecurityOpenApi() {
    SecurityScheme securityScheme = new SecurityScheme()
      .name("X-Auth-Token")
      .type(SecurityScheme.Type.APIKEY)
      .in(SecurityScheme.In.HEADER)
      .name("X-Auth-Token");

    return OpenApi -> OpenApi
      .addSecurityItem(new SecurityRequirement().addList("apiKeyScheme"))
      .getComponents().addSecuritySchemes("apiKeyScheme", securityScheme);
  }
}
