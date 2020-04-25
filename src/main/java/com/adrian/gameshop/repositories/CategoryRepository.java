package com.adrian.gameshop.repositories;

import com.adrian.gameshop.models.Category;
import org.springframework.data.repository.CrudRepository;


public interface CategoryRepository extends CrudRepository<Category, String> {

    Category findByName(String name);
}
