package com.adrian.gameshop.controllers;

import com.adrian.gameshop.models.Company;
import com.adrian.gameshop.models.Game;
import com.adrian.gameshop.repositories.CategoryRepository;
import com.adrian.gameshop.services.CompanyService;
import com.adrian.gameshop.services.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class GameControllerTest {

    @Mock
    GameService gameService;

    @Mock
    CompanyService companyService;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    Model model;

    GameController gameController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
        gameController = new GameController(gameService, companyService, categoryRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(gameController).build();
    }

    @Test
    void gamesListPage() throws Exception {

        Set<Game> games = new HashSet<>();
        games.add(new Game());

        when(gameService.getGames()).thenReturn(games);

        ArgumentCaptor<Set<Game>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        String viewName = gameController.gamesListPage(model);
        assertEquals("game/gamesList", viewName);

        verify(gameService, times(1)).getGames();
        verify(model, times(1)).addAttribute(eq("games"), argumentCaptor.capture());

        Set<Game> setInController = argumentCaptor.getValue();
        assertEquals(1, setInController.size());
    }

    @Test
    void showGame() throws Exception {

        Game game = new Game();
        game.setId(1L);

        when(gameService.findById(anyLong())).thenReturn(game);

        mockMvc.perform(get("/games/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("game/show"))
                .andExpect(model().attributeExists("game"));

        verify(gameService, times(1)).findById(anyLong());
    }

    @Test
    void newGameForm() throws Exception {

        mockMvc.perform(get("/games/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("game/createForm"))
                .andExpect(model().attributeExists("game"))
                .andExpect(model().attributeExists("companies"))
                .andExpect(model().attributeExists("categories"));
    }

    @Test
    void saveOrUpdateGame() throws Exception {

        Company company = new Company();
        Game game = new Game();
        game.setId(1L);
        game.setCompany(company);

        when(gameService.saveGame(any())).thenReturn(game);

        mockMvc.perform(post("/games/new")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
                .param("name", "name test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/games/1/image"));

        verify(gameService, times(1)).saveGame(any());
    }

    @Test
    void newImageForm() throws Exception {

        Game game = new Game();
        game.setId(1L);

        when(gameService.findById(anyLong())).thenReturn(game);

        mockMvc.perform(get("/games/1/image"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("game"));

        verify(gameService, times(1)).findById(anyLong());
    }

    @Test
    void saveOrUpdateImage() throws Exception {

        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "file",
                "testing.txt",
                "text/plain", "Test".getBytes()
        );

        mockMvc.perform(multipart("/games/1/image").file(mockMultipartFile))
                .andExpect(status().is3xxRedirection());

        verify(gameService, times(1)).saveImageFile(anyLong(), any());

    }

    @Test
    void renderImage() throws Exception {

        Game game = new Game();
        game.setId(1L);

        String testString = "TEST";
        Byte[] bytes = new Byte[testString.getBytes().length];

        int i = 0;

        for (byte primByte : testString.getBytes()){
            bytes[i++] = primByte;
        }

        game.setImage(bytes);

        when(gameService.findById(anyLong())).thenReturn(game);

        MockHttpServletResponse response = mockMvc.perform(get("/games/1/gameImage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        byte[] responseBytes = response.getContentAsByteArray();

        assertEquals(testString.getBytes().length, responseBytes.length);
    }
}