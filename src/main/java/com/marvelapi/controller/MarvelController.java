package com.marvelapi.controller;

import com.marvelapi.dto.CharacterDTO;
import com.marvelapi.service.MarvelService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/marvel")
public class MarvelController {

    public static final String GET_CHARACTERS = "GET_CHARACTERS";
    private final MarvelService service;

    @Cacheable(GET_CHARACTERS)
    @GetMapping("/characters")
    public List<Integer> getCharacters(){
        return service.getCharacterIds();
    }

    @GetMapping("/characters/{characterId}")
    public CharacterDTO getCharacterById(@PathVariable Integer characterId, @RequestParam(required = false) String languageCode){
        return this.service.getCharacterDetail(characterId, languageCode);
    }
}
