package com.n26.assignment.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Ahmad Hamouda on 2/24/17.
 */

@Controller
@EnableSwagger2
public class SwaggerConfig {
    @Value("${swagger.group.name}")
    private String groupName;
    @Value("${swagger.paths.regex}")
    private String pathsRegex;
    @Value("${swagger.title")
    private String title;
    @Value("${swagger.description}")
    private String description;
    @Value("${swagger.terms.service.url}")
    private String termsURL;
    @Value("${swagger.contact}")
    private String contact;
    @Value("${swagger.license}")
    private String license;
    @Value("${swagger.license.url}")
    private String licenseURL;
    @Value("${swagger.version}")
    private String version;

    @Bean
    public Docket fcaApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.n26.assignment"))
                .paths(PathSelectors.any())
//                .paths(regex(pathsRegex))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl(termsURL)
                .contact(contact)
                .license(license)
                .licenseUrl(licenseURL)
                .version(version)
                .build();
    }

}
