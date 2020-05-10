package com.adrian.gameshop.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(exclude = {"games"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "companies")
public class Company extends BaseEntity{


    @NotNull
    @Column(name = "nationality")
    private String nationality;

    @Lob
    @Column(name = "logo")
    private Byte[] logo;

    @OneToMany(mappedBy = "company",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    private Set<Game> games;

    @Builder
    public Company(Long id, @NotEmpty String name, String nationality) {
        super(id, name);
        this.nationality = nationality;
        games = new HashSet<>();
    }


    public void addGame(Game game){
        game.setCompany(this);
        games.add(game);
    }
}
