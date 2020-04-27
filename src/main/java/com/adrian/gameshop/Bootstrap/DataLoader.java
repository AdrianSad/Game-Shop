package com.adrian.gameshop.Bootstrap;

import com.adrian.gameshop.models.Category;
import com.adrian.gameshop.models.Company;
import com.adrian.gameshop.models.Game;
import com.adrian.gameshop.models.Platform;
import com.adrian.gameshop.repositories.CategoryRepository;
import com.adrian.gameshop.repositories.CompanyRepository;
import com.adrian.gameshop.repositories.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    private final GameRepository gameRepository;
    private final CategoryRepository categoryRepository;
    private final CompanyRepository companyRepository;

    public DataLoader(GameRepository gameRepository, CategoryRepository categoryRepository, CompanyRepository companyRepository) {
        this.gameRepository = gameRepository;
        this.categoryRepository = categoryRepository;
        this.companyRepository = companyRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        loadData();
    }

    private void loadData(){

        // LOAD CATEGORIES

        Category rpg = categoryRepository.save(Category.builder().name("RPG").build());
        Category fpp = categoryRepository.save(Category.builder().name("FPP").build());
        Category fps = categoryRepository.save(Category.builder().name("FPS").build());
        categoryRepository.save(Category.builder().name("MMORPG").build());
        categoryRepository.save(Category.builder().name("Tower Defense").build());
        categoryRepository.save(Category.builder().name("Fighting").build());
        Category tpp = categoryRepository.save(Category.builder().name("TPP").build());
        Category sandbox = categoryRepository.save(Category.builder().name("Sandbox").build());

        log.info("####  Loaded " + categoryRepository.count() + " categories    ####");

        // LOAD COMPANIES

        Company cdp = companyRepository.save(Company.builder().name("CD Projekt Red").nationality("Polish").build());
        companyRepository.save(Company.builder().name("Electronic Arts").nationality("American").build());
        Company ubisoft = companyRepository.save(Company.builder().name("Ubisoft").nationality("French").build());
        companyRepository.save(Company.builder().name("Activision Blizzard").nationality("American").build());

        log.info("####  Loaded " + companyRepository.count() + " companies  ####");

        // LOAD GAMES

        Game game1 = new Game();
        game1.setName("Witcher 3");
        game1.setDescription("The Witcher® 3: Wild Hunt is a story-driven, next-generation open world role-playing game, set in a visually stunning fantasy universe, full of meaningful choices and impactful consequences. You play as Geralt of Rivia, a monster hunter tasked with finding a child from an ancient prophecy.");
        game1.setPrice(new BigDecimal(114.99));

        game1.getCategories().add(rpg);
        game1.getCategories().add(tpp);
        game1.getCategories().add(sandbox);

        game1.setPlatforms(Platform.PC.getName() + "\n" + Platform.XBOXONE.getName() + "\n" + Platform.PS4.getName());
        cdp.addGame(game1);

        gameRepository.save(game1);


        Game game2 = new Game();
        game2.setName("Assassin’s Creed Origins");
        game2.setDescription("Assassin's Creed Origins is an action-adventure stealth game played from a third-person perspective. Players complete quests—linear scenarios with set objectives—to progress through the story, earn experience points, and acquire new skills.");
        game2.setPrice(new BigDecimal(91.32));

        game2.getCategories().add(tpp);
        game2.getCategories().add(sandbox);

        game2.setPlatforms(Platform.PC + "\n" + Platform.XBOXONE + "\n" + Platform.PS4);
        ubisoft.addGame(game2);

        gameRepository.save(game2);

        Game game3 = new Game();
        game3.setName("Far Cry 3");
        game3.setDescription("Far Cry 3 is a 2012 first-person shooter developed by Ubisoft Montreal and published by Ubisoft. It is the third main installment in the Far Cry series. The game takes place on the fictional Rook Islands, a tropical archipelago which can be freely explored by players. Gameplay focuses on combat and exploration.");
        game3.setPrice(new BigDecimal(101.99));

        game3.getCategories().add(fpp);
        game3.getCategories().add(fps);
        game3.getCategories().add(sandbox);

        game3.setPlatforms(Platform.PC + "\n" + Platform.XBOXONE + "\n" + Platform.XBOX360 + "\n" + Platform.PS4 + "\n" + Platform.PS3);
        ubisoft.addGame(game3);

        gameRepository.save(game3);

        log.info("####  Loaded " + gameRepository.count() + " games  ####");

    }

}
