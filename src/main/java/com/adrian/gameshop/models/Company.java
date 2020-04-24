package com.adrian.gameshop.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Document
public class Company extends BaseEntity {

    private String nationality;
    private Byte[] logo;
    @DBRef
    private List<Game> games;
}
