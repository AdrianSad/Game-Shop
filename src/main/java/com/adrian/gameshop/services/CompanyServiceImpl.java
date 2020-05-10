package com.adrian.gameshop.services;

import com.adrian.gameshop.models.Company;
import com.adrian.gameshop.models.Game;
import com.adrian.gameshop.repositories.CompanyRepository;
import com.adrian.gameshop.repositories.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;
    private final GameRepository gameRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository, GameRepository gameRepository) {
        this.companyRepository = companyRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public Set<Company> getCompanies() {

        Set<Company> companies = new HashSet<>();
        companyRepository.findAll().iterator().forEachRemaining(companies::add);
        return companies;
    }


    @Override
    public Set<Game> getCompanyGames(Long companyId) {

        return StreamSupport.stream(gameRepository.findAll()
                .spliterator(), false)
                .filter(game -> game.getCompany().getId().equals(companyId))
                .collect(Collectors.toSet());

    }

    @Override
    @Transactional
    public Company findById(Long id) {

        Optional<Company> companyOptional = companyRepository.findById(id);

        if(companyOptional.isPresent()){
            return companyOptional.get();
        }
        else
            throw new RuntimeException("Company Not Found For ID : " + id);
    }

    @Override
    @Transactional
    public Company saveCompany(Company company) {

        return companyRepository.save(company);
    }

    @Override
    public void deleteById(Long id) {

        companyRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void saveImageFile(Long companyId, MultipartFile file) {

        try{
            Company company = findById(companyId);
            Byte[] byteObject = new Byte[file.getBytes().length];
            int i = 0;

            for(byte b : file.getBytes()){
                byteObject[i++] = b;
            }

            company.setLogo(byteObject);
            companyRepository.save(company);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
