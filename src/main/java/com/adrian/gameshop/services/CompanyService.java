package com.adrian.gameshop.services;

import com.adrian.gameshop.models.Company;
import com.adrian.gameshop.models.Game;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface CompanyService {

    Set<Company> getCompanies();

    Set<Game> getCompanyGames(Long companyId);

    Company findById(Long id);

    Company saveCompany(Company company);

    void deleteById(Long id);

    void saveImageFile(Long companyId, MultipartFile file);
}
