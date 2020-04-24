package com.adrian.gameshop.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Document(collection = "categories")
public class Category extends BaseEntity {

    @DBRef
    private List<Game> games;
}
