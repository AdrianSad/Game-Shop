package com.adrian.gameshop.controllers;

import com.adrian.gameshop.models.Company;
import com.adrian.gameshop.models.Game;
import com.adrian.gameshop.services.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

class CompanyControllerTest {

    @Mock
    CompanyService companyService;

    CompanyController companyController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
        companyController = new CompanyController(companyService);
        mockMvc = MockMvcBuilders.standaloneSetup(companyController).build();
    }

    @Test
    void companyListPage() throws Exception {

        Set<Company> companies = new HashSet<>();
        companies.add(new Company());

        when(companyService.getCompanies()).thenReturn(companies);

        mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andExpect(view().name("company/companyList"))
                .andExpect(model().attributeExists("companies"));

        verify(companyService, times(1)).getCompanies();
    }

    @Test
    void companyGamesPage() throws Exception {

        Company company = Company.builder().id(1L).build();
        company.addGame(new Game());
        company.addGame(new Game());

        when(companyService.findById(anyLong())).thenReturn(company);
        when(companyService.getCompanyGames(anyLong())).thenReturn(company.getGames());

        mockMvc.perform(get("/companies/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("company/show"))
                .andExpect(model().attributeExists("company"))
                .andExpect(model().attributeExists("companyGames"));

        verify(companyService, times(1)).getCompanyGames(anyLong());
    }
}