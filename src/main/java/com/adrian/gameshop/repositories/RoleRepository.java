package com.adrian.gameshop.repositories;

import com.adrian.gameshop.models.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<UserRole, String> {

    UserRole findByName(String name);
}
