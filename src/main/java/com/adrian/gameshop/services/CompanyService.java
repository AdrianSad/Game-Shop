package com.adrian.gameshop.services;

import com.adrian.gameshop.models.Company;
import com.adrian.gameshop.models.Game;

import java.util.Set;

public interface CompanyService {

    Set<Company> getCompanies();

    Set<Game> getCompanyGames(Long companyId);

    Company findById(Long id);
}
