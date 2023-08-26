package com.streetdrop.external.swagger.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;


@Profile({"local", "dev"})
@Configuration
public class SwaggerConfig {
    private final String IDFV_TOKEN_HEADER = "x-sdp-idfv";

    @Value(value = "${swagger.server-url}")
    private String serverUrl;

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("v1")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI springOpenAPI() {
        SecurityScheme bearerAuth = new SecurityScheme().type(SecurityScheme.Type.APIKEY).name(IDFV_TOKEN_HEADER).in(SecurityScheme.In.HEADER);
        SecurityRequirement securityItem = new SecurityRequirement().addList(IDFV_TOKEN_HEADER);

        return new OpenAPI()
                .components(new Components().addSecuritySchemes(IDFV_TOKEN_HEADER, bearerAuth))
                .addSecurityItem(securityItem)
                .info(new Info().title("Street Drop Server API")
                        .description("Street Drop Server API 명세서입니다.")
                        .version("v0.0.1"))
                .servers(List.of(new Server().url(serverUrl)));
    }

}