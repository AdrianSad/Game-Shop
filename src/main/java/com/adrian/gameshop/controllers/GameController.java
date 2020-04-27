package com.adrian.gameshop.controllers;

import com.adrian.gameshop.services.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games")
    public String gamesListPage(Model model){

        model.addAttribute("games", gameService.getGames());
        return "game/gamesList";
    }

    @GetMapping("/games/{id}/show")
    public String showGame(@PathVariable Long id, Model model){

        model.addAttribute("game", gameService.findById(id));
        return "game/show";
    }
}
