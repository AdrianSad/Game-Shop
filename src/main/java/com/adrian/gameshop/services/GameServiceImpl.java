package com.adrian.gameshop.services;

import com.adrian.gameshop.models.Game;
import com.adrian.gameshop.repositories.GameRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
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
    @Transactional
    public Game findById(Long id) {

        Optional<Game> gameOptional = gameRepository.findById(id);

        if(gameOptional.isPresent())
            return gameOptional.get();
        else
            throw new RuntimeException("Game Not Found For ID : " + id);

    }

    @Override
    @Transactional
    public Game saveGame(Game game) {
        game.getCompany().addGame(game);
        return gameRepository.save(game);
    }

    @Override
    public Page<Game> getGamesOrderByName(int page) {
        return gameRepository.findAllByOrderByNameAsc(PageRequest.of(subtractPageByOne(page), 2, Sort.by("name")));
    }

    @Override
    public void deleteById(Long id) {

        gameRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void saveImageFile(Long gameId, MultipartFile file) {

        try{
            Game game = findById(gameId);
            Byte[] byteObject = new Byte[file.getBytes().length];
            int i = 0;

            for(byte b : file.getBytes()){
                byteObject[i++] = b;
            }

            game.setImage(byteObject);
            gameRepository.save(game);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int subtractPageByOne(int page){
        return (page < 1) ? 0 : page - 1;
    }
}
