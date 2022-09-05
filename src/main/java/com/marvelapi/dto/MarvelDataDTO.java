package com.marvelapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class MarvelDataDTO<T> {
    private Integer total;
    private List<T> results;
}

