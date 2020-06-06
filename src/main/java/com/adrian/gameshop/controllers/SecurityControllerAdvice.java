package com.adrian.gameshop.controllers;

import com.adrian.gameshop.models.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class SecurityControllerAdvice {

    @ModelAttribute("currentUser")
    User currentUser(@AuthenticationPrincipal User currentUser){
        return currentUser;
    }
}
