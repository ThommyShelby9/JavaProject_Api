//  package com.example.demo.config;

// import java.util.Collections;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// import springfox.documentation.builders.PathSelectors;
// import springfox.documentation.builders.RequestHandlerSelectors;
// import springfox.documentation.service.ApiInfo;
// import springfox.documentation.spi.DocumentationType;
// import springfox.documentation.spring.web.plugins.Docket;
// import springfox.documentation.swagger2.annotations.EnableSwagger2;

// @Configuration
// @EnableSwagger2
// public class SwaggerConfig implements WebMvcConfigurer{
//     @Bean
//     public Docket api() {
//         return new Docket(DocumentationType.SWAGGER_2)
//                 .select()
//                 .paths(PathSelectors.ant("/api/**"))
//                 .apis(RequestHandlerSelectors.basePackage("com.apicrud.demo.ApicrudApplication"))
//                 .build()
//                 .apiInfo(apiInfo());
//     }

//     private ApiInfo apiInfo() {
//         return new ApiInfo(
//                 "My REST API", // title
//                 "Some custom description of API.", // description
//                 "Version 1.0", // version
//                 "Terms of service", // terms of service URL
//                 new springfox.documentation.service.Contact("Bhanuka Dissanayake", "www.example.com",
//                         "myeaddress@company.com"),
//                 "License of API", "API license URL", Collections.emptyList()); // contact info
//     }

//     @Override
//     public void addResourceHandlers(ResourceHandlerRegistry registry) {
//         registry.addResourceHandler("swagger-ui.html")
//                 .addResourceLocations("classpath:/META-INF/resources/");

//         registry.addResourceHandler("/webjars/**")
//                 .addResourceLocations("classpath:/META-INF/resources/webjars/");
//     }
// }