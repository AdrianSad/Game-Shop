package com.adrian.gameshop.services;

import com.adrian.gameshop.exceptions.EmailExistsException;
import com.adrian.gameshop.models.User;
import com.adrian.gameshop.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public User registerNewUserAccount(User user) throws EmailExistsException {

        if(emailExist(user.getEmail())){
            throw new EmailExistsException("There is an account with that email address : "
            + user.getEmail());
        }

        return userRepository.save(user);
    }

    private boolean emailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
