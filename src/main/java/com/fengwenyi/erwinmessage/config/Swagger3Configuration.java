package com.fengwenyi.erwinmessage.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-31
 */
@Configuration
public class Swagger3Configuration {

    @Bean
    public GroupedOpenApi commonApi() {
        return GroupedOpenApi.builder()
                .group("common")
                .pathsToMatch("/common/**")
                .build();
    }

    @Bean
    public GroupedOpenApi indexApi() {
        return GroupedOpenApi.builder()
                .group("index")
                .pathsToMatch("/index/**")
                .build();
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("admin")
                .pathsToMatch("/admin/**")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Erwin Message APIs")
                        .description("即时通讯")
                        .version("v0.0.1")
                        .license(
                                new License()
                                        .name("MIT License")
                                        .url("https://github.com/fengwenyi/erwin-message/blob/main/LICENSE")
                        )
                );
    }

}
