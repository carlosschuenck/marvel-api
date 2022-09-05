package com.marvelapi.config;

import com.marvelapi.controller.MarvelController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.CacheManager;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.marvelapi.controller.MarvelController.GET_CHARACTERS;

@Slf4j
@Component
@AllArgsConstructor
public class CacheInitializeConfig {

    private final MarvelController controller;
    private final CacheManager cacheManager;

    @EventListener(ApplicationReadyEvent.class)
    public void createCache() {
        controller.getCharacters();
        log.info("Characters endpoint cached successfully!!");
    }

    // Reset cache 1AM every day.
    @Scheduled(cron = "0 0 1 * * ?")
    public void resetCache(){
        cacheManager.getCache(GET_CHARACTERS).clear();
        controller.getCharacters();
        log.info("Characters cache reset successfully!!");
    }
}
