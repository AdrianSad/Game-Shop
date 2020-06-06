package com.adrian.gameshop.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "games")
public class Game extends BaseEntity{

    @Lob
    @NotNull
    @Size(min = 15, max = 500)
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "platforms")
    private String platforms;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "game_category",
    joinColumns = @JoinColumn(name = "game_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @NotNull
    @Min(10)
    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER)
    private Company company;

    @Lob
    @Column(name = "image")
    private Byte[] image;

}
