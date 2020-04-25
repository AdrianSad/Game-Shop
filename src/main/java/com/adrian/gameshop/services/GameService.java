package com.adrian.gameshop.services;

import com.adrian.gameshop.models.Game;

import java.util.Set;


public interface GameService {

    Set<Game> getGames();

    Game findById(Long id);

    void deleteById(Long id);
}
