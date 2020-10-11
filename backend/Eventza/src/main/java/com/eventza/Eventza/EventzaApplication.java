package com.eventza.Eventza;

import java.util.Collection;
import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAsync
@EnableScheduling
@SpringBootApplication
@EnableSwagger2
public class EventzaApplication {

  public static void main(String[] args) {
    SpringApplication.run(EventzaApplication.class, args);
  }

  @Bean
  public Docket swaggerConfig() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .paths(PathSelectors.ant("/api/**"))
        .apis(RequestHandlerSelectors.basePackage("com.eventza.Eventza"))
        .build()
        .apiInfo(apiInformation());
  }

  private ApiInfo apiInformation(){
    return new ApiInfo(
        "Eventaza API",
        "API for Event Management System",
        "1.0",
        "Free to use",
        new springfox.documentation.service.Contact("Eventaza", "http://localhost:8000", "Eventaza076@gmail.com"),
        "API License",
        "http://localhost:8000",
        Collections.emptyList());
  }
}
