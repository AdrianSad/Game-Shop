package com.adrian.gameshop.repositories;

import com.adrian.gameshop.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
