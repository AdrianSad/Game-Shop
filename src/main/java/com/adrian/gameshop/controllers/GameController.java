package com.adrian.gameshop.controllers;

import com.adrian.gameshop.models.Game;
import com.adrian.gameshop.repositories.CategoryRepository;
import com.adrian.gameshop.services.CompanyService;
import com.adrian.gameshop.services.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;
    private final CompanyService companyService;
    private final CategoryRepository categoryRepository;

    public GameController(GameService gameService, CompanyService companyService, CategoryRepository categoryRepository) {
        this.gameService = gameService;
        this.companyService = companyService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public String gamesListPage(Model model){

        model.addAttribute("games", gameService.getGames());
        return "game/gamesList";
    }

    @GetMapping("/{id}/show")
    public String showGame(@PathVariable Long id, Model model){

        model.addAttribute("game", gameService.findById(id));
        return "game/show";
    }

    @GetMapping("/new")
    public String newGameForm(Model model){

        model.addAttribute("game", new Game());
        model.addAttribute("companies", companyService.getCompanies());
        model.addAttribute("categories", categoryRepository.findAll());

        return "game/createForm";
    }

    @PostMapping("/new")
    public String saveOrUpdateGame(@Valid @ModelAttribute("game") Game game, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "game/createForm";
        }

        Game savedGame = gameService.saveGame(game);
        return "redirect:/games/" + savedGame.getId() + "/show";
    }
}
