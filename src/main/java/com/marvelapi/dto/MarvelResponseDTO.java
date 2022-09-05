package com.marvelapi.dto;

import lombok.Data;

@Data
public class MarvelResponseDTO<T>{
    private MarvelDataDTO<T> data;
}
