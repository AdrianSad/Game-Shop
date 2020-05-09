package com.adrian.gameshop.services;

import com.adrian.gameshop.models.Company;
import com.adrian.gameshop.models.Game;
import com.adrian.gameshop.repositories.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    GameServiceImpl gameService;

    @Mock
    GameRepository gameRepository;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
        gameService = new GameServiceImpl(gameRepository);
    }

    @Test
    void getGames() {

        Set<Game> gameSet = new HashSet<>();
        gameSet.add(new Game());

        when(gameService.getGames()).thenReturn(gameSet);

        gameSet = gameService.getGames();

        assertEquals(gameSet.size(), 1);
        verify(gameRepository, times(1)).findAll();
        verify(gameRepository, never()).findById(anyLong());
    }

    @Test
    void findById() {

        Game game = new Game();
        game.setId(1L);

        Optional<Game> gameOptional = Optional.of(game);

        when(gameRepository.findById(anyLong())).thenReturn(gameOptional);

        Game gameFound = gameService.findById(1L);

        assertNotNull(gameFound, "Null game returned");
        verify(gameRepository, times(1)).findById(anyLong());
    }

    @Test
    void saveGame() {

        Company company = Company.builder().build();
        Game game = new Game();
        game.setCompany(company);
        game.setId(1L);

        when(gameRepository.save(any())).thenReturn(game);

        Game savedGame = gameService.saveGame(game);

        assertNotNull(savedGame, "Returned game is null");
        assertEquals(1, savedGame.getId());
        verify(gameRepository, times(1)).save(any(Game.class));

    }

    @Test
    void deleteById() {

        gameService.deleteById(1L);

        verify(gameRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void saveImageFile() throws IOException {

        Long id = 1L;

        MultipartFile multipartFile = new MockMultipartFile("file",
                "testing.txt",
                "text/plain",
                "TEST".getBytes());

        Game game = new Game();
        game.setId(id);
        Optional<Game> gameOptional = Optional.of(game);

        when(gameRepository.findById(anyLong())).thenReturn(gameOptional);

        ArgumentCaptor<Game> argumentCaptor = ArgumentCaptor.forClass(Game.class);

        gameService.saveImageFile(id, multipartFile);

        verify(gameRepository, times(1)).save(argumentCaptor.capture());
        Game savedGame = argumentCaptor.getValue();
        assertEquals(multipartFile.getBytes().length, savedGame.getImage().length);
    }
}