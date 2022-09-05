package com.marvelapi.service;
import com.marvelapi.client.MarvelApiClient;
import com.marvelapi.dto.CharacterDTO;
import com.marvelapi.dto.MarvelResponseDTO;
import com.marvelapi.exception.BadRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.darkprograms.speech.translator.GoogleTranslate.translate;
import static java.lang.Math.ceil;
import static java.util.Objects.isNull;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Slf4j
@Service
@AllArgsConstructor
public class MarvelService {

    public static final int LIMIT = 100;
    public static final String DEFAULT_LANGUAGE = "en";
    private final MarvelApiClient client;

    public CharacterDTO getCharacterDetail(Integer characterId, String languageCode){
        var response = client.getCharacterById(characterId).getData().getResults().get(0);
        String newDescription = translateDescription(languageCode, response);
        response.setDescription(newDescription);
        return response;
    }

    private String translateDescription(String languageCode, CharacterDTO response) {
        try {
            if(isTranslatable(languageCode, response))
                return translate(languageCode, response.getDescription());
            return response.getDescription();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new BadRequest("Wrong language code");
        }
    }

    private boolean isTranslatable(String languageCode, CharacterDTO response) {
        return !isNull(response) && isNotBlank(response.getDescription()) &&
                isNotBlank(languageCode) && !DEFAULT_LANGUAGE.equals(languageCode);
    }

    public List<Integer> getCharacterIds(){
        var data = client.getCharacters(LIMIT, 0).getData();
        var ids = data.getResults().stream().map(dto -> dto.getId()).collect(toList());
        List completableFutures = fetchMissingCharacterIds(data.getTotal());
        ids.addAll(convertToList(completableFutures));
        return ids;
    }

    private List fetchMissingCharacterIds(Integer total) {
        int numPagesToFetch =  (int) ceil((double) total / LIMIT);
        List completableFutures = new ArrayList<>();
        for (int i = 1; i < numPagesToFetch; i++) {
            int offset = i*LIMIT;
            completableFutures.add(supplyAsync(() -> client.getCharacters(LIMIT, offset)));
        }
        return completableFutures;
    }

    private List<Integer> convertToList(
            List<CompletableFuture<MarvelResponseDTO<CharacterDTO>>> completableFutures) {
        return completableFutures.stream()
                .map(CompletableFuture::join)
                .flatMap(resp -> resp.getData().getResults().stream())
                .map(characterIdDTO -> characterIdDTO.getId())
                .collect(toList());
    }

}
