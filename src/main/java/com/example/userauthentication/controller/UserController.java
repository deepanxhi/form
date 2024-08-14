package com.example.userauthentication.controller;


import com.example.userauthentication.entity.UserDtls;
import com.example.userauthentication.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.userauthentication.model.LoginRequest;


@Controller
public class UserController {
    @Autowired
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDtls());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserDtls user, HttpSession session) {
        userService.saveUser(user);
        session.setAttribute("message", "Registration successful, now login");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginRequest") LoginRequest loginRequest, Model model) {
        // Logic for authenticating the user
        UserDtls user = userService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        if (user != null) {
            model.addAttribute("user", user);
            return "welcome";
        } else {
            model.addAttribute("errorMessage", "Invalid email or password. Please try again.");
            return "login";
        }
    }


    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
}
