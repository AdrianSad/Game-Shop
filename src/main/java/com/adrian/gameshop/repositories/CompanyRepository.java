package com.adrian.gameshop.repositories;

import com.adrian.gameshop.models.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company,String> {

    Company findByName(String name);
}
