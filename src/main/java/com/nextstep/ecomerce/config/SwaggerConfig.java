package com.nextstep.ecomerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.time.LocalDate;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket customImplementation() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.nextstep.ecomerce.controller"))
                .build()
                .apiInfo(apiInfo())
                .pathMapping("/")
                .useDefaultResponseMessages(false)
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class);
    }

    ApiInfo apiInfo() {
        return new  ApiInfoBuilder()
                .title("Swagger with Spring Boot + Security")
                .version("1.0.0")
                .description("Your Description")
                .contact(new Contact("Contact Name", "Contact_URL","contact@email.com"))
                .build();
    }
}