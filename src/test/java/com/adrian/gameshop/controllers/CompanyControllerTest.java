package com.adrian.gameshop.controllers;

import com.adrian.gameshop.models.Company;
import com.adrian.gameshop.models.Game;
import com.adrian.gameshop.services.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
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

    @Test
    void newCompanyForm() throws Exception {

        mockMvc.perform(get("/companies/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("company/createForm"))
                .andExpect(model().attributeExists("company"));
    }

    @Test
    void saveOrUpdateCompany() throws Exception {

        Company company = Company.builder().id(1L).build();

        when(companyService.saveCompany(any())).thenReturn(company);

        mockMvc.perform(post("/companies/new")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
                .param("name", "TEST")
                .param("nationality", "TEST"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/companies/1/logo"));

        verify(companyService, times(1)).saveCompany(any());
    }

    @Test
    void updateCompany() throws Exception {

        Company company = Company.builder().id(1L).build();

        when(companyService.findById(anyLong())).thenReturn(company);

        mockMvc.perform(get("/companies/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("company/createForm"))
                .andExpect(model().attributeExists("company"));
    }

    @Test
    void deleteCompany() throws Exception {

        mockMvc.perform(get("/companies/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/companies"));

        verify(companyService, times(1)).deleteById(anyLong());
    }

    @Test
    void newImageForm() throws Exception {

        Company company = Company.builder().id(1L).build();

        when(companyService.findById(anyLong())).thenReturn(company);

        mockMvc.perform(get("/companies/1/logo"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("company"));

        verify(companyService, times(1)).findById(anyLong());
    }

    @Test
    void saveOrUpdateImage() throws Exception {

        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "file",
                "testing.txt",
                "text/plain", "Test".getBytes()
        );

        mockMvc.perform(multipart("/companies/1/logo").file(mockMultipartFile))
                .andExpect(status().is3xxRedirection());

        verify(companyService, times(1)).saveImageFile(anyLong(), any());

    }

    @Test
    void renderImage() throws Exception {

        Company company = Company.builder().id(1L).build();

        String testString = "TEST";
        Byte[] bytes = new Byte[testString.getBytes().length];

        int i = 0;

        for (byte primByte : testString.getBytes()){
            bytes[i++] = primByte;
        }

        company.setLogo(bytes);

        when(companyService.findById(anyLong())).thenReturn(company);

        MockHttpServletResponse response = mockMvc.perform(get("/companies/1/companyImage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        byte[] responseBytes = response.getContentAsByteArray();

        assertEquals(testString.getBytes().length, responseBytes.length);
    }
}