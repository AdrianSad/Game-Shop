package com.adrian.gameshop.models;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Document(collection = "games")
public class Game extends BaseEntity{

    private String description;
    private String platform;
    private List<Category> categories = new ArrayList<>();
    private BigDecimal price;
    private String company;
    private Byte[] image;

}
