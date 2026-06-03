package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;

@Controller
@RequestMapping("/user")
public class UserController {

    // Доступно для USER и ADMIN
    @GetMapping()
    public String userProfile(Authentication authentication, Model model) {
        // Получаем текущего пользователя
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        boolean isAdmin = user.getRoles().stream()
                .anyMatch(role -> role.getTitle().equals("ROLE_ADMIN"));
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("currentPage", "profile");
        return "user/profile";
    }
}