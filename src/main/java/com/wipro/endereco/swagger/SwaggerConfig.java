package com.wipro.endereco.swagger;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "app.swagger.enabled", havingValue = "true")
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wipro.endereco.controller"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/v1")
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Endereço API",
                "API para consulta de endereço por CEP",
                "1.0.0",
                "Terms of service",
                new Contact("Jocimar Neres", "https://www.linkedin.com/in/jocimar-neres/", "jocimarneres@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
