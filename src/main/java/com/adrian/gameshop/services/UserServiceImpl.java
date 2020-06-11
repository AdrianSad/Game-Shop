package com.adrian.gameshop.services;

import com.adrian.gameshop.exceptions.EmailExistsException;
import com.adrian.gameshop.models.User;
import com.adrian.gameshop.repositories.RoleRepository;
import com.adrian.gameshop.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public User registerNewUserAccount(User user) throws EmailExistsException {

        if(emailExist(user.getEmail())){
            throw new EmailExistsException("There is an account with that email address : "
            + user.getEmail());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserRoles(Arrays.asList(roleRepository.findByName("ROLE_CUSTOMER")));
        return userRepository.save(user);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByEmail(username);
    }

    private boolean emailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
