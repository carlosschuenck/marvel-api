package com.marvelapi.client;

import com.marvelapi.config.MarvelConfig;
import com.marvelapi.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "marvelClient", url = "${marvel-api.url}", configuration = MarvelConfig.class)
public interface MarvelApiClient {

    @GetMapping(value = "/v1/public/characters")
    MarvelResponseDTO<CharacterDTO> getCharacters(@RequestParam Integer limit, @RequestParam Integer offset);

    @GetMapping(value = "/v1/public/characters/{characterId}")
    MarvelResponseDTO<CharacterDTO> getCharacterById(@PathVariable Integer characterId);
}
