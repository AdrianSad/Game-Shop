package com.adrian.gameshop.controllers;

import com.adrian.gameshop.services.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public String companyListPage(Model model){

        model.addAttribute("companies", companyService.getCompanies());
        return "company/companyList";
    }

    @GetMapping("/{id}/show")
    public String companyGamesPage(@PathVariable Long id, Model model){

        model.addAttribute("companyGames", companyService.getCompanyGames(id));
        model.addAttribute("company", companyService.findById(id));

        return "company/show";
    }
}
