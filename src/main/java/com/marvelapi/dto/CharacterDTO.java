package com.marvelapi.dto;

import lombok.Data;

@Data
public class CharacterDTO {
    private Integer id;
    private String name;
    private String description;
    private ThumbnailDTO thumbnail;
}

