package com.adrian.gameshop.services;

import com.adrian.gameshop.models.Company;
import com.adrian.gameshop.models.Game;
import com.adrian.gameshop.repositories.CompanyRepository;
import com.adrian.gameshop.repositories.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CompanyServiceTest {

    CompanyServiceImpl companyService;

    @Mock
    CompanyRepository companyRepository;

    @Mock
    GameRepository gameRepository;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
        companyService = new CompanyServiceImpl(companyRepository, gameRepository);
    }

    @Test
    void getCompanies() {

        Company company = new Company();
        Set<Company> companies = new HashSet<>();
        companies.add(company);

        when(companyService.getCompanies()).thenReturn(companies);

        companies = companyService.getCompanies();

        assertEquals(companies.size(), 1);
        verify(companyRepository, times(1)).findAll();
        verify(companyRepository, never()).findById(anyLong());
    }

    @Test
    void getCompanyGames() {

        /*Company company = Company.builder().id(1L).build();

        Game game1 = new Game();
        game1.setId(1L);

        Game game2 = new Game();
        game1.setId(2L);

        company.addGame(game1);
        company.addGame(game2);

        Set<Game> gameSet = new HashSet<>();
        gameSet.add(game1);
        gameSet.add(game2);

        when(companyService.getCompanyGames(anyLong())).thenReturn(gameSet);

        Set<Game> companyGames = companyService.getCompanyGames(1L);

        assertEquals(companyGames.size(), 2);
        verify(gameRepository, times(1)).findAll();
        verify(companyRepository, times(1)).findById(anyLong());*/

    }

    @Test
    void findById() {

        Company company = new Company();
        company.setId(1L);

        Optional<Company> companyOptional = Optional.of(company);

        when(companyRepository.findById(anyLong())).thenReturn(companyOptional);

        Company companyFound = companyService.findById(1L);

        assertNotNull(companyFound, "Null company returned");
        verify(companyRepository, times(1)).findById(anyLong());

    }
}