package com.adrian.gameshop.services;

import com.adrian.gameshop.models.Game;
import com.adrian.gameshop.repositories.GameRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }


    @Override
    public Set<Game> getGames() {

        Set<Game> games = new HashSet<>();
        gameRepository.findAll().iterator().forEachRemaining(games::add);
        return games;
    }

    @Override
    public Game findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
