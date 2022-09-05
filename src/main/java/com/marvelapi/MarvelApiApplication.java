package com.marvelapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@EnableFeignClients
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Marvel API", version = "1.0"))
public class MarvelApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarvelApiApplication.class, args);
    }

}
