package com.social.network.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

@Configuration
@EnableSwagger2
@Profile("dev")
public class SwaggerConfiguration {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage(" com.social.network.controllers"))
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(LocalDateTime.class, Data.class)
                .directModelSubstitute(LocalDate.class, String.class)
                .forCodeGeneration(true)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Social network API", "", "0.0.1", "",
                new Contact("artsem.sidarenka", "", ""), "", "", Collections.emptyList());

    }

//    @Bean
//    public SecurityConfiguration security() {
//        return new SecurityConfiguration(null, null, null, null, null, ApiKeyVehicle.HEADER, "Authorization", ",");
//    }
}
