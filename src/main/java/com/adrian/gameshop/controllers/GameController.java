package com.adrian.gameshop.controllers;

import com.adrian.gameshop.models.Game;
import com.adrian.gameshop.repositories.CategoryRepository;
import com.adrian.gameshop.services.CompanyService;
import com.adrian.gameshop.services.GameService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

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
    public String gamesListPage(@RequestParam(defaultValue = "0") int page, Model model){

        Page<Game> games = gameService.getGamesOrderByName(page);

        model.addAttribute("pages", games);

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
    public String saveOrUpdateGame(@Valid @ModelAttribute("game") Game game, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){

            model.addAttribute("companies", companyService.getCompanies());
            model.addAttribute("categories", categoryRepository.findAll());
            return "game/createForm";
        }

        Game savedGame = gameService.saveGame(game);

        return "redirect:/games/" + savedGame.getId() + "/image";
    }

    @GetMapping("/{id}/image")
    public String newImageForm(@PathVariable Long id, Model model){

        model.addAttribute("game", gameService.findById(id));
        return "game/uploadImageForm";
    }

    @PostMapping("/{id}/image")
    public String saveOrUpdateImage(@PathVariable Long id, @RequestParam("file") MultipartFile file){

        gameService.saveImageFile(id,file);

        return "redirect:/games/" + id + "/show";
    }

    @GetMapping("/{id}/delete")
    public String deleteGame(@PathVariable Long id){

        gameService.deleteById(id);

        return "redirect:/games";
    }

    @GetMapping("/{id}/update")
    public String updateGame(@PathVariable Long id, Model model){

        model.addAttribute("game", gameService.findById(id));
        model.addAttribute("companies", companyService.getCompanies());
        model.addAttribute("categories", categoryRepository.findAll());

        return "game/createForm";
    }

    @GetMapping("/{id}/gameImage")
    public void renderImage(@PathVariable Long id, HttpServletResponse response) throws IOException {

        Game game = gameService.findById(id);
        byte[] byteArray = new byte[game.getImage().length];
        int i = 0;

        for (Byte wrappedByte : game.getImage()){
            byteArray[i++] = wrappedByte;
        }
        response.setContentType("image/jpeg");
        InputStream inputStream = new ByteArrayInputStream(byteArray);
        IOUtils.copy(inputStream, response.getOutputStream());
    }
}
