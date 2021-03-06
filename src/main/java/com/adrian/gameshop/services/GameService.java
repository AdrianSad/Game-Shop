package com.adrian.gameshop.services;

import com.adrian.gameshop.models.Game;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;


public interface GameService {

    Set<Game> getGames();

    Game findById(Long id);

    Game saveGame(Game game);

    Page<Game> getGamesOrderByName(int page);

    void deleteById(Long id);

    void saveImageFile(Long gameId, MultipartFile file);
}
