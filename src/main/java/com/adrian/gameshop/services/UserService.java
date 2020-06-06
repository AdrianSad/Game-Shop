package com.adrian.gameshop.services;

import com.adrian.gameshop.exceptions.EmailExistsException;
import com.adrian.gameshop.models.User;

public interface UserService {

    User registerNewUserAccount(User user) throws EmailExistsException;
    User getUser(String username);
}
