package com.adrian.gameshop.controllers;

import com.adrian.gameshop.exceptions.EmailExistsException;
import com.adrian.gameshop.models.User;
import com.adrian.gameshop.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerPage(Model model){

        model.addAttribute("user", new User());
        return "user/registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid User user, Model model, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "user/registration";
        } else {

            try {
                userService.registerNewUserAccount(user);
            } catch (EmailExistsException e) {
                return "user/registration";
            }
        }

        model.addAttribute("user", user);

        return "user/login";
    }
}
