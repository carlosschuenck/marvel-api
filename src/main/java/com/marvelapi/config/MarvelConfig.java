package com.marvelapi.config;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.time.Instant;

import static org.springframework.util.DigestUtils.md5DigestAsHex;

public class MarvelConfig {

    @Value("${marvel-api.publicKey}")
    private String publicKey;
    @Value("${marvel-api.privateKey}")
    private String privateKey;
    private Long timestamp = Instant.now().getEpochSecond();

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.query("ts", timestamp.toString());
            requestTemplate.query("apikey",publicKey);
            requestTemplate.query("hash", generateHash());
        };
    }

    private String generateHash(){
        String stringToHash = timestamp + privateKey + publicKey;
        return md5DigestAsHex(stringToHash.getBytes());
    }
}
