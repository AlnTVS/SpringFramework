package com.geekbrains.july.market.controllers;

import com.geekbrains.july.market.configs.SecurityConfig;
import com.geekbrains.july.market.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdditionalController {


    private UsersService currentUsersService;

    @Autowired
    AdditionalController(UsersService currentUsersService) {
        this.currentUsersService = currentUsersService;
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

    @GetMapping("/")
    public String homepage() {
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login_page";
    }

    @GetMapping("/profile/{userName}")
    public String profilePage(@PathVariable String userName, Model model) {
    model.addAttribute("userInfo", currentUsersService.findByPhone(userName));
        return  "about_me"; }
}