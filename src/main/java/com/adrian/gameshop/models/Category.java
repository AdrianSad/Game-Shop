package com.adrian.gameshop.models;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(exclude = {"games"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    @ManyToMany(mappedBy = "categories")
    private Set<Game> games;

    @Builder
    public Category(Long id, @NotEmpty String name) {
        super(id, name);
        games = new HashSet<>();
    }
}
