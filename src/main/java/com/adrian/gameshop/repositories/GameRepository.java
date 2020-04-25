package com.adrian.gameshop.repositories;

import com.adrian.gameshop.models.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, String> {
}
