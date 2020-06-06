package com.adrian.gameshop.services;

import com.adrian.gameshop.models.User;
import com.adrian.gameshop.models.UserRole;
import com.adrian.gameshop.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new UsernameNotFoundException("No user found with username: " + email);
        } else {

            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    getAuthorities(user.getUserRoles()));
        }

    }

    private static List<GrantedAuthority> getAuthorities (Set<UserRole> roles) {

        List<GrantedAuthority> authorities = new ArrayList<>();

        for(UserRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }
}
